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
public final class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final int WAIT_IN_MILLIS = 100;

    private App() {
    }

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        final SimpleRectangle simpleRectangle = WindowFinder.activateAndFind("Сапер");
        if (simpleRectangle == null) {
            LOGGER.warn("no winmine.exe found");
            return;
        }

        Bot bot = new Bot();

        boolean resume = true;
        while (resume) {
            resume = mainLoop(bot, simpleRectangle);
        }

    }

    private static boolean mainLoop(Bot bot, SimpleRectangle simpleRectangle) throws AWTException, InterruptedException {
        BufferedImage image = bot.createScreenCapture(simpleRectangle);

        Board board = new Board(simpleRectangle.getLeftCorner(), image);

        BoardState boardState = board.getState();

        if (boardState == null) {
            LOGGER.info("LOST board");
            return false;
        }

        switch (boardState) {
            case NORMAL:
                break;
            case FAIL:
                LOGGER.info("FAIL");
                return false;
            case OK:
                LOGGER.info("WIN");
                return false;
            case WAIT:
                Thread.sleep(WAIT_IN_MILLIS);
                return true;
            default:
                LOGGER.error("unknown state");
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
