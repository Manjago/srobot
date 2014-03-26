package srobot;

import org.junit.Test;

public class AutoCellAnalyzerTest {
    @Test
    public void testExtractElements() throws Exception {

        Cells cells = CellsProvider.supplyCellsStd();

        AutoCellAnalyzer a = new AutoCellAnalyzer(cells);

        Elements e = a.extractElements(cells.get(0, 0));
        System.out.println(e);


    }
}
