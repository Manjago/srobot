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
        System.out.println(e);
    }

}
