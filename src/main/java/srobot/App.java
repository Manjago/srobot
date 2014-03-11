package srobot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 * alg0r 68 секунд профессионал
 */
public final class App
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private App() {
    }

    public static void main( String[] args ) throws AWTException, IOException {
        Robot robot = new Robot();
        final SimpleRectangle simpleRectangle = WindowFinder.activateAndFind("Сапер");
        if (simpleRectangle == null){
            logger.warn("no winmine.exe found");
            return;
        }

        Rectangle area = simpleRectangle.toRectangle();
        BufferedImage image = robot.createScreenCapture(area);

        Board board = new Board(simpleRectangle.getLeftCorner(), image);
        Cells cells = board.resolve();

        Solver solver = new StupidSolver();

        SimplePoint turn = solver.turn(cells);

        SimplePoint click = board.recode(turn);

        if (turn != null){
            robot.mouseMove(click.getX() + 8, click.getY() + 8);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }



    }
}
