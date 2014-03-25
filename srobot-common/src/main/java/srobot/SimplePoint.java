package srobot;


import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class SimplePoint implements Comparable<SimplePoint> {
    public static final SimplePoint ZERO = new SimplePoint(0, 0);
    private final int x;
    private final int y;

    public SimplePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SimplePoint add(SimplePoint p) {
        if (p == null) {
            return this;
        }
        return new SimplePoint(x + p.getX(), y + p.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SimplePoint simplePoint = (SimplePoint) o;

        if (x != simplePoint.x) {
            return false;
        }
        if (y != simplePoint.y) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        final int prime = 31;
        result = prime * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "SimplePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(@Nonnull SimplePoint o) {
        Objects.requireNonNull(o, "SimplePoint == null");
        int preCompare = Integer.valueOf(getX()).compareTo(o.getX());
        if (preCompare != 0) {
            return preCompare;
        } else {
            return Integer.valueOf(getY()).compareTo(o.getY());
        }

    }

    public String strKey() {
        return String.format("(%S,%d)", x, y);
    }
}
