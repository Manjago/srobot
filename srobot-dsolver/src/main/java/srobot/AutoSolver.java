package srobot;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AutoSolver implements Solver {

    private final CellAnalyzerFactory cellAnalyzerFactory;

    public AutoSolver(CellAnalyzerFactory cellAnalyzerFactory) {
        this.cellAnalyzerFactory = cellAnalyzerFactory;
    }

    @Override
    public Prediction predict(Cells cells) {


        List<CellInfo> emptyCells = getEmptyCells(cells);

        Optional<Prediction> prediction = emptyCells.stream()
                .parallel()
                .map(cellInfo -> cellAnalyzerFactory.create().makePredict(cellInfo))
                .filter(prediction1 -> prediction1 != null)
                .findAny();

        return prediction.orElse(null);
    }

    public List<CellInfo> getEmptyCells(Cells cells) {
        return cells.asStream()
                .filter(cellInfo -> cellInfo.getCellType().getState() == CellType.State.OPENED)
                .collect(Collectors.toList());
    }
}


