package srobot;

import junit.framework.TestCase;
import org.junit.Test;

public class CellNeighboursTest {
    @Test
    public void test() throws Exception {
        Cells cells = CellsProvider.supplyCellsStd();
        CellNeighbours cn = new CellNeighbours(cells, SimplePoint.ZERO);

        TestCase.assertNotNull(cn);
        TestCase.assertEquals(3, cn.size());
        TestCase.assertEquals(new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)), cn.get(new SimplePoint(0, 1)));
        TestCase.assertEquals(new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)), cn.get(new SimplePoint(1, 0)));
        TestCase.assertEquals(new CellInfo(CellType.BOMB, new SimplePoint(1, 1)), cn.get(new SimplePoint(1, 1)));

    }
}
