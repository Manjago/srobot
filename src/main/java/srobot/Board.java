package srobot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Board {

    private static final Logger logger = LoggerFactory.getLogger(Board.class);
    private static final Finder finder = new BruteFinder();
    private static final Map<CellType, SearchPattern> patterns;
    private static final Map<BoardState, SearchPattern> states;

    public Board(SimplePoint resolvedLeftCorner, BufferedImage big) {
        this.resolvedLeftCorner = resolvedLeftCorner;
        this.big = big;
    }

    private final SimplePoint resolvedLeftCorner;
    private final BufferedImage big;
    private NavigableSet<Integer> xCoord;
    private NavigableSet<Integer> yCoord;

    static {
        patterns = new HashMap<>();
        states = new HashMap<>();
        try {
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

            states.put(BoardState.NORMAL, new SearchPattern(Loader.load("normalState.png")));
            states.put(BoardState.WAIT, new SearchPattern(Loader.load("waitState.png")));
            states.put(BoardState.FAIL, new SearchPattern(Loader.load("failState.png")));
            states.put(BoardState.OK, new SearchPattern(Loader.load("finishState.png")));

        } catch (IOException e) {
            logger.error("fail load picture", e);
        }

    }

    public Cells resolve() {


        xCoord = new TreeSet<>();
        yCoord = new TreeSet<>();

        Map<SimplePoint, CellType> temp = new HashMap<>();

        for (Map.Entry<CellType, SearchPattern> k : patterns.entrySet()) {
            List<SimplePoint> res = finder.find(big, k.getValue());
            for (SimplePoint point : res) {
                temp.put(point, k.getKey());
                xCoord.add(point.getX());
                yCoord.add(point.getY());
            }
        }

        boolean selftest = temp.size() == xCoord.size() * yCoord.size();
        if (!selftest) {
            throw new IllegalStateException(String.format("something wrong: %d %d %d", temp.size(), xCoord.size(), yCoord.size()));
        }

        Cells result = new Cells(xCoord.size(), yCoord.size());

        int i = -1;
        for (int x : xCoord) {
            ++i;
            int j = -1;
            for (int y : yCoord) {
                ++j;
                final CellType cellType = temp.get(new SimplePoint(x, y));
                if (cellType == null) {
                    throw new IllegalStateException(String.format("null cell (%d,%d) [%d,%d]", i, j, x, y));
                }
                result.put(i, j, cellType);
            }
        }

        return result;
    }

    public SimplePoint recode(SimplePoint turn) {
        if (turn == null) {
            throw new IllegalArgumentException();
        }

        if (turn.getX() < 0 || turn.getX() >= xCoord.size()) {
            throw new IllegalArgumentException("x");
        }

        if (turn.getY() < 0 || turn.getY() >= yCoord.size()) {
            throw new IllegalArgumentException("y");
        }

        int i = 0;
        int xPoint = -1;
        for (int x : xCoord) {
            if (i == turn.getX()) {
                xPoint = x;
                break;
            }
            ++i;
        }
        if (xPoint == -1) {
            throw new IllegalStateException("x");
        }

        int j = 0;
        int yPoint = -1;
        for (int y : yCoord) {
            if (j == turn.getY()) {
                yPoint = y;
                break;
            }
            ++j;
        }
        if (yPoint == -1) {
            throw new IllegalStateException("y");
        }

        return new SimplePoint(xPoint, yPoint).add(resolvedLeftCorner);
    }


    public BoardState getState(){

        for(Map.Entry<BoardState, SearchPattern> state : states.entrySet()){
            SimplePoint point = finder.findOne(big, state.getValue());
            if (point != null){
                return state.getKey();
            }
        }
        return null;

    }

}

