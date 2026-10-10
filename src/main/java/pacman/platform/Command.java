package pacman.platform;

/** A labelled soft-key action. Labels are compared by identity in places, so the label reference is kept as-is. */
public final class Command {
    public static final int SCREEN = 1;
    public static final int BACK = 2;
    public static final int OK = 4;

    private final String label;
    private final int type;
    private final int priority;

    public Command(String label, int type, int priority) {
        this.label = label;
        this.type = type;
        this.priority = priority;
    }

    public String getLabel() {
        return label;
    }

    public int getCommandType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
}
