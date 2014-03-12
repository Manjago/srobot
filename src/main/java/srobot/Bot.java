package srobot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class Bot {

    private static final int HALF_CELL = 8;
    private final Robot robot;

    public Bot() throws AWTException {
        robot = new Robot();
    }

    public BufferedImage createScreenCapture(SimpleRectangle rect){
        return robot.createScreenCapture(rect.toRectangle());
    }

    public void cellClick(SimplePoint point){
        if (point == null){
            throw new IllegalArgumentException();
        }
        robot.mouseMove(point.getX() + HALF_CELL, point.getY() + HALF_CELL);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
