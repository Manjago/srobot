package srobot;

import junit.framework.TestCase;
import org.junit.Test;

public class ElementsTest {
    @Test
    public void testEquals() throws Exception {
        Elements e = new Elements();
        e.add(new Element(new CellInfo(CellType.CLOSED, new SimplePoint(2, 3))));
        e.add(new Element(new CellInfo(CellType.EMPTY, new SimplePoint(3, 3))));

        Elements e2 = new Elements();
        e2.add(new Element(new CellInfo(CellType.EMPTY, new SimplePoint(3, 3))));
        e2.add(new Element(new CellInfo(CellType.CLOSED, new SimplePoint(2, 3))));

        TestCase.assertEquals(e, e2);
    }
}
