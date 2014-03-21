package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AutoSolverTest {

    private static Cells supplyCellsStd() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.INFO_1, new SimplePoint(0, 0)),
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                new CellInfo(CellType.BOMB, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }

    private static Cells supplyCells1() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 0)),
                new CellInfo(CellType.CLOSED, new SimplePoint(0, 1)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                new CellInfo(CellType.BOMB, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }

    private static Cells supplyCellsEmpty() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 0)),
                new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)),
                new CellInfo(CellType.INFO_2, new SimplePoint(1, 0)),
                new CellInfo(CellType.INFO_2, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }

    private static Cells supplyCellsClosed() {
        List<CellInfo> data = Arrays.asList(
                new CellInfo(CellType.CLOSED, new SimplePoint(0, 0)),
                new CellInfo(CellType.CLOSED, new SimplePoint(0, 1)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                new CellInfo(CellType.CLOSED, new SimplePoint(1, 1))
        );
        return new Cells(data);
    }

    @Test
    public void testGetEmpty() throws Exception {
        Cells cells = supplyCellsStd();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(null));
        List<CellInfo> r = autoSolver.getEmptyCells(cells);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(2, r.size());

    }

    @Test
    public void testPessimisticPrediction() throws Exception {
        Cells cells = supplyCellsStd();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(null));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNull(p);
    }

    @Test
    public void testOptimisticPrediction() throws Exception {
        Cells cells = supplyCells1();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.FREE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNotNull(p);
    }

    @Test
    public void testSuperOptimisticPrediction() throws Exception {
        Cells cells = supplyCellsEmpty();
        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.MINE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNotNull(p);
    }

    @Test
    public void testStupidOptimisticPrediction() throws Exception {
        Cells cells = supplyCellsClosed();

        AutoSolver autoSolver = new AutoSolver(new DummyCellAnalyzerFactory(Prediction.PredictionType.MINE));
        Prediction p = autoSolver.predict(cells);
        TestCase.assertNull(p);
    }

}
