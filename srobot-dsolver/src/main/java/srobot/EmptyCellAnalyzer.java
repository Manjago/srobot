package srobot;

public class EmptyCellAnalyzer {

    private final Cells cells;

    public EmptyCellAnalyzer(Cells cells) {
        this.cells = cells;
    }

    public Prediction makePredict(CellInfo cellInfo){
        return null;
    }
}
