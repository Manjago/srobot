package srobot;

import java.util.Arrays;
import java.util.List;

public class AutoSolver implements Solver {
    @Override
    public Prediction predict(Cells cells) {


        getEmptyCells(cells);

        return null;
    }

    public List<CellInfo> getEmptyCells(Cells cells) {
        return Arrays.asList(cells.asStream()
                .filter(cellInfo -> cellInfo.getCellType().getState() == CellType.State.OPENED)
                .toArray(CellInfo[]::new));
    }
}


