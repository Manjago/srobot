package srobot;

import srobot.lamelinq.Predicate;

import java.util.List;

public class AutoSolver implements Solver {
    @Override
    public Prediction predict(Cells cells) {


        getEmptyCells(cells);

        return null;
    }

    public List<CellInfo> getEmptyCells(Cells cells) {
        return cells.findAll(new Predicate<CellInfo>() {
            @Override
            public boolean test(CellInfo item) {
                return item.getCellType().getState() == CellType.State.OPENED;
            }
        });
    }
}


