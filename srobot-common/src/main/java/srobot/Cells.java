package srobot;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Cells {
    private final Map<SimplePoint, CellInfo> cells;
    private final int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private final int height;

    public Stream<CellInfo> asStream() {
        return cells.values().stream();
    }

    public Cells(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        this.cells = new HashMap<>();
        this.width = width;
        this.height = height;

    }

    public String debugLine(int line) {

        if (line < 0 || line >= height) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < width; ++i) {
            sb.append(get(i, line).getCellType().getDebugChar());
        }

        return sb.toString();
    }

    public boolean isCorrect(int i, int j) {
        return (i >= 0 && i < width && j >= 0 && j < height);
    }

    public void put(int i, int j, CellType cellType) {
        if (!isCorrect(i, j)) {
            throw new AppException(String.format("Bad put for %d %d", i, j));
        }
        final SimplePoint coords = new SimplePoint(i, j);
        cells.put(coords, new CellInfo(cellType, coords));
    }

    public CellInfo get(int i, int j) {
        if (isCorrect(i, j)) {
            return cells.get(new SimplePoint(i, j));
        }
        throw new AppException(String.format("bad get %d %d for cells %d %d", i, j, width, height));
    }

}
