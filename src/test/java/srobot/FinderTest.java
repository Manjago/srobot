package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class FinderTest {
    @Test
    public void testShiftedRight1() throws Exception {
        BufferedImage big = Loader.load("shifted_right_1.png");
        BufferedImage test = Loader.load("1.png");
        List<LamePoint> r = Finder.find(big, test);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
        TestCase.assertEquals(new LamePoint(1, 0), r.get(0));
    }

    @Test
    public void testShiftedDown1() throws Exception {
        BufferedImage big = Loader.load("shifted_down_1.png");
        BufferedImage test = Loader.load("1.png");
        List<LamePoint> r = Finder.find(big, test);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
        TestCase.assertEquals(new LamePoint(0, 1), r.get(0));
    }

    @Test
    public void test1_11() throws Exception {
        BufferedImage big = Loader.load("1_11.png");
        BufferedImage test = Loader.load("1.png");
        List<LamePoint> r = Finder.find(big, test);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(11, r.size());
    }

    @Test
    public void test1_2_small() throws Exception {
        BufferedImage big = Loader.load("1_2_small.png");
        BufferedImage test = Loader.load("1.png");
        List<LamePoint> r = Finder.find(big, test);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(2, r.size());
    }

    @Test
    public void test1() throws Exception {
        BufferedImage big = Loader.load("test1.png");
        BufferedImage test = Loader.load("1.png");
        List<LamePoint> r = Finder.find(big, test);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
    }

    @Test
    public void test3() throws Exception {
        BufferedImage big = Loader.load("test3_3.png");
        BufferedImage test = Loader.load("3.png");
        List<LamePoint> r = Finder.find(big, test, new LamePoint(0, 2));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(3, r.size());
    }

    @Test
    public void testNormal() throws Exception {
        BufferedImage big = Loader.load("testNormal.png");
        BufferedImage test = Loader.load("normal.png");
        List<LamePoint> r = Finder.find(big, test);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
    }

    @Test
    public void testCombo() throws Exception {
        BufferedImage big = Loader.load("testCorners.png");
        TestCase.assertEquals(1, Finder.find(big, Loader.load("NE.png")).size());
        TestCase.assertEquals(1, Finder.find(big, Loader.load("NW.png")).size());
        TestCase.assertEquals(1, Finder.find(big, Loader.load("SE.png")).size());
        TestCase.assertEquals(1, Finder.find(big, Loader.load("SW.png")).size());
        TestCase.assertEquals(25, Finder.find(big, Loader.load("1.png")).size());
        TestCase.assertEquals(12, Finder.find(big, Loader.load("2.png")).size());
        TestCase.assertEquals(1, Finder.find(big, Loader.load("3.png"), new LamePoint(0, 2)).size());
        TestCase.assertEquals(1, Finder.find(big, Loader.load("4.png")).size());
        TestCase.assertEquals(0, Finder.find(big, Loader.load("5.png")).size());
        TestCase.assertEquals(1, Finder.find(big, Loader.load("normal.png")).size());
    }


}
