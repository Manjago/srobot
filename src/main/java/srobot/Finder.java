package srobot;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Finder {
    List<SimplePoint> find(BufferedImage searchBase, SearchPattern pattern);
    List<SimplePoint> find(BufferedImage searchBase, SearchPattern pattern, int depth);
    SimplePoint findOne(BufferedImage searchBase, SearchPattern pattern);
}
