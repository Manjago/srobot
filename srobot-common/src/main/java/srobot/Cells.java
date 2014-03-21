package srobot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cells {
    private final Map<SimplePoint, CellInfo> cells;
    private final int width;
    private final int height;

    public Cells(Collection<CellInfo> data) {
        this.cells = data.stream().collect(Collectors.toMap((Function<CellInfo, SimplePoint>) CellInfo::getCoords, o -> o));

        width = data.stream()
                .map(cellInfo -> cellInfo.getCoords().getX())
                .reduce(Integer.MIN_VALUE, Integer::max) + 1;

        height = data.stream()
                .map(cellInfo -> cellInfo.getCoords()
                        .getY()).reduce(Integer.MIN_VALUE, Integer::max) + 1;

        if (width <= 0 || height <= 0 || data.size() != width * height) {
            throw new IllegalStateException(String.format("bad cells width=%d height=%d size=%d", width, height, data.size()));
        }

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Stream<CellInfo> asStream() {
        return cells.values().stream();
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

    public CellInfo get(int i, int j) {
        if (isCorrect(i, j)) {
            return cells.get(new SimplePoint(i, j));
        }
        throw new AppException(String.format("bad get %d %d for cells %d %d", i, j, width, height));
    }

}
