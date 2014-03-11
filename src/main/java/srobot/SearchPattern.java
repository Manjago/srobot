package srobot;

import java.awt.image.BufferedImage;

public class SearchPattern {
    private final BufferedImage pattern;

    public SearchPattern(BufferedImage pattern) {
        if (pattern == null || pattern.getHeight() < 1 || pattern.getWidth() < 1 ) {
            throw new IllegalArgumentException();
        }
        this.pattern = pattern;
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
        return getRGB(point) == rgb;
    }

    @Override
    public String toString() {
        return "SearchPattern{" +
                "pattern=" + pattern +
                '}';
    }
}
