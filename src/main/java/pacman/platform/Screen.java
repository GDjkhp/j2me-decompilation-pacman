package pacman.platform;

/**
 * A full-window drawing surface with keyboard and pointer callbacks. Subclasses implement {@link #paint(Gfx)}
 * and override the input hooks they care about. The logical size is fixed at {@value #WIDTH}x{@value #HEIGHT};
 * {@link Display} scales it to the real window.
 */
public abstract class Screen {
    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;

    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 5;
    public static final int DOWN = 6;
    public static final int FIRE = 8;
    public static final int GAME_A = 9;
    public static final int GAME_B = 10;
    public static final int GAME_C = 11;
    public static final int GAME_D = 12;

    private volatile boolean shown;

    /** Draws the whole screen. */
    protected abstract void paint(Gfx g);

    protected void keyPressed(int keyCode) {
    }

    protected void keyRepeated(int keyCode) {
    }

    protected void keyReleased(int keyCode) {
    }

    protected void pointerPressed(int x, int y) {
    }

    protected void pointerDragged(int x, int y) {
    }

    protected void pointerReleased(int x, int y) {
    }

    /** Called when this screen becomes the visible one (or the window is restored). */
    protected void showNotify() {
    }

    /** Called when this screen stops being visible (or the window is minimised). */
    protected void hideNotify() {
    }

    public final int getWidth() {
        return WIDTH;
    }

    public final int getHeight() {
        return HEIGHT;
    }

    public final boolean isShown() {
        return shown;
    }

    /** Requests an asynchronous repaint. */
    public final void repaint() {
        Display.getDisplay().requestRepaint(this);
    }

    /** Requests a repaint of a region (the whole screen is repainted regardless). */
    public final void repaint(int x, int y, int w, int h) {
        repaint();
    }

    /** Blocks until any pending repaint request has been painted. */
    public final void serviceRepaints() {
        Display.getDisplay().paintNow(this);
    }

    /** No-op on desktop: the window always uses the full canvas area. */
    public final void setFullScreenMode(boolean fullScreen) {
    }

    /** Maps a key code to one of the game actions ({@link #UP}, {@link #FIRE}, ...), or 0. */
    public int getGameAction(int keyCode) {
        switch (keyCode) {
            case -1:
            case '2':
                return UP;
            case -2:
            case '8':
                return DOWN;
            case -3:
            case '4':
                return LEFT;
            case -4:
            case '6':
                return RIGHT;
            case -5:
            case '5':
                return FIRE;
            case '1':
                return GAME_A;
            case '3':
                return GAME_B;
            case '7':
                return GAME_C;
            case '9':
                return GAME_D;
            default:
                return 0;
        }
    }

    final void setShown(boolean shown) {
        this.shown = shown;
    }

    final void dispatchKeyPressed(int keyCode) {
        keyPressed(keyCode);
    }

    final void dispatchKeyRepeated(int keyCode) {
        keyRepeated(keyCode);
    }

    final void dispatchKeyReleased(int keyCode) {
        keyReleased(keyCode);
    }

    final void dispatchPointerPressed(int x, int y) {
        pointerPressed(x, y);
    }

    final void dispatchPointerDragged(int x, int y) {
        pointerDragged(x, y);
    }

    final void dispatchPointerReleased(int x, int y) {
        pointerReleased(x, y);
    }

    final void dispatchShowNotify() {
        showNotify();
    }

    final void dispatchHideNotify() {
        hideNotify();
    }

    final void dispatchPaint(Gfx g) {
        paint(g);
    }
}
