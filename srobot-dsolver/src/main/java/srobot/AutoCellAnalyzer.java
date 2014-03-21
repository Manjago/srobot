package srobot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AutoCellAnalyzer implements CellAnalyzer {

    private final Collection<Element> elements = new ArrayList<>();

    private final Cells cells;

    public AutoCellAnalyzer(Cells cells) {
        this.cells = cells;
    }


    @Override
    public Prediction makePredict(CellInfo cellInfo) {

        CellNeighbours cellNeighbours = new CellNeighbours(cells, cellInfo.getCoords());



        return null;
    }
}
