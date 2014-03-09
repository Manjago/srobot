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

        stopWatch.start("normal");
        List<LamePoint> normalSearch = Finder.find(bigBoard, new SearchPattern(normal));
        stopWatch.stop();
        System.out.println(normalSearch);

//        stopWatch.start("corner");
//        List<LamePoint> cornerSearch = Finder.find(bigBoard, nw);
//        stopWatch.stop();
//        System.out.println(cornerSearch);

        System.out.println(stopWatch.prettyPrint());

    }
}
