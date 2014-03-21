package srobot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CellNeighbours {

    private final List<CellInfo> data = new ArrayList<>();

    public Stream<CellInfo> asStream() {
        return data.stream();
    }

    public CellNeighbours(Cells cells, SimplePoint start) {
        for (int iShift = -1; iShift <= 1; ++iShift) {
            for (int jShift = -1; jShift <= 1; ++jShift) {

                final int i = start.getX() + iShift;
                final int j = start.getY() + jShift;
                if (cells.isCorrect(i, j)) {
                    data.add(cells.get(i, j));
                }

            }
        }

    }

}
