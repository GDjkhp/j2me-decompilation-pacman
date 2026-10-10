package pacman.platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * A tiny record-based save file. Records are numbered from 1 and the whole store is rewritten atomically on every
 * change. Files live in {@code ~/.pacman-se} (override with the {@code pacman.data.dir} system property).
 */
public final class PersistentStore {
    private final Path file;
    private final List<byte[]> records = new ArrayList<>();

    private PersistentStore(Path file) {
        this.file = file;
    }

    /**
     * Opens a store, optionally creating it when it does not exist yet.
     *
     * @throws IOException if the store does not exist and {@code create} is false, or on I/O failure
     */
    public static PersistentStore open(String name, boolean create) throws IOException {
        Path dir = Paths.get(System.getProperty("pacman.data.dir",
                Paths.get(System.getProperty("user.home"), ".pacman-se").toString()));
        Files.createDirectories(dir);
        PersistentStore store = new PersistentStore(dir.resolve(name + ".dat"));
        if (Files.exists(store.file)) {
            store.load();
        } else if (!create) {
            throw new IOException("No such store: " + name);
        }
        return store;
    }

    private void load() throws IOException {
        try (DataInputStream in = new DataInputStream(Files.newInputStream(file))) {
            int count = in.readInt();
            for (int i = 0; i < count; i++) {
                byte[] data = new byte[in.readInt()];
                in.readFully(data);
                records.add(data);
            }
        } catch (IOException e) {
            records.clear(); // A corrupt save is treated as empty rather than fatal.
        }
    }

    private void save() throws IOException {
        Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(tmp))) {
            out.writeInt(records.size());
            for (byte[] r : records) {
                out.writeInt(r.length);
                out.write(r);
            }
        }
        Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
    }

    public synchronized int getNumRecords() {
        return records.size();
    }

    /** Returns a copy of record {@code id} (1-based). */
    public synchronized byte[] getRecord(int id) {
        return records.get(id - 1).clone();
    }

    /** Replaces record {@code id} (1-based). */
    public synchronized void setRecord(int id, byte[] data, int offset, int length) throws IOException {
        byte[] copy = new byte[length];
        System.arraycopy(data, offset, copy, 0, length);
        records.set(id - 1, copy);
        save();
    }

    /** Appends a record and returns its id. */
    public synchronized int addRecord(byte[] data, int offset, int length) throws IOException {
        byte[] copy = new byte[length];
        System.arraycopy(data, offset, copy, 0, length);
        records.add(copy);
        save();
        return records.size();
    }

    public void close() {
        // All changes are written through immediately; nothing to release.
    }
}
