package srobot;

public class AutoCellAnalyzer implements CellAnalyzer {

    private final Cells cells;

    public AutoCellAnalyzer(Cells cells) {
        this.cells = cells;
    }

    @Override
    public Prediction makePredict(CellInfo cellInfo) {

        return null;
    }

    public Elements extractElements(CellInfo cellInfo) {
        CellNeighbours cellNeighbours = new CellNeighbours(cells, cellInfo.getCoords());

        ElementCollector collector = cellNeighbours.asStream().collect(
                () -> new ElementCollector(cellNeighbours)
                , ElementCollector::accept
                , ElementCollector::accept
        );

        return collector.getElements();
    }

}
