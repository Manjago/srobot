package srobot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        ImageIO.write(image, "BMP", new File("c:/temp/screenShot.bmp"));

        Color c = new Color(101,201,155,255);
        int rgb = c.getRGB();

        for(int i = 0; i < image.getWidth(); ++i){
            for (int j =0; j < image.getHeight(); ++j){
                int a = image.getRGB(i, j);
                if (a == rgb){
                    System.out.println("catched " + i + " " + j);
                } else
                {
                  //  System.out.println("NON catched " + i + " " + j + " " + Integer.toHexString(a));
                }
            }
        }

    }
}
