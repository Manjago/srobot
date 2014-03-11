package srobot;

import java.awt.*;

public class SimpleRectangle {
    private final SimplePoint leftCorner;
    private final int width;
    private final int height;

    public SimpleRectangle(SimplePoint leftCorner, int width, int height) {
        this.leftCorner = leftCorner;
        this.width = width;
        this.height = height;
    }

    public SimplePoint getLeftCorner() {
        return leftCorner;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle toRectangle(){
        return new Rectangle(leftCorner.getX(), leftCorner.getY(), width, height);
    }

    @Override
    public String toString() {
        return "SimpleRectangle{" +
                "leftCorner=" + leftCorner +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
