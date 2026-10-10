package pacman;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pacman.platform.PersistentStore;

/** A persistent top-10 list of scores, shown by the "HIGH SCORES" menu entry. */
final class LocalScores {
    static final int MAX_ENTRIES = 10;
    private static final String STORE = "hiscores";

    private record Entry(String name, int score) {
    }

    private LocalScores() {
    }

    /**
     * Adds a score to the table.
     *
     * @return the 1-based rank achieved, or 0 if the score did not make the table
     */
    static synchronized int submit(String name, int score) {
        List<Entry> entries = load();
        int pos = 0;
        while (pos < entries.size() && entries.get(pos).score() >= score) {
            pos++;
        }
        if (pos >= MAX_ENTRIES) {
            return 0;
        }
        entries.add(pos, new Entry(name, score));
        while (entries.size() > MAX_ENTRIES) {
            entries.remove(entries.size() - 1);
        }
        save(entries);
        return pos + 1;
    }

    /**
     * Formats the table for the ranking screen: {@code highlightRank,count,rank,name,score,rank,name,score,...}.
     * Entries whose rank equals {@code highlightRank} are drawn highlighted; pass 0 for none.
     */
    static synchronized String format(int highlightRank) {
        List<Entry> entries = load();
        StringBuilder sb = new StringBuilder();
        sb.append(highlightRank).append(',').append(entries.size());
        for (int i = 0; i < entries.size(); i++) {
            sb.append(',').append(i + 1)
              .append(',').append(entries.get(i).name().replace(',', ' '))
              .append(',').append(entries.get(i).score());
        }
        return sb.toString();
    }

    private static List<Entry> load() {
        List<Entry> entries = new ArrayList<>();
        try {
            PersistentStore store = PersistentStore.open(STORE, false);
            try {
                if (store.getNumRecords() > 0) {
                    DataInputStream in = new DataInputStream(new ByteArrayInputStream(store.getRecord(1)));
                    int count = in.readInt();
                    for (int i = 0; i < count && i < MAX_ENTRIES; i++) {
                        entries.add(new Entry(in.readUTF(), in.readInt()));
                    }
                }
            } finally {
                store.close();
            }
        } catch (IOException e) {
            // No table yet (or unreadable): start empty.
            entries.clear();
        }
        return entries;
    }

    private static void save(List<Entry> entries) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeInt(entries.size());
            for (Entry e : entries) {
                out.writeUTF(e.name());
                out.writeInt(e.score());
            }
            byte[] data = bytes.toByteArray();
            PersistentStore store = PersistentStore.open(STORE, true);
            try {
                if (store.getNumRecords() == 0) {
                    store.addRecord(data, 0, data.length);
                } else {
                    store.setRecord(1, data, 0, data.length);
                }
            } finally {
                store.close();
            }
        } catch (IOException e) {
            // Saving is best effort; the game continues without persistence.
        }
    }
}
