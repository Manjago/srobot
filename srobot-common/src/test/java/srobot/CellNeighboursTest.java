package srobot;

import org.junit.Test;

public class CellNeighboursTest {
    @Test
    public void test() throws Exception {
        Cells cells = CellsProvider.supplyCellsStd();
        CellNeighbours cn = new CellNeighbours(cells, SimplePoint.ZERO);
        System.out.println(cn);

    }
}
