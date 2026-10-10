package pacman;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import pacman.platform.Display;

/** Developer tool: runs the game headlessly, optionally pressing keys, and writes screenshots. */
public final class Harness {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "true");
        File out = new File(args.length > 0 ? args[0] : "/tmp/shots");
        out.mkdirs();
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.err.println("UNCAUGHT in " + t.getName());
            e.printStackTrace();
        });
        Pacman app = new Pacman();
        app.startApp();
        Display d = Display.getDisplay();
        // script: "wait:ms", "key:code", "shot:name"
        for (int i = 1; i < args.length; i++) {
            String[] p = args[i].split(":", 2);
            switch (p[0]) {
                case "wait" -> Thread.sleep(Long.parseLong(p[1]));
                case "key" -> {
                    int code = Integer.parseInt(p[1]);
                    d.sendKey(code, true);
                    Thread.sleep(80);
                    d.sendKey(code, false);
                }
                case "shot" -> {
                    BufferedImage img = d.snapshot();
                    ImageIO.write(img, "png", new File(out, p[1] + ".png"));
                }
                default -> throw new IllegalArgumentException(args[i]);
            }
        }
        System.exit(0);
    }
}
