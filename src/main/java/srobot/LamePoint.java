package srobot;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class LamePoint implements Comparable<LamePoint> {
    private final int x;
    private final int y;
    public static final LamePoint ZERO = new LamePoint(0, 0);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LamePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public LamePoint nextHor() {
        return new LamePoint(x + 1, y);
    }

    public LamePoint nextVer() {
        return new LamePoint(0, y + 1);
    }

    public LamePoint add(LamePoint p) {
        if (p == null) {
            return this;
        }
        return new LamePoint(x + p.getX(), y + p.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LamePoint lamePoint = (LamePoint) o;

        if (x != lamePoint.x) {
            return false;
        }
        if (y != lamePoint.y) {
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
        return "LamePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(LamePoint o) {
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
