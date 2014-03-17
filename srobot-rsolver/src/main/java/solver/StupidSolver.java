package solver;

import srobot.CellType;
import srobot.Cells;
import srobot.SimplePoint;
import srobot.Solver;

public class StupidSolver implements Solver {
    @Override
    public SimplePoint turn(Cells cells) {

        // ищем первую же закрытую
        for(int i = 0; i < cells.getWidth(); ++i){
            for (int j = 0; j < cells.getHeight(); ++j){
                if (cells.get(i, j) == CellType.CLOSED){
                    return new SimplePoint(i, j);
                }
            }
        }

        return null;
    }
}
