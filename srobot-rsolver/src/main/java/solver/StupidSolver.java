package solver;

import srobot.*;
import srobot.lamelinq.Predicate;

public class StupidSolver implements Solver {
    @Override
    public Prediction predict(Cells cells) {

        Prediction result = state1with1closed();
        if (result != null){
            return result;
        }

        CellInfo firstClosed = cells.findFirst(new Predicate<CellInfo>() {
            @Override
            public boolean test(CellInfo item) {
                return CellType.CLOSED.equals(item.getCellType());
            }
        });

        if (firstClosed != null){
            return new Prediction(firstClosed.getCoords(), Prediction.PredictionType.FREE);
        }

        return null;
    }

    private Prediction state1with1closed(){



        return null;
    }
}
