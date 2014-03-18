package solver;

import srobot.*;
import srobot.lamelinq.Predicate;

import java.util.List;

public class StupidSolver implements Solver {

    @Override
    public Prediction predict(Cells cells) {

        Prediction result = state1with1closed(cells);
        if (result != null) {
            return result;
        }

        CellInfo firstClosed = cells.findFirst(new OneCellTypePredicate(CellType.CLOSED));

        if (firstClosed != null) {
            return new Prediction(firstClosed.getCoords(), Prediction.PredictionType.FREE);
        }

        return null;
    }

    private Prediction state1with1closed(final Cells cells) {

        CellInfo cellInfo = cells.findFirst(new Predicate<CellInfo>() {
            @Override
            public boolean test(CellInfo item) {

                if (!CellType.INFO_1.equals(item.getCellType())) {
                    return false;
                }

                final CellNeighbours cellNeighbours = new CellNeighbours(cells, item.getCoords());
                List<CellInfo> neighboursClosed = cellNeighbours.findAll(new OneCellTypePredicate(CellType.CLOSED));

                if (neighboursClosed.size() != 1) {
                    return false;
                }

                CellInfo flagged = cellNeighbours.findFirst(new OneCellTypePredicate(CellType.FLAG));
                return flagged == null;
            }
        });


        if (cellInfo == null) {
            return null;
        }

        CellInfo result = new CellNeighbours(cells, cellInfo.getCoords()).findFirst(new OneCellTypePredicate(CellType.CLOSED));

        return new Prediction(result.getCoords(), Prediction.PredictionType.MINE);

    }
}
