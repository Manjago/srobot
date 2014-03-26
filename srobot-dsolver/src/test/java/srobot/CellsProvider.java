package srobot;

import java.util.Arrays;
import java.util.List;

public class CellsProvider {
    static Cells supplyCellsStd() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.INFO_1, new SimplePoint(0, 0)),
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                new CellInfo(CellType.BOMB, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }

    static Cells supplyCells1() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 0)),
                new CellInfo(CellType.CLOSED, new SimplePoint(0, 1)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                new CellInfo(CellType.BOMB, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }

    static Cells supplyCellsEmpty() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 0)),
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)),
                new CellInfo(CellType.INFO_2, new SimplePoint(1, 0)),
                new CellInfo(CellType.INFO_2, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }

    static Cells supplyCellsClosed() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.CLOSED, new SimplePoint(0, 0)),
                new CellInfo(CellType.CLOSED, new SimplePoint(0, 1)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }
}
