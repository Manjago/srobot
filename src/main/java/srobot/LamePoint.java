package srobot;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class LamePoint {
    private final int x;
    private final int y;

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

    public LamePoint nextHor(){
        return new LamePoint(x + 1, y);
    }

    public LamePoint nextVer(){
        return new LamePoint(0, y + 1);
    }

    @Override
    public String toString() {
        return "LamePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
