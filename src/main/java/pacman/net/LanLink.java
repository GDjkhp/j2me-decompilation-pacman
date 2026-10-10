package pacman.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A packet-oriented connection to one other player. Each {@link #send} is delivered as one packet of the same
 * size (messages are length-prefixed on the TCP stream), and received packets are queued so that
 * {@link #ready()} never blocks.
 */
public final class LanLink {
    private static final int CONNECT_TIMEOUT_MS = 5000;
    private static final int MAX_PACKET = 1024;
    private static final byte[] CLOSED = new byte[0];

    private final Socket socket;
    private final DataOutputStream out;
    private final BlockingQueue<byte[]> inbox = new LinkedBlockingQueue<>();
    private volatile boolean closed;

    LanLink(Socket socket) throws IOException {
        this.socket = socket;
        socket.setTcpNoDelay(true);
        this.out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        Thread reader = new Thread(() -> readLoop(in), "pacman-lan-reader");
        reader.setDaemon(true);
        reader.start();
    }

    /** Opens a connection to a discovered host. */
    public static LanLink connect(LanHost host) throws IOException {
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(host.address(), host.port()), CONNECT_TIMEOUT_MS);
            return new LanLink(s);
        } catch (IOException e) {
            s.close();
            throw e;
        }
    }

    private void readLoop(DataInputStream in) {
        try {
            while (true) {
                int len = in.readUnsignedShort();
                if (len > MAX_PACKET) {
                    throw new IOException("Oversized packet: " + len);
                }
                byte[] packet = new byte[len];
                in.readFully(packet);
                inbox.add(packet);
            }
        } catch (IOException e) {
            closed = true;
            inbox.add(CLOSED);
        }
    }

    /** True if a packet can be received without blocking. */
    public boolean ready() throws IOException {
        byte[] head = inbox.peek();
        if (head == null) {
            return false;
        }
        if (head == CLOSED) {
            throw new IOException("Connection closed");
        }
        return true;
    }

    /**
     * Receives one packet into {@code buffer}, blocking until one arrives. Extra bytes are discarded.
     *
     * @return the number of bytes copied
     */
    public int receive(byte[] buffer) throws IOException {
        try {
            byte[] packet = inbox.take();
            if (packet == CLOSED) {
                inbox.add(CLOSED);
                throw new IOException("Connection closed");
            }
            int n = Math.min(packet.length, buffer.length);
            System.arraycopy(packet, 0, buffer, 0, n);
            return n;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Interrupted", e);
        }
    }

    /** Sends {@code data} as one packet. */
    public synchronized void send(byte[] data) throws IOException {
        if (closed) {
            throw new IOException("Connection closed");
        }
        out.writeShort(data.length);
        out.write(data);
        out.flush();
    }

    public void close() throws IOException {
        closed = true;
        socket.close();
    }
}
