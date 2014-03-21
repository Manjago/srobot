package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CellsTest {

    @Test
    public void test2x2() throws Exception {

        Cells cells = new Cells(
                Arrays.asList(
                        new CellInfo(CellType.INFO_1, new SimplePoint(0, 0)),
                        new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)),
                        new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                        new CellInfo(CellType.BOMB, new SimplePoint(1, 1)),
                        new CellInfo(CellType.INFO_1, new SimplePoint(0, 2)),
                        new CellInfo(CellType.INFO_1, new SimplePoint(1, 2))
                        ));

        TestCase.assertEquals(3, cells.getHeight());
        TestCase.assertEquals(2, cells.getWidth());
    }

    @Test(expected = IllegalStateException.class)
    public void testBad() throws Exception {

        new Cells(
                Arrays.asList(
                        new CellInfo(CellType.INFO_1, new SimplePoint(0, 0)),
                        new CellInfo(CellType.INFO_2, new SimplePoint(0, 1)),
                        new CellInfo(CellType.CLOSED, new SimplePoint(1, 0)),
                        new CellInfo(CellType.BOMB, new SimplePoint(1, 1)),
                        new CellInfo(CellType.INFO_1, new SimplePoint(0, 2))
                ));

    }

}
