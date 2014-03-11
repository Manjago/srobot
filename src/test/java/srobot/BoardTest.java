package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.awt.image.BufferedImage;

public class BoardTest {
    @Test
    public void testNormal() throws Exception {
        BufferedImage big = Loader.load("test9x10.png");

        Board board = new Board();
        Cells cells = board.resolve(big);
        TestCase.assertNotNull(cells);
        TestCase.assertEquals(9, cells.getHeight());
        TestCase.assertEquals(10, cells.getWidth());
        TestCase.assertEquals("XX1.....1X", cells.debugLine(0));
        TestCase.assertEquals("XX1.....11", cells.debugLine(1));
        TestCase.assertEquals("XX11.111..", cells.debugLine(2));
        TestCase.assertEquals("XXX1.1X1..", cells.debugLine(3));
        TestCase.assertEquals("X311.111..", cells.debugLine(4));
        TestCase.assertEquals("X1........", cells.debugLine(5));
        TestCase.assertEquals("X111......", cells.debugLine(6));
        TestCase.assertEquals("XXX1111...", cells.debugLine(7));
        TestCase.assertEquals("XXXXXX1...", cells.debugLine(8));
    }
}
