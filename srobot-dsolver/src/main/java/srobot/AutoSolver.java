package srobot;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AutoSolver implements Solver {
    @Override
    public Prediction predict(Cells cells) {


        List<CellInfo> emptyCells = getEmptyCells(cells);

        //emptyCells.stream().forEach();

        return null;
    }

    public List<CellInfo> getEmptyCells(Cells cells) {
        return cells.asStream()
                .filter(cellInfo -> cellInfo.getCellType().getState() == CellType.State.OPENED)
                .collect(Collectors.toList());
    }
}


