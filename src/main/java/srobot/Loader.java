package srobot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public final class Loader {

    private Loader() {
    }

    public static BufferedImage load(String name) throws IOException {
        URL url = Loader.class.getResource(name);

        if (url == null) {
            return null;
        }

        return ImageIO.read(url);
    }

}
