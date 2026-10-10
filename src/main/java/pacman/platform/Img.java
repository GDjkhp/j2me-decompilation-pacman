package pacman.platform;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/** Off-screen image: a thin wrapper around {@link BufferedImage} offering the handful of operations the game needs. */
public final class Img {
    private final BufferedImage image;
    private final boolean mutable;

    private Img(BufferedImage image, boolean mutable) {
        this.image = image;
        this.mutable = mutable;
    }

    /** Loads an image from the classpath, e.g. {@code "/menu_top.png"}. */
    public static Img createImage(String resource) throws IOException {
        try (InputStream in = Img.class.getResourceAsStream(resource)) {
            if (in == null) {
                throw new IOException("Resource not found: " + resource);
            }
            return decode(in);
        }
    }

    /** Decodes an image from encoded (PNG/GIF/JPEG) bytes. */
    public static Img createImage(byte[] data, int offset, int length) throws IOException {
        return decode(new ByteArrayInputStream(data, offset, length));
    }

    /** Creates a mutable image filled with opaque white. */
    public static Img createImage(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return new Img(bi, true);
    }

    /** Creates an immutable image from raw ARGB (or RGB if {@code processAlpha} is false) pixels. */
    public static Img createRGBImage(int[] rgb, int width, int height, boolean processAlpha) {
        BufferedImage bi = new BufferedImage(width, height,
                processAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        bi.setRGB(0, 0, width, height, rgb, 0, width);
        return new Img(bi, false);
    }

    private static Img decode(InputStream in) throws IOException {
        BufferedImage src = ImageIO.read(in);
        if (src == null) {
            throw new IOException("Unsupported image format");
        }
        // Normalise to ARGB so drawing and pixel access behave consistently.
        BufferedImage argb = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = argb.createGraphics();
        g.drawImage(src, 0, 0, null);
        g.dispose();
        return new Img(argb, false);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public boolean isMutable() {
        return mutable;
    }

    /** Returns a graphics context for drawing into this (mutable) image. */
    public Gfx getGraphics() {
        if (!mutable) {
            throw new IllegalStateException("Image is immutable");
        }
        return new Gfx(image.createGraphics(), image.getWidth(), image.getHeight());
    }

    /** Copies ARGB pixels of a rectangle into {@code rgb}. */
    public void getRGB(int[] rgb, int offset, int scanlength, int x, int y, int width, int height) {
        image.getRGB(x, y, width, height, rgb, offset, scanlength);
    }

    BufferedImage awt() {
        return image;
    }
}
