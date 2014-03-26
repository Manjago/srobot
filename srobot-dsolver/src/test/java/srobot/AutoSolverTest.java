package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class AutoSolverTest {

    @Test
    public void testGetEmpty() throws Exception {
        Cells cells = CellsProvider.supplyCellsStd();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(null));
        List<CellInfo> r = autoSolver.getEmptyCells(cells);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(2, r.size());

    }

    @Test
    public void testPessimisticPrediction() throws Exception {
        Cells cells = CellsProvider.supplyCellsStd();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(null));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNull(p);
    }

    @Test
    public void testOptimisticPrediction() throws Exception {
        Cells cells = CellsProvider.supplyCells1();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.FREE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNotNull(p);
    }

    @Test
    public void testSuperOptimisticPrediction() throws Exception {
        Cells cells = CellsProvider.supplyCellsEmpty();
        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.MINE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNotNull(p);
    }

    @Test
    public void testStupidOptimisticPrediction() throws Exception {
        Cells cells = CellsProvider.supplyCellsClosed();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.MINE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNull(p);
    }

}
