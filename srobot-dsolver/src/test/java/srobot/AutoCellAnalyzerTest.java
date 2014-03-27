package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class AutoCellAnalyzerTest {
    @Test
    public void testExtractElements() throws Exception {

        Cells cells = CellsProvider.supplyCellsStd();

        AutoCellAnalyzer a = new AutoCellAnalyzer(cells);

        Elements e = a.extractElements(cells.get(0, 0));

        TestCase.assertNotNull(e);
        TestCase.assertEquals(1, e.size());
        TestCase.assertEquals(new Elements(new Element(new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)))), e);
    }

    @Test
    public void testExtractElementsEmpty() throws Exception {

        Cells cells = CellsProvider.supplyCellsEmpty();

        AutoCellAnalyzer a = new AutoCellAnalyzer(cells);

        Elements e = a.extractElements(cells.get(0, 0));

        TestCase.assertNotNull(e);
        TestCase.assertEquals(6, e.size());
    }

    @Test
    public void testExtractElementsElements() throws Exception {

        Cells cells = CellsProvider.supplyCellsElements();

        AutoCellAnalyzer a = new AutoCellAnalyzer(cells);

        Elements e = a.extractElements(cells.get(1, 1));

        TestCase.assertNotNull(e);
        Elements std = new Elements(
                new Element(CellType.INFO_1, 0,1),
                new Element(new CellInfo(CellType.INFO_3, 2, 1), new CellInfo(CellType.INFO_2, 2, 0)),
                new Element(CellType.INFO_2, 2,0),
                new Element(CellType.INFO_3, 2,1)
                );

        TestCase.assertEquals(std, e);
    }
}
