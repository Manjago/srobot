package srobot;

import java.awt.image.BufferedImage;

public class SearchPattern {
    private final BufferedImage pattern;
    private final SimplePoint transparent;

    public SearchPattern(BufferedImage pattern) {
        this(pattern, SimplePoint.ZERO);
    }

    public SearchPattern(BufferedImage pattern, SimplePoint transparent) {
        if (pattern == null || pattern.getHeight() < 1 || pattern.getWidth() < 1 ) {
            throw new IllegalArgumentException();
        }
        this.pattern = pattern;
        this.transparent = transparent;
    }

    private int getTransparentRGB() {
        return pattern.getRGB(transparent.getX(), transparent.getY());
    }

    public int getHeight() {
        return pattern.getHeight();
    }

    public int getWidth() {
        return pattern.getWidth();
    }

    public int getRGB(SimplePoint point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }
        return pattern.getRGB(point.getX(), point.getY());
    }

    public boolean isGood(int rgb, SimplePoint point) {
        int testRGB = getRGB(point);
        return transparent != null ? testRGB == getTransparentRGB() || testRGB == rgb : testRGB == rgb;
    }

    @Override
    public String toString() {
        return "SearchPattern{" +
                "pattern=" + pattern +
                ", transparent=" + transparent +
                '}';
    }
}
