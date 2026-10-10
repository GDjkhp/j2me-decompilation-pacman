package pacman.net;

import java.net.InetAddress;

/** A game found on the local network: the advertised service name and where to connect. */
public final class LanHost {
    private final String name;
    private final InetAddress address;
    private final int port;

    LanHost(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    /** The advertised service name, {@code nickname_loopSettings_waitSettings}. */
    public String getName() {
        return name;
    }

    InetAddress address() {
        return address;
    }

    int port() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof LanHost && ((LanHost) o).address.equals(address) && ((LanHost) o).port == port;
    }

    @Override
    public int hashCode() {
        return address.hashCode() * 31 + port;
    }
}
