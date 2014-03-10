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
        Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(area);
        ImageIO.write(image, "png", new File("c:/temp/screenShot.png"));

        Finder finder = new BruteFinder();
        SimplePoint normal = finder.findOne(image, new SearchPattern(Loader.load("normal.png")));
        System.out.println(normal);

        List<SimplePoint> e = finder.find(image, new SearchPattern(Loader.load("empty.png")));
        System.out.println(e.size());
    }
}
