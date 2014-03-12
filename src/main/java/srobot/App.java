package srobot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 * <p/>
 * alg0r 68 секунд профессионал
 */
public class App {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final int WAIT_IN_MILLIS = 100;
    private final Bot bot;

    private class TaskResult{
        private final TaskResultEnum taskResultEnum;
        private final SimplePoint simplePoint;

        private TaskResult(TaskResultEnum taskResultEnum) {
            this.taskResultEnum = taskResultEnum;
            simplePoint = null;
        }

        private TaskResult(TaskResultEnum taskResultEnum, SimplePoint simplePoint) {
            this.taskResultEnum = taskResultEnum;
            this.simplePoint = simplePoint;
        }
    }

    enum TaskResultEnum {
        BREAK, SOFTRESUME, HARDRESUME
    }

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        new App().run();
    }

    public App() throws AWTException {
        bot = new Bot();
    }

    public void run() throws AWTException, InterruptedException, IOException {

        SimpleRectangle simpleRectangle = null;

        TaskResult resume = new TaskResult(TaskResultEnum.HARDRESUME);
        do {

            if (resume.taskResultEnum == TaskResultEnum.HARDRESUME){
                simpleRectangle = WindowFinder.activateAndFind("Сапер");
                if (simpleRectangle == null) {
                    logger.warn("no winmine.exe found");
                    return;
                }

                if (resume.simplePoint != null){
                    bot.cellClick(resume.simplePoint);
                }

            }

            resume = mainLoop(simpleRectangle);
        }while(resume != null && !TaskResultEnum.BREAK.equals(resume.taskResultEnum));


    }

    private TaskResult mainLoop(SimpleRectangle simpleRectangle) throws AWTException, InterruptedException, IOException {


        Board board;
        BoardState boardState;
        Cells cells = null;

        int retryCount = 10;
        do {

            BufferedImage image = bot.createScreenCapture(simpleRectangle);

            ImageIO.write(image, "PNG", new File("c:/tmp/screen.png"));

            board = new Board(simpleRectangle.getLeftCorner(), image);

            boardState = board.getState();

            if (boardState == null){
                --retryCount;
                logger.debug("board not found, retry {}", retryCount);
                Thread.sleep(WAIT_IN_MILLIS);
            } else {
                switch (boardState) {
                    case NORMAL:
                        break;
                    case FAIL:
                        logger.info("FAIL");
                        return new TaskResult(TaskResultEnum.HARDRESUME, board.getCurrentStateAbsPos());
                    case OK:
                        logger.info("WIN");
                        return new TaskResult(TaskResultEnum.HARDRESUME, board.getCurrentStateAbsPos());
                    case WAIT:
                        Thread.sleep(WAIT_IN_MILLIS);
                        return new TaskResult(TaskResultEnum.SOFTRESUME);
                    default:
                        logger.error("unknown state");
                        return new TaskResult(TaskResultEnum.BREAK);
                }


                cells = board.resolve();

                if (cells == null){
                    --retryCount;
                    logger.debug("board not full drawed, retry {}", retryCount);
                    Thread.sleep(WAIT_IN_MILLIS);
                } else {
                    break;
                }

            }


        } while (retryCount >= 0);


        if (boardState == null){
            logger.error("BOARD LOST");
            return new TaskResult(TaskResultEnum.BREAK);
        }

        if (cells == null){
            logger.error("BOARD NOT PARSED");
            return new TaskResult(TaskResultEnum.BREAK);
        }

        Solver solver = new StupidSolver();

        SimplePoint turn = solver.turn(cells);

        if (turn != null) {
            SimplePoint click = board.recode(turn);

            if (click != null) {
                bot.cellClick(click);
            }
        }

        return new TaskResult(TaskResultEnum.SOFTRESUME);
    }

}
