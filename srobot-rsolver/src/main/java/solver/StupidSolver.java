package solver;

import srobot.*;

public class StupidSolver implements Solver {
    @Override
    public Prediction predict(Cells cells) {

        // ищем первую же закрытую
        for(int i = 0; i < cells.getWidth(); ++i){
            for (int j = 0; j < cells.getHeight(); ++j){
                if (cells.get(i, j) == CellType.CLOSED){
                    return new Prediction(new SimplePoint(i, j), Prediction.PredictionType.FREE);
                }
            }
        }

        return null;
    }
}
