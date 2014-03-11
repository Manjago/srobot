package srobot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 * alg0r 68 секунд профессионал
 */
public class App 
{
    public static void main( String[] args ) throws AWTException, IOException {
        Robot robot = new Robot();
        final SimpleRectangle simpleRectangle = WindowFinder.activateAndFind("Сапер");
        if (simpleRectangle == null){
            System.out.println("no sapper");
            return;
        }

        Rectangle area = simpleRectangle.toRectangle();
        BufferedImage image = robot.createScreenCapture(area);
        ImageIO.write(image, "png", new File("c:/temp/screenShot.png"));

        Finder finder = new BruteFinder();
        SimplePoint normal = finder.findOne(image, new SearchPattern(Loader.load("normal.png")));
        System.out.println(normal);

        List<SimplePoint> closed = finder.find(image, new SearchPattern(Loader.load("closed.png")));
        System.out.printf("closed %d\n%s\n\n", closed.size(), closed);

        List<SimplePoint> opened = finder.find(image, new SearchPattern(Loader.load("opened.png")));
        System.out.printf("opened %d\n%s\n" +
                "\n", opened.size(), opened);

        List<SimplePoint> e1 = finder.find(image, new SearchPattern(Loader.load("1.png")));
        System.out.printf("1 %d\n%s\n" +
                "\n", e1.size(), e1);

        List<SimplePoint> e2 = finder.find(image, new SearchPattern(Loader.load("2.png")));
        System.out.printf("2 %d\n%s\n" +
                "\n", e2.size(), e2);

    }
}
