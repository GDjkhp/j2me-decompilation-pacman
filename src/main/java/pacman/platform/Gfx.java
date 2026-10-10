package pacman.platform;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * 2D drawing surface with an integer-pixel, anchor-based API (origin at top-left, +y down).
 * Clip and translate behave like the original MIDP model: the clip is expressed in translated coordinates
 * and {@code setClip} replaces the previous clip.
 */
public final class Gfx {
    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    public static final int BASELINE = 64;

    private final Graphics2D g;
    private int color;
    private int translateX;
    private int translateY;
    private Fnt font = Fnt.getDefaultFont();

    Gfx(Graphics2D g, int width, int height) {
        this.g = g;
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setClip(0, 0, width, height);
        g.setFont(font.awt());
        setColor(0);
    }

    public void setColor(int rgb) {
        color = rgb & 0xFFFFFF;
        g.setColor(new Color(color));
    }

    public void setColor(int r, int gr, int b) {
        setColor((r & 0xFF) << 16 | (gr & 0xFF) << 8 | (b & 0xFF));
    }

    public int getColor() {
        return color;
    }

    public void setFont(Fnt f) {
        font = f;
        g.setFont(f.awt());
    }

    public Fnt getFont() {
        return font;
    }

    public void translate(int x, int y) {
        translateX += x;
        translateY += y;
        g.translate(x, y);
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void setClip(int x, int y, int w, int h) {
        g.setClip(x, y, w, h);
    }

    public void clipRect(int x, int y, int w, int h) {
        g.clipRect(x, y, w, h);
    }

    public int getClipX() {
        return g.getClipBounds().x;
    }

    public int getClipY() {
        return g.getClipBounds().y;
    }

    public int getClipWidth() {
        return g.getClipBounds().width;
    }

    public int getClipHeight() {
        return g.getClipBounds().height;
    }

    public void fillRect(int x, int y, int w, int h) {
        g.fillRect(x, y, w, h);
    }

    public void drawRect(int x, int y, int w, int h) {
        g.drawRect(x, y, w, h);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
        g.drawArc(x, y, w, h, startAngle, arcAngle);
    }

    public void drawImage(Img img, int x, int y, int anchor) {
        int w = img.getWidth();
        int h = img.getHeight();
        g.drawImage(img.awt(), alignX(x, w, anchor), alignY(y, h, anchor), null);
    }

    /** Draws a sub-rectangle of {@code img}. Only the identity transform (0) is supported. */
    public void drawRegion(Img img, int sx, int sy, int w, int h, int transform, int dx, int dy, int anchor) {
        if (transform != 0) {
            throw new IllegalArgumentException("Unsupported image transform: " + transform);
        }
        int x = alignX(dx, w, anchor);
        int y = alignY(dy, h, anchor);
        g.drawImage(img.awt(), x, y, x + w, y + h, sx, sy, sx + w, sy + h, null);
    }

    public void drawString(String s, int x, int y, int anchor) {
        Fnt f = font;
        int w = f.stringWidth(s);
        int px = alignX(x, w, anchor);
        int py;
        if ((anchor & BASELINE) != 0) {
            py = y;
        } else if ((anchor & BOTTOM) != 0) {
            py = y - (f.getHeight() - f.getBaselinePosition());
        } else {
            py = y + f.getBaselinePosition();
        }
        g.drawString(s, px, py);
    }

    public void drawChar(char c, int x, int y, int anchor) {
        drawString(String.valueOf(c), x, y, anchor);
    }

    private static int alignX(int x, int w, int anchor) {
        if ((anchor & HCENTER) != 0) {
            return x - (w >> 1);
        }
        if ((anchor & RIGHT) != 0) {
            return x - w;
        }
        return x;
    }

    private static int alignY(int y, int h, int anchor) {
        if ((anchor & VCENTER) != 0) {
            return y - (h >> 1);
        }
        if ((anchor & BOTTOM) != 0) {
            return y - h;
        }
        return y;
    }

    /** Releases native resources; the context must not be used afterwards. */
    public void dispose() {
        g.dispose();
    }
}
