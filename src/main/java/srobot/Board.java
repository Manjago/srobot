package srobot;

import java.awt.image.BufferedImage;
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

        BufferedImage normal = Loader.load("normal.png");
        BufferedImage nw = Loader.load("NW.png");
        stopWatch.stop();

        stopWatch.start("NW");
        List<SimplePoint> nwSearch = new BruteFinder().find(bigBoard, new SearchPattern(nw), 1);
        stopWatch.stop();
        System.out.println(nwSearch);

        stopWatch.start("normal");
        List<SimplePoint> normalSearch = new BruteFinder().find(bigBoard, new SearchPattern(normal), 1);
        stopWatch.stop();
        System.out.println(normalSearch);

        System.out.println(stopWatch.prettyPrint());

    }
}
