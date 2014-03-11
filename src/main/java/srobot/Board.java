package srobot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Board {

    private final Finder finder = new BruteFinder();
    private final Map<CellType, SearchPattern> patterns;

    public Board() throws IOException {
        this.patterns = new HashMap<>();
        patterns.put(CellType.BOMB, new SearchPattern(Loader.load("bomb.png")));
        patterns.put(CellType.ERROR_BOMB, new SearchPattern(Loader.load("errorBomb.png")));
        patterns.put(CellType.EXPLODED_BOMB, new SearchPattern(Loader.load("explodedBomb.png")));
        patterns.put(CellType.FLAG, new SearchPattern(Loader.load("flag.png")));
        patterns.put(CellType.INFO_1, new SearchPattern(Loader.load("1.png")));
        patterns.put(CellType.INFO_2, new SearchPattern(Loader.load("2.png")));
        patterns.put(CellType.INFO_3, new SearchPattern(Loader.load("3.png")));
        patterns.put(CellType.INFO_4, new SearchPattern(Loader.load("4.png")));
        patterns.put(CellType.INFO_5, new SearchPattern(Loader.load("5.png")));
        patterns.put(CellType.INFO_6, new SearchPattern(Loader.load("6.png")));
        patterns.put(CellType.INFO_7, new SearchPattern(Loader.load("7.png")));
        patterns.put(CellType.INFO_8, new SearchPattern(Loader.load("8.png")));
        patterns.put(CellType.OPENED, new SearchPattern(Loader.load("opened.png")));
        patterns.put(CellType.CLOSED, new SearchPattern(Loader.load("closed.png")));
        patterns.put(CellType.QUESTION, new SearchPattern(Loader.load("question.png")));
    }

    public Cells resolve(BufferedImage big) {

        NavigableSet<Integer> xCoord = new TreeSet<>();
        NavigableSet<Integer> yCoord = new TreeSet<>();

        Map<SimplePoint, CellType> temp = new HashMap<>();

        for(Map.Entry<CellType, SearchPattern> k : patterns.entrySet()){
           List<SimplePoint> res = finder.find(big, k.getValue());
           for(SimplePoint point : res){
               temp.put(point, k.getKey());
               xCoord.add(point.getX());
               yCoord.add(point.getY());
           }
        }

        boolean selftest = temp.size() == xCoord.size() * yCoord.size();
        if (!selftest){
            throw new IllegalStateException(String.format("something wrong: %d %d %d", temp.size(), xCoord.size(), yCoord.size()));
        }

        Cells result = new Cells(xCoord.size(), yCoord.size());

        int i = -1;
        for(int x : xCoord){
            ++i;
            int j = -1;
            for(int y : yCoord){
                ++j;
                final CellType cellType = temp.get(new SimplePoint(x, y));
                if (cellType == null){
                    throw new IllegalStateException(String.format("null cell (%d,%d) [%d,%d]", i, j, x, y));
                }
                result.put(i, j, cellType);
            }
        }

        return result;
    }
}

