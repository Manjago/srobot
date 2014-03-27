package srobot;

import junit.framework.TestCase;
import org.junit.Test;

public class ElementTest {
    @Test
    public void testEquals() throws Exception {
        Element e = new Element(new CellInfo(CellType.CLOSED, new SimplePoint(1, 2)));
        TestCase.assertEquals(new Element(new CellInfo(CellType.CLOSED, new SimplePoint(1, 2))), e);
    }

    @Test
    public void testEqualsLong() throws Exception {
        Element e = new Element(new CellInfo(CellType.CLOSED, new SimplePoint(1, 2)), new CellInfo(CellType.BOMB, new SimplePoint(2, 2)));
        TestCase.assertEquals(new Element(new CellInfo(CellType.CLOSED, new SimplePoint(1, 2)), new CellInfo(CellType.BOMB, new SimplePoint(2, 2))), e);
    }

}
