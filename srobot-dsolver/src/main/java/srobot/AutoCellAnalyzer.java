package srobot;

public class AutoCellAnalyzer implements CellAnalyzer {

    private final Cells cells;

    public AutoCellAnalyzer(Cells cells) {
        this.cells = cells;
    }

    @Override
    public Prediction makePredict(CellInfo cellInfo) {

        Elements elements = extractElements(cellInfo);

        ElementStates elementStates = new ElementStates(elements);

        calcInitialStates(elementStates);

        return null;
    }

    private void calcInitialStates(ElementStates elementStates) {

    }

    public Elements extractElements(CellInfo cellInfo) {
        CellNeighbours cellNeighbours = new CellNeighbours(cells, cellInfo.getCoords());

        ElementCollector collector = cellNeighbours.asStream()
                .filter(cellInfo1 -> cellInfo1.getCellType().getState() == CellType.State.OPENED)
                .collect(
                        () -> new ElementCollector(cellNeighbours)
                        , ElementCollector::accept
                        , ElementCollector::accept
                );

        return collector.getElements();
    }

}
