package srobot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Board {
    public static void main(String[] args) throws IOException {
        StopWatch stopWatch = new StopWatch("Find");
        stopWatch.start("load");
        BufferedImage bigBoard  = Loader.load("bigBoard.png");

        stopWatch.stop();

        stopWatch.start("init");
        boolean result =  new Board().init(bigBoard);
        stopWatch.stop();
        System.out.println(result);

        System.out.println(stopWatch.prettyPrint());

    }

    public boolean init(BufferedImage image) throws IOException {

        Finder finder = new BruteFinder();

        SimplePoint nw = finder.findOne(image, new SearchPattern(Loader.load("nw.png"), null));
        if (nw == null){
            return false;
        }

        BufferedImage step_1 = image.getSubimage(nw.getX(), nw.getY(), image.getWidth() - nw.getX(), image.getHeight() - nw.getY());

        ImageIO.write(step_1, "PNG", new File("c:/temp/step_1.png"));

        final BufferedImage neImage = Loader.load("ne.png");
        SimplePoint ne = finder.findOne(step_1, new SearchPattern(neImage, null));
        if (ne == null){
            return false;
        }

        BufferedImage step_2 = step_1.getSubimage(0, 0, ne.getX() + neImage.getWidth(), step_1.getHeight());
        ImageIO.write(step_2, "PNG", new File("c:/temp/step_2.png"));


        return step_2 != null;
    }
}
