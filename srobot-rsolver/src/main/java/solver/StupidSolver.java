package solver;

import srobot.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StupidSolver implements Solver {

    @Override
    public Prediction predict(Cells cells) {

        Prediction result = state1with1closed(cells);
        if (result != null) {
            return result;
        }

        Optional<CellInfo> anyClosed = cells.asStream()
                .filter(cellInfo -> cellInfo.getCellType() == CellType.CLOSED)
                .findAny();

        if (anyClosed.isPresent()) {
            return new Prediction(anyClosed.get().getCoords(), Prediction.PredictionType.FREE);
        }

        return null;
    }

    private Prediction state1with1closed(final Cells cells) {

        Optional<CellInfo> anyCellInfo = cells.asStream()
                .filter(item -> {
                    if (!CellType.INFO_1.equals(item.getCellType())) {
                        return false;
                    }

                    final CellNeighbours cellNeighbours = new CellNeighbours(cells, item.getCoords());

                    List<CellInfo> neighboursClosed = cellNeighbours.asStream()
                            .filter(cellInfo -> cellInfo.getCellType() == CellType.CLOSED).collect(Collectors.toList());

                    if (neighboursClosed.size() != 1) {
                        return false;
                    }

                    Optional<CellInfo> anyFlagged = cellNeighbours.asStream()
                            .filter(cellInfo -> cellInfo.getCellType() == CellType.FLAG).findAny();
                    return !anyFlagged.isPresent();

                })
                .findAny();

        if (!anyCellInfo.isPresent()) {
            return null;
        }

        Optional<CellInfo> anyResult = new CellNeighbours(cells, anyCellInfo.get().getCoords()).asStream()
                .filter(cellInfo -> cellInfo.getCellType() == CellType.CLOSED).findAny();

        if (!anyResult.isPresent()) {
            throw new AppException("algo exception");
        }

        return new Prediction(anyResult.get().getCoords(), Prediction.PredictionType.MINE);
    }
}
