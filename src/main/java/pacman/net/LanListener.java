package pacman.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Waits for one other player to connect. While open it advertises {@code serviceName} by UDP broadcast
 * (and to the loopback address, so two copies on one machine can find each other).
 */
public final class LanListener {
    private final ServerSocket server;
    private final Thread announcer;
    private volatile boolean closed;

    public LanListener(String serviceName) throws IOException {
        server = new ServerSocket(0);
        byte[] message = (LanDiscovery.MAGIC + "|" + server.getLocalPort() + "|" + serviceName)
                .getBytes(StandardCharsets.UTF_8);
        announcer = new Thread(() -> announceLoop(message), "pacman-lan-announce");
        announcer.setDaemon(true);
        announcer.start();
    }

    /** Blocks until a player connects, then stops advertising. */
    public LanLink accept() throws IOException {
        try {
            Socket s = server.accept();
            return new LanLink(s);
        } finally {
            closed = true;
        }
    }

    public void close() throws IOException {
        closed = true;
        server.close();
    }

    private void announceLoop(byte[] message) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            while (!closed) {
                for (InetAddress target : targets()) {
                    try {
                        socket.send(new DatagramPacket(message, message.length, target, LanDiscovery.PORT));
                    } catch (IOException ignored) {
                        // Interface may be down; try the others.
                    }
                }
                Thread.sleep(500);
            }
        } catch (IOException | InterruptedException e) {
            // Stop advertising.
        }
    }

    private static List<InetAddress> targets() {
        List<InetAddress> list = new ArrayList<>();
        list.add(InetAddress.getLoopbackAddress());
        try {
            for (NetworkInterface nif : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!nif.isUp() || nif.isLoopback()) {
                    continue;
                }
                for (InterfaceAddress ia : nif.getInterfaceAddresses()) {
                    if (ia.getBroadcast() != null) {
                        list.add(ia.getBroadcast());
                    }
                }
            }
        } catch (IOException e) {
            // Fall back to loopback only.
        }
        return list;
    }
}
