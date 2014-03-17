package srobot;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class SimplePoint implements Comparable<SimplePoint> {
    private final int x;
    private final int y;
    public static final SimplePoint ZERO = new SimplePoint(0, 0);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SimplePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SimplePoint nextHor() {
        return new SimplePoint(x + 1, y);
    }

    public SimplePoint nextVer() {
        return new SimplePoint(0, y + 1);
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
    public int compareTo(SimplePoint o) {
        if (o == null) {
            return 1;
        }
        int preCompare = Integer.valueOf(getX()).compareTo(o.getX());
        if (preCompare != 0) {
            return preCompare;
        } else {
            return Integer.valueOf(getY()).compareTo(o.getY());
        }

    }
}
