package pacman.platform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The application window. Hosts the current {@link Screen}, renders it into a fixed-size back buffer and scales that
 * to the window, and translates keyboard / mouse input into the key codes and pointer events the game expects.
 */
public final class Display {
    /** Callbacks for window life-cycle events. */
    public interface Lifecycle {
        /** The window became active (or was shown for the first time). */
        void onResume();

        /** The window was deactivated or minimised. */
        void onPause();

        /** The user asked to close the window. */
        void onClose();
    }

    private static final Display INSTANCE = new Display();

    private final Object lock = new Object();
    private final BufferedImage back = new BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private final Gfx backGfx;
    private final Set<Integer> keysDown = new HashSet<>();
    private JFrame frame;
    private CanvasPanel panel;
    private Lifecycle lifecycle;
    private volatile Screen current;
    private boolean paused;

    private Display() {
        java.awt.Graphics2D g = back.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
        g.dispose();
        backGfx = new Gfx(back.createGraphics(), Screen.WIDTH, Screen.HEIGHT);
    }

    public static Display getDisplay() {
        return INSTANCE;
    }

    public Screen getCurrent() {
        return current;
    }

    /** Makes {@code next} the visible screen. */
    public void setCurrent(Screen next) {
        Screen previous = current;
        if (previous == next) {
            return;
        }
        if (previous != null) {
            previous.setShown(false);
            previous.dispatchHideNotify();
        }
        current = next;
        if (next != null) {
            next.setShown(true);
            next.dispatchShowNotify();
            requestRepaint(next);
        }
    }

    /** Creates and shows the window. Must be called once, from any thread. */
    public void open(String title, Lifecycle callbacks) {
        this.lifecycle = callbacks;
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }
        Runnable build = () -> {
            panel = new CanvasPanel();
            frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    lifecycle.onClose();
                }

                @Override
                public void windowActivated(WindowEvent e) {
                    resume();
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                    pause();
                }

                @Override
                public void windowIconified(WindowEvent e) {
                    pause();
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                    resume();
                }
            });
            frame.setVisible(true);
            panel.requestFocusInWindow();
        };
        invokeAndWait(build);
    }

    /** Renders the current screen and returns a copy of the frame (logical 360x640 pixels). Works without a window. */
    public BufferedImage snapshot() {
        Screen s = current;
        if (s != null) {
            renderBackBuffer(s);
        }
        synchronized (lock) {
            BufferedImage copy = new BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = copy.createGraphics();
            g.drawImage(back, 0, 0, null);
            g.dispose();
            return copy;
        }
    }

    /** Delivers a key press or release to the current screen, as if typed (used for automation and tests). */
    public void sendKey(int keyCode, boolean pressed) {
        Screen s = current;
        if (s == null) {
            return;
        }
        if (pressed) {
            s.dispatchKeyPressed(keyCode);
        } else {
            s.dispatchKeyReleased(keyCode);
        }
    }

    /** Closes the window. */
    public void close() {
        Runnable r = () -> {
            if (frame != null) {
                frame.dispose();
            }
        };
        SwingUtilities.invokeLater(r);
    }

    JFrame frame() {
        return frame;
    }

    private void resume() {
        if (paused) {
            paused = false;
            Screen s = current;
            if (s != null) {
                s.dispatchShowNotify();
            }
            lifecycle.onResume();
        }
    }

    private void pause() {
        if (!paused) {
            paused = true;
            keysDown.clear();
            Screen s = current;
            if (s != null) {
                s.dispatchHideNotify();
            }
            lifecycle.onPause();
        }
    }

    void requestRepaint(Screen s) {
        if (s == current && panel != null) {
            panel.repaint();
        }
    }

    /** Paints {@code s} into the back buffer immediately on the calling thread and schedules a blit. */
    void paintNow(Screen s) {
        if (s == current) {
            renderBackBuffer(s);
            if (panel != null) {
                panel.repaint();
            }
        }
    }

    private void renderBackBuffer(Screen s) {
        synchronized (lock) {
            s.dispatchPaint(backGfx);
        }
    }

    private static void invokeAndWait(Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
            return;
        }
        try {
            SwingUtilities.invokeAndWait(r);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    /** Maps AWT key events to the numeric key codes used by the game (soft keys are -6/-7, d-pad is -1..-5). */
    private static int mapKey(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                return -1;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                return -2;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                return -3;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                return -4;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                return -5;
            case KeyEvent.VK_F1:
            case KeyEvent.VK_Z:
                return -6;
            case KeyEvent.VK_F2:
            case KeyEvent.VK_X:
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_BACK_SPACE:
                return -7;
            case KeyEvent.VK_MULTIPLY:
                return '*';
            case KeyEvent.VK_NUMBER_SIGN:
                return '#';
            default:
                break;
        }
        char c = e.getKeyChar();
        if (c >= '0' && c <= '9' || c == '*' || c == '#') {
            return c;
        }
        return 0;
    }

    private final class CanvasPanel extends JPanel {
        private double scale = 1;
        private int offsetX;
        private int offsetY;

        CanvasPanel() {
            setBackground(Color.BLACK);
            setFocusable(true);
            double screenScale = initialScale();
            setPreferredSize(new Dimension((int) (Screen.WIDTH * screenScale), (int) (Screen.HEIGHT * screenScale)));
            setFocusTraversalKeysEnabled(false);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int code = mapKey(e);
                    Screen s = current;
                    if (code == 0 || s == null) {
                        return;
                    }
                    if (keysDown.add(code)) {
                        s.dispatchKeyPressed(code);
                    } else {
                        s.dispatchKeyRepeated(code);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    int code = mapKey(e);
                    Screen s = current;
                    if (code == 0 || s == null) {
                        return;
                    }
                    if (keysDown.remove(code)) {
                        s.dispatchKeyReleased(code);
                    }
                }
            });
            MouseAdapter mouse = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    requestFocusInWindow();
                    Screen s = current;
                    if (s != null) {
                        s.dispatchPointerPressed(toX(e), toY(e));
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    Screen s = current;
                    if (s != null) {
                        s.dispatchPointerDragged(toX(e), toY(e));
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Screen s = current;
                    if (s != null) {
                        s.dispatchPointerReleased(toX(e), toY(e));
                    }
                }
            };
            addMouseListener(mouse);
            addMouseMotionListener(mouse);
        }

        private double initialScale() {
            if (GraphicsEnvironment.isHeadless()) {
                return 1;
            }
            int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
            double s = Math.floor(screenHeight * 0.85 / Screen.HEIGHT * 2) / 2;
            return Math.max(1, Math.min(3, s));
        }

        private int toX(MouseEvent e) {
            return (int) Math.floor((e.getX() - offsetX) / scale);
        }

        private int toY(MouseEvent e) {
            return (int) Math.floor((e.getY() - offsetY) / scale);
        }

        @Override
        protected void paintComponent(Graphics g0) {
            super.paintComponent(g0);
            Screen s = current;
            if (s != null) {
                renderBackBuffer(s);
            }
            scale = Math.min(getWidth() / (double) Screen.WIDTH, getHeight() / (double) Screen.HEIGHT);
            int w = (int) Math.round(Screen.WIDTH * scale);
            int h = (int) Math.round(Screen.HEIGHT * scale);
            offsetX = (getWidth() - w) / 2;
            offsetY = (getHeight() - h) / 2;
            Graphics2D g = (Graphics2D) g0.create();
            boolean integral = scale == Math.floor(scale);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    integral ? RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
                            : RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            synchronized (lock) {
                g.drawImage(back, offsetX, offsetY, w, h, null);
            }
            g.dispose();
        }
    }
}
