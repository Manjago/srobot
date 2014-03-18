package srobot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solver.StupidSolver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Hello world!
 * <p/>
 * alg0r 68 секунд профессионал
 */
public class App {
    private static final int MAX_RETRY_COUNT = 10;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final int WAIT_IN_MILLIS = 100;
    private final Bot bot;
    private Solver solver = new StupidSolver();


    private static final class TaskResult {
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

    public void run() throws AWTException, InterruptedException {

        SimpleRectangle simpleRectangle = null;

        TaskResult resume = new TaskResult(TaskResultEnum.HARDRESUME);
        do {

            if (resume.taskResultEnum == TaskResultEnum.HARDRESUME) {
                simpleRectangle = WindowFinder.activateAndFind("Сапер");
                if (simpleRectangle == null) {
                    logger.warn("no winmine.exe found");
                    return;
                }

                if (resume.simplePoint != null) {
                    bot.cellClick(resume.simplePoint, simpleRectangle.getLeftCorner());
                }

            }

            resume = mainLoop(simpleRectangle);
        } while (resume != null && !TaskResultEnum.BREAK.equals(resume.taskResultEnum));


    }

    private TaskResult mainLoop(SimpleRectangle simpleRectangle) throws AWTException, InterruptedException {

        Board board;
        BoardState boardState;
        Cells cells = null;

        int retryCount = MAX_RETRY_COUNT;
        do {

            BufferedImage image = bot.createScreenCapture(simpleRectangle);

            board = new Board(simpleRectangle.getLeftCorner(), image);

            boardState = board.getState();

            if (boardState == null) {
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

                if (cells == null) {
                    --retryCount;
                    logger.debug("board not full drawed, retry {}", retryCount);
                    Thread.sleep(WAIT_IN_MILLIS);
                } else {
                    break;
                }

            }


        } while (retryCount >= 0);


        if (boardState == null) {
            logger.error("BOARD LOST");
            return new TaskResult(TaskResultEnum.BREAK);
        }

        if (cells == null) {
            logger.error("BOARD NOT PARSED");
            return new TaskResult(TaskResultEnum.BREAK);
        }

        solveTurn(board, cells);

        return new TaskResult(TaskResultEnum.SOFTRESUME);
    }

    private void solveTurn(Board board, Cells cells) {

        Prediction prediction = solver.predict(cells);
        if (prediction != null && prediction.getSimplePoint() != null) {
            SimplePoint click = board.recode(prediction.getSimplePoint());

            if (click != null) {
                bot.cellClick(click, board.getResolvedLeftCorner(),
                        Prediction.PredictionType.MINE.equals(prediction.getPredictionType()) ?
                                Bot.MouseButton.RIGHT :
                                Bot.MouseButton.LEFT
                );
            } else {
                throw new AppException(String.format("bad click"));
            }

        } else {
            throw new AppException(String.format("bad prediction %s", String.valueOf(prediction)));
        }

    }

}
