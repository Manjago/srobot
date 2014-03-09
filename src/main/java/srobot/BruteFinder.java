package srobot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BruteFinder implements Finder {


    @Override
    public List<SimplePoint> find(BufferedImage searchBase, SearchPattern pattern) {
        return find(searchBase, pattern, 0);
    }

    @Override
    public List<SimplePoint> find(BufferedImage searchBase, SearchPattern pattern, int depth) {

        check("searchBase", searchBase);
        if (pattern == null) {
            throw new IllegalArgumentException();
        }

        List<SimplePoint> result = new ArrayList<>();
        for (int j = 0; j <= searchBase.getHeight() - pattern.getHeight(); ++j) {
            for (int i = 0; i <= searchBase.getWidth() - pattern.getWidth(); ++i){
                test(searchBase, pattern, result, j, i);
                if (depth != 0 && result.size() >= depth){
                    return result;
                }
            }
        }

        return result;
    }

    private static void test(BufferedImage searchBase, SearchPattern pattern, List<SimplePoint> result, int j, int i) {
        for(int jShift = 0; jShift < pattern.getHeight(); ++jShift){
            for (int iShift = 0; iShift < pattern.getWidth(); ++iShift){
                int rgb = searchBase.getRGB(i + iShift, j + jShift);
                if (!pattern.isGood(rgb, new SimplePoint(iShift, jShift))){
                    return;
                }
            }
        }
        result.add(new SimplePoint(i, j));
    }

    private static void check(String msg, BufferedImage img) {
        if (img == null || img.getHeight() < 1 || img.getWidth() < 1) {
            throw new IllegalArgumentException(msg);
        }
    }
}
