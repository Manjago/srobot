package solver;

import srobot.*;
import srobot.lamelinq.Predicate;

public class StupidSolver implements Solver {
    @Override
    public Prediction predict(Cells cells) {

        CellInfo firstClosed = cells.findFirst(new Predicate<CellType>() {
            @Override
            public boolean test(CellType item) {
                return CellType.CLOSED.equals(item);
            }
        });

        if (firstClosed != null){
            return new Prediction(firstClosed.getCoords(), Prediction.PredictionType.FREE);
        }

        return null;
    }
}
