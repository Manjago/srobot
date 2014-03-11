package srobot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Hello world!
 * <p/>
 * alg0r 68 секунд профессионал
 */
public final class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private App() {
    }

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        Robot robot = new Robot();
        final SimpleRectangle simpleRectangle = WindowFinder.activateAndFind("Сапер");
        if (simpleRectangle == null) {
            logger.warn("no winmine.exe found");
            return;
        }

        Rectangle area = simpleRectangle.toRectangle();

        while (true) {
            BufferedImage image = robot.createScreenCapture(area);

            Board board = new Board(simpleRectangle.getLeftCorner(), image);

            BoardState boardState = board.getState();

            if (boardState == null) {
                System.out.println("LOST board");
                break;
            }

            boolean isNormal;

            switch (boardState) {
                case NORMAL:
                    isNormal = true;
                    break;
                case FAIL:
                    isNormal = false;
                    System.out.println("FAIL");
                    break;
                case OK:
                    isNormal = false;
                    System.out.println("WIN");
                    break;
                case WAIT:
                    Thread.sleep(100);
                    continue;
                default:
                    isNormal = false;
                    System.out.println("unknown state");
                    break;
            }


            if (!isNormal){
                break;
            }

            Cells cells = board.resolve();

            Solver solver = new StupidSolver();

            SimplePoint turn = solver.turn(cells);

            SimplePoint click = board.recode(turn);

            if (turn != null) {
                robot.mouseMove(click.getX() + 8, click.getY() + 8);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }


        }


    }
}
