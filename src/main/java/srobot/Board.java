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

    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);
    private static final Finder FINDER = new BruteFinder();
    private static final Map<CellType, SearchPattern> PATTERNS;
    private static final Map<BoardState, SearchPattern> STATES;

    public Board(SimplePoint resolvedLeftCorner, BufferedImage big) {
        this.resolvedLeftCorner = resolvedLeftCorner;
        this.big = big;
    }

    private final SimplePoint resolvedLeftCorner;
    private final BufferedImage big;
    private NavigableSet<Integer> xCoord;
    private NavigableSet<Integer> yCoord;

    static {
        PATTERNS = new HashMap<>();
        STATES = new HashMap<>();
        try {
            PATTERNS.put(CellType.BOMB, new SearchPattern(Loader.load("bomb.png")));
            PATTERNS.put(CellType.ERROR_BOMB, new SearchPattern(Loader.load("errorBomb.png")));
            PATTERNS.put(CellType.EXPLODED_BOMB, new SearchPattern(Loader.load("explodedBomb.png")));
            PATTERNS.put(CellType.FLAG, new SearchPattern(Loader.load("flag.png")));
            PATTERNS.put(CellType.INFO_1, new SearchPattern(Loader.load("1.png")));
            PATTERNS.put(CellType.INFO_2, new SearchPattern(Loader.load("2.png")));
            PATTERNS.put(CellType.INFO_3, new SearchPattern(Loader.load("3.png")));
            PATTERNS.put(CellType.INFO_4, new SearchPattern(Loader.load("4.png")));
            PATTERNS.put(CellType.INFO_5, new SearchPattern(Loader.load("5.png")));
            PATTERNS.put(CellType.INFO_6, new SearchPattern(Loader.load("6.png")));
            PATTERNS.put(CellType.INFO_7, new SearchPattern(Loader.load("7.png")));
            PATTERNS.put(CellType.INFO_8, new SearchPattern(Loader.load("8.png")));
            PATTERNS.put(CellType.OPENED, new SearchPattern(Loader.load("opened.png")));
            PATTERNS.put(CellType.CLOSED, new SearchPattern(Loader.load("closed.png")));
            PATTERNS.put(CellType.QUESTION, new SearchPattern(Loader.load("question.png")));

            STATES.put(BoardState.NORMAL, new SearchPattern(Loader.load("normalState.png")));
            STATES.put(BoardState.WAIT, new SearchPattern(Loader.load("waitState.png")));
            STATES.put(BoardState.FAIL, new SearchPattern(Loader.load("failState.png")));
            STATES.put(BoardState.OK, new SearchPattern(Loader.load("finishState.png")));

        } catch (IOException e) {
            LOGGER.error("fail load picture", e);
        }

    }

    public Cells resolve() {


        xCoord = new TreeSet<>();
        yCoord = new TreeSet<>();

        Map<SimplePoint, CellType> temp = new HashMap<>();

        for (Map.Entry<CellType, SearchPattern> k : PATTERNS.entrySet()) {
            List<SimplePoint> res = FINDER.find(big, k.getValue());
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

        int xPoint = recodeX(turn);

        int yPoint = recodeY(turn);

        return new SimplePoint(xPoint, yPoint).add(resolvedLeftCorner);
    }

    private int recodeY(SimplePoint turn) {
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
        return yPoint;
    }

    private int recodeX(SimplePoint turn) {
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
        return xPoint;
    }


    public BoardState getState(){

        for(Map.Entry<BoardState, SearchPattern> state : STATES.entrySet()){
            SimplePoint point = FINDER.findOne(big, state.getValue());
            if (point != null){
                return state.getKey();
            }
        }
        return null;

    }

}

