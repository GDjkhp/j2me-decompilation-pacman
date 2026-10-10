package pacman.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/** Listens for {@link LanListener} announcements for a fixed time and collects the games it hears about. */
public final class LanDiscovery {
    static final String MAGIC = "PACMAN-LAN1";
    static final int PORT = 47236;
    private static final int MAX_HOSTS = 8;

    private final List<LanHost> hosts = new ArrayList<>();
    private final Runnable onDone;
    private volatile boolean cancelled;
    private DatagramSocket socket;

    private LanDiscovery(Runnable onDone) {
        this.onDone = onDone;
    }

    /**
     * Starts searching for {@code durationMs}; {@code onDone} runs when the search ends (also if it was cancelled
     * or could not start).
     */
    public static LanDiscovery start(int durationMs, Runnable onDone) {
        LanDiscovery d = new LanDiscovery(onDone);
        Thread t = new Thread(() -> d.run(durationMs), "pacman-lan-discovery");
        t.setDaemon(true);
        t.start();
        return d;
    }

    private void run(int durationMs) {
        try {
            DatagramSocket s = new DatagramSocket(null);
            s.setReuseAddress(true);
            s.bind(new InetSocketAddress(PORT));
            s.setSoTimeout(200);
            synchronized (this) {
                socket = s;
            }
            long end = System.currentTimeMillis() + durationMs;
            byte[] buf = new byte[256];
            while (!cancelled && System.currentTimeMillis() < end) {
                try {
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    s.receive(p);
                    handle(new String(p.getData(), 0, p.getLength(), StandardCharsets.UTF_8), p);
                } catch (java.net.SocketTimeoutException e) {
                    // Keep waiting until the time is up.
                }
            }
            s.close();
        } catch (IOException e) {
            // Could not listen (port busy / no network): report "nothing found".
        } finally {
            onDone.run();
        }
    }

    private void handle(String text, DatagramPacket from) {
        String[] parts = text.split("\\|", 3);
        if (parts.length != 3 || !MAGIC.equals(parts[0])) {
            return;
        }
        try {
            LanHost host = new LanHost(parts[2], normalise(from.getAddress()), Integer.parseInt(parts[1]));
            synchronized (this) {
                if (hosts.size() < MAX_HOSTS && !hosts.contains(host)) {
                    hosts.add(host);
                }
            }
        } catch (NumberFormatException e) {
            // Ignore malformed announcements.
        }
    }

    /**
     * Addresses of this machine all become the loopback address, so a game announced on several local
     * interfaces is listed once.
     */
    private static InetAddress normalise(InetAddress address) {
        try {
            if (address.isLoopbackAddress() || NetworkInterface.getByInetAddress(address) != null) {
                return InetAddress.getLoopbackAddress();
            }
        } catch (SocketException e) {
            // Keep the address as received.
        }
        return address;
    }

    /** Stops searching early. */
    public void cancel() {
        cancelled = true;
    }

    /** The games heard so far. */
    public synchronized LanHost[] hosts() {
        return hosts.toArray(new LanHost[0]);
    }
}
