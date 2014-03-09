package srobot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BruteFinder implements Finder {
    @Override
    public List<SimplePoint> find(BufferedImage searchBase, SearchPattern pattern) {

        check("searchBase", searchBase);
        if (pattern == null) {
            throw new IllegalArgumentException();
        }

        List<SimplePoint> result = new ArrayList<>();
        for (int j = 0; j <= searchBase.getHeight() - pattern.getHeight(); ++j) {
            bigLoop:
            for (int i = 0; i <= searchBase.getWidth() - pattern.getWidth(); ++i){
                int rgb = searchBase.getRGB(i, j);
                for(int jShift = 0; jShift < pattern.getHeight(); ++jShift){
                    for (int iShift = 0; iShift < pattern.getWidth(); ++iShift){
                        if (!pattern.isGood(rgb, new SimplePoint(iShift, jShift))){
                            break bigLoop;
                        }
                    }
                }
                result.add(new SimplePoint(i, j));
            }
        }



        return result;
    }

    private static void check(String msg, BufferedImage img) {
        if (img == null || img.getHeight() < 1 || img.getWidth() < 1) {
            throw new IllegalArgumentException(msg);
        }
    }
}
