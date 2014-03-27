package srobot;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellNeighbours {

    private final Bag<CellInfo, SimplePoint> data = new Bag<>();

    public CellNeighbours(Cells cells, SimplePoint start) {
        for (int iShift = -1; iShift <= 1; ++iShift) {
            for (int jShift = -1; jShift <= 1; ++jShift) {

                if (iShift == 0 && jShift == 0) {
                    continue;
                }

                final int i = start.getX() + iShift;
                final int j = start.getY() + jShift;
                if (cells.isCorrect(i, j)) {
                    data.add(cells.get(i, j));
                }

            }
        }

    }

    public int size() {
        return data.size();
    }

    public Stream<CellInfo> asStream() {
        return data.asStream();
    }

    public boolean contains(@Nonnull SimplePoint point) {
        Objects.requireNonNull(point, "SimplePoint == null");
        return data.contains(point);
    }

    public boolean containsOpened(@Nonnull SimplePoint point) {
        Objects.requireNonNull(point, "SimplePoint == null");
        return data.contains(point) && data.get(point).getCellType().getState() == CellType.State.OPENED;
    }

    public CellInfo get(@Nonnull SimplePoint pretender) {
        Objects.requireNonNull(pretender);

        return data.get(pretender);
    }

    @Override
    public String toString() {
        return asStream().sorted().collect(Collectors.toList()).toString();
    }
}
