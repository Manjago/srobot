package srobot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Hello world!
 * <p/>
 * alg0r 68 секунд профессионал
 */
public class App {
    private final Logger logger = LoggerFactory.getLogger(App.class);
    private static final int WAIT_IN_MILLIS = 100;
    private final Bot bot;


    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        new App().run();
    }

    public App() throws AWTException {
        bot = new Bot();
    }

    public void run() throws AWTException, InterruptedException {
        final SimpleRectangle simpleRectangle = WindowFinder.activateAndFind("Сапер");
        if (simpleRectangle == null) {
            logger.warn("no winmine.exe found");
            return;
        }

        boolean resume = true;
        while (resume) {
            resume = mainLoop(simpleRectangle);
        }

    }

    private boolean mainLoop(SimpleRectangle simpleRectangle) throws AWTException, InterruptedException {
        BufferedImage image = bot.createScreenCapture(simpleRectangle);

        Board board = new Board(simpleRectangle.getLeftCorner(), image);

        BoardState boardState = board.getState();

        if (boardState == null) {
            logger.info("LOST board");
            return false;
        }

        switch (boardState) {
            case NORMAL:
                break;
            case FAIL:
                logger.info("FAIL");
                return false;
            case OK:
                logger.info("WIN");
                return false;
            case WAIT:
                Thread.sleep(WAIT_IN_MILLIS);
                return true;
            default:
                logger.error("unknown state");
                return false;
        }


        Cells cells = board.resolve();

        Solver solver = new StupidSolver();

        SimplePoint turn = solver.turn(cells);

        if (turn != null) {
            SimplePoint click = board.recode(turn);

            if (click != null) {
                bot.cellClick(click);
            }
        }

        return true;
    }

}
