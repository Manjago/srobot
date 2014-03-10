package srobot;

import sun.plugin2.gluegen.runtime.BufferFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Board {

    private final Finder finder = new BruteFinder();

    public static void main(String[] args) throws IOException {
        StopWatch stopWatch = new StopWatch("Find");
        stopWatch.start("load");
        BufferedImage bigBoard  = Loader.load("bigGotcha.png");

        stopWatch.stop();

        stopWatch.start("init");
        final Board board = new Board();
        BufferedImage boardImage =  board.catchFromScratch(bigBoard);
        stopWatch.stop();

        System.out.println(boardImage);

        //stopWatch.start("analyze");
        //board.analyze(boardImage);
        //stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

    }

    public BufferedImage catchFromScratch(BufferedImage image) throws IOException {

        SimplePoint nw = finder.findOne(image, new SearchPattern(Loader.load("nw.png"), null));
        if (nw == null){
            return null;
        }

        BufferedImage step_1 = image.getSubimage(nw.getX(), nw.getY(), image.getWidth() - nw.getX(), image.getHeight() - nw.getY());

        ImageIO.write(step_1, "PNG", new File("c:/temp/step_1.png"));

        final BufferedImage neImage = Loader.load("ne.png");
        SimplePoint ne = finder.findOne(step_1, new SearchPattern(neImage, null));
        if (ne == null){
            return null;
        }

        BufferedImage step_2 = step_1.getSubimage(0, 0, ne.getX() + neImage.getWidth(), step_1.getHeight());
        ImageIO.write(step_2, "PNG", new File("c:/temp/step_2.png"));

        final BufferedImage swpng = Loader.load("sw.png");
        List<SimplePoint> sw = finder.find(step_2, new SearchPattern(swpng, null), 2);

        if (sw.size() != 2){
            return null;
        }

        Collections.sort(sw);
        SimplePoint sw1 = sw.get(0);
        SimplePoint sw2 = sw.get(1);

        BufferedImage step_3_1 = step_2.getSubimage(0, 0, step_2.getWidth(), sw1.getY() + swpng.getHeight());
        ImageIO.write(step_3_1, "PNG", new File("c:/temp/step_3_1.png"));

        BufferedImage step_3_2 = step_2.getSubimage(0, sw1.getY() + swpng.getHeight(), step_2.getWidth(), sw2.getY() - sw1.getY());
        ImageIO.write(step_3_2, "PNG", new File("c:/temp/step_3_2.png"));

        BufferedImage result = step_3_2.getSubimage(9, 8, step_3_2.getWidth() - 9 - 3, step_3_2.getHeight() - 8 - 4);
        ImageIO.write(result, "PNG", new File("c:/temp/result.png"));

        return result;
    }

    void analyze(BufferedImage board) throws IOException {
        List<SimplePoint> n1 = finder.find(board, new SearchPattern(Loader.load("1.png")));
        System.out.println("1 " + n1.size());
    }
}
