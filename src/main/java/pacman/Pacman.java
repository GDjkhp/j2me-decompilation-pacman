package pacman;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import pacman.platform.Display;

/** Application host: owns the game canvas, reads the application properties and wires the window life-cycle. */
public class Pacman {
    static GameScreen field_0 = null;

    private final Properties properties = new Properties();

    public Pacman() {
        try (InputStream in = Pacman.class.getResourceAsStream("/app.properties")) {
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read app.properties", e);
        }
        field_0 = new GameScreen(this);
        Display.getDisplay().setCurrent(field_0);
        Thread gameThread = new Thread(field_0, "pacman-game");
        gameThread.setDaemon(true);
        gameThread.start();
    }

    /** Looks up an application property; returns null when it is not defined. */
    public String getAppProperty(String key) {
        return properties.getProperty(key);
    }

    /** Opens a URL in the system browser, if one is available. */
    public void platformRequest(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(URI.create(url));
            }
        } catch (IOException | RuntimeException e) {
            // No browser available: ignore, matching the original "connection not found" behaviour.
        }
    }

    /** Terminates the application. */
    public void notifyDestroyed() {
        Display.getDisplay().close();
        System.exit(0);
    }

    public void startApp() {
        field_0.method_141();
    }

    public void pauseApp() {
        if (field_0 != null) {
            field_0.method_142();
        }
    }

    public void destroyApp() {
        if (field_0 != null) {
            field_0.method_143();
        }
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", System.getProperty("sun.java2d.opengl", "false"));
        Pacman app = new Pacman();
        Display.getDisplay().open(app.getAppProperty("MIDlet-Name"), new Display.Lifecycle() {
            @Override
            public void onResume() {
                app.startApp();
            }

            @Override
            public void onPause() {
                app.pauseApp();
            }

            @Override
            public void onClose() {
                app.destroyApp();
                app.notifyDestroyed();
            }
        });
        app.startApp();
    }
}
