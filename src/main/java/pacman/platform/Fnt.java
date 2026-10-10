package pacman.platform;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/** Font descriptor with the metrics model the game expects (height, baseline, string width). */
public final class Fnt {
    public static final int FACE_SYSTEM = 0;
    public static final int FACE_MONOSPACE = 32;
    public static final int FACE_PROPORTIONAL = 64;
    public static final int STYLE_PLAIN = 0;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_ITALIC = 2;
    public static final int SIZE_MEDIUM = 0;
    public static final int SIZE_SMALL = 8;
    public static final int SIZE_LARGE = 16;

    private static final FontMetricsHolder SCRATCH = new FontMetricsHolder();
    private static Fnt defaultFont;

    private final Font font;
    private final FontMetrics metrics;

    private Fnt(Font font) {
        this.font = font;
        this.metrics = SCRATCH.metricsFor(font);
    }

    public static synchronized Fnt getDefaultFont() {
        if (defaultFont == null) {
            defaultFont = getFont(FACE_SYSTEM, STYLE_PLAIN, SIZE_MEDIUM);
        }
        return defaultFont;
    }

    public static Fnt getFont(int face, int style, int size) {
        String family = face == FACE_MONOSPACE ? Font.MONOSPACED : Font.SANS_SERIF;
        int px = size == SIZE_SMALL ? 12 : size == SIZE_LARGE ? 19 : 15;
        int awtStyle = ((style & STYLE_BOLD) != 0 ? Font.BOLD : 0) | ((style & STYLE_ITALIC) != 0 ? Font.ITALIC : 0);
        return new Fnt(new Font(family, awtStyle, px));
    }

    /** Line height: ascent + descent + leading. */
    public int getHeight() {
        return metrics.getHeight();
    }

    /** Distance from the top of the line to the baseline. */
    public int getBaselinePosition() {
        return metrics.getAscent() + metrics.getLeading();
    }

    public int stringWidth(String s) {
        return metrics.stringWidth(s);
    }

    public int charWidth(char c) {
        return metrics.charWidth(c);
    }

    Font awt() {
        return font;
    }

    private static final class FontMetricsHolder {
        private final Graphics2D g;

        FontMetricsHolder() {
            g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }

        synchronized FontMetrics metricsFor(Font f) {
            return g.getFontMetrics(f);
        }
    }
}
