package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class AutoSolverTest {
    @Test
    public void testGetEmpty() throws Exception {
        Cells cells = new Cells(2,2);
        cells.put(0, 0, CellType.INFO_1);
        cells.put(0, 1, CellType.INFO_2);
        cells.put(1, 0, CellType.CLOSED);
        cells.put(1, 1, CellType.BOMB);

        AutoSolver autoSolver = new AutoSolver();
        List<CellInfo> r = autoSolver.getEmptyCells(cells);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(2, r.size());

    }
}
