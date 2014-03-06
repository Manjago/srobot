package srobot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Loader {

    public static BufferedImage load(String name) throws IOException {
        URL url = Loader.class.getResource("1.png");

        if (url == null){
            return null;
        }

        return ImageIO.read(url);
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = load("1.png");
        Color tr = new Color(255, 255, 255);
        System.out.printf("%s %d\n", tr, tr.getRGB());


        for(int i = 0; i < image.getWidth(); ++i){
            for (int j =0; j < image.getHeight(); ++j){
                System.out.printf("i = %d, j = %d, rgb = %d,  color= %s %s\n", i, j, image.getRGB(i, j), new Color(image.getRGB(i, j)));
            }
        }
    }
}
