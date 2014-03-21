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

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(null));
        List<CellInfo> r = autoSolver.getEmptyCells(cells);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(2, r.size());

    }

    @Test
    public void testPessimisticPrediction() throws Exception {
        Cells cells = new Cells(2,2);
        cells.put(0, 0, CellType.INFO_1);
        cells.put(0, 1, CellType.INFO_2);
        cells.put(1, 0, CellType.CLOSED);
        cells.put(1, 1, CellType.BOMB);

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(null));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNull(p);
    }

    @Test
    public void testOptimisticPrediction() throws Exception {
        Cells cells = new Cells(2,2);
        cells.put(0, 0, CellType.INFO_2);
        cells.put(0, 1, CellType.CLOSED);
        cells.put(1, 0, CellType.CLOSED);
        cells.put(1, 1, CellType.BOMB);

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.FREE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNotNull(p);
        System.out.println(p);
    }

    @Test
    public void testSuperOptimisticPrediction() throws Exception {
        Cells cells = new Cells(2,2);
        cells.put(0, 0, CellType.INFO_2);
        cells.put(0, 1, CellType.INFO_2);
        cells.put(1, 0, CellType.INFO_2);
        cells.put(1, 1, CellType.INFO_2);

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.MINE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNotNull(p);
        System.out.println(p);
    }

    @Test
    public void testStupidOptimisticPrediction() throws Exception {
        Cells cells = new Cells(2,2);
        cells.put(0, 0, CellType.CLOSED);
        cells.put(0, 1, CellType.CLOSED);
        cells.put(1, 0, CellType.CLOSED);
        cells.put(1, 1, CellType.CLOSED);

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.MINE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNull(p);
    }

}
