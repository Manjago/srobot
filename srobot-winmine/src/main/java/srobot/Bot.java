package srobot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class Bot {

    public enum MouseButton{
        LEFT(InputEvent.BUTTON1_MASK), RIGHT(InputEvent.BUTTON3_MASK);

        MouseButton(int buttonMask) {
            this.buttonMask = buttonMask;
        }

        public int getButtonMask() {
            return buttonMask;
        }

        private final int buttonMask;
    }

    private static final int HALF_CELL = 8;
    private final Robot robot;

    public Bot() throws AWTException {
        robot = new Robot();
    }

    public BufferedImage createScreenCapture(SimpleRectangle rect){
        return robot.createScreenCapture(rect.toRectangle());
    }

    public void cellClick(SimplePoint point, SimplePoint resume){
        click(point, HALF_CELL, resume, MouseButton.LEFT);
    }

    public void cellClick(SimplePoint point, SimplePoint resume, MouseButton mouseButton){
        click(point, HALF_CELL, resume, mouseButton != null ? mouseButton : MouseButton.LEFT);
    }

    private void click(SimplePoint point, int shift, SimplePoint resume, MouseButton mouseButton){
        if (point == null || resume == null){
            throw new IllegalArgumentException();
        }
        robot.mouseMove(point.getX() + shift, point.getY() + shift);
        robot.mousePress(mouseButton.getButtonMask());
        robot.mouseRelease(mouseButton.getButtonMask());

        robot.mouseMove(resume.getX(), resume.getY());
    }
}
