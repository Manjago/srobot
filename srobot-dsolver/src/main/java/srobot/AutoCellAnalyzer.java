package srobot;

import java.util.ArrayList;
import java.util.Collection;

public class AutoCellAnalyzer implements CellAnalyzer {

    private final Collection<Element> elements = new ArrayList<>();
    private final Cells cells;

    public AutoCellAnalyzer(Cells cells) {
        this.cells = cells;
    }

    @Override
    public Prediction makePredict(CellInfo cellInfo) {

        CellNeighbours cellNeighbours = new CellNeighbours(cells, cellInfo.getCoords());

        ElementCollector collector = cellNeighbours.asStream().collect(ElementCollector::new
                , ElementCollector::accept, ElementCollector::accept
        );

        Elements elements = collector.getElements();


        return null;
    }

}
