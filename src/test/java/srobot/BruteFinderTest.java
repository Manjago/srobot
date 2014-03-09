package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class BruteFinderTest {

    @Test
    public void testSelf() throws Exception {
        BufferedImage big = Loader.load("1.png");
        BufferedImage test = Loader.load("1.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
        TestCase.assertEquals(new SimplePoint(0, 0), r.get(0));
    }

    @Test
    public void testShiftedRight1() throws Exception {
        BufferedImage big = Loader.load("shifted_right_1.png");
        BufferedImage test = Loader.load("1.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
        TestCase.assertEquals(new SimplePoint(1, 0), r.get(0));
    }

    @Test
    public void testShiftedDown1() throws Exception {
        BufferedImage big = Loader.load("shifted_down_1.png");
        BufferedImage test = Loader.load("1.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
        TestCase.assertEquals(new SimplePoint(0, 1), r.get(0));
    }

    @Test
    public void test1_11() throws Exception {
        BufferedImage big = Loader.load("1_11.png");
        BufferedImage test = Loader.load("1.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(11, r.size());
    }

    @Test
    public void test1_2_small() throws Exception {
        BufferedImage big = Loader.load("1_2_small.png");
        BufferedImage test = Loader.load("1.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(2, r.size());
    }

    @Test
    public void test1_2_small_oneonly() throws Exception {
        BufferedImage big = Loader.load("1_2_small.png");
        BufferedImage test = Loader.load("1.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test), 1);
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
    }

    @Test
    public void test1_2_small_oneonly_yes() throws Exception {
        BufferedImage big = Loader.load("1_2_small.png");
        BufferedImage test = Loader.load("1.png");
        SimplePoint r = new BruteFinder().findOne(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
    }

    @Test
    public void test1_2_small_oneonly_no() throws Exception {
        BufferedImage big = Loader.load("1_2_small.png");
        BufferedImage test = Loader.load("3.png");
        SimplePoint r = new BruteFinder().findOne(big, new SearchPattern(test));
        TestCase.assertNull(r);
    }

    @Test
    public void test1() throws Exception {
        BufferedImage big = Loader.load("test1.png");
        BufferedImage test = Loader.load("1.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
    }

    @Test
    public void test3() throws Exception {
        BufferedImage big = Loader.load("test3_3.png");
        BufferedImage test = Loader.load("3.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test, new SimplePoint(0, 2)));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(3, r.size());
    }

    @Test
    public void testNormal() throws Exception {
        BufferedImage big = Loader.load("testNormal.png");
        BufferedImage test = Loader.load("normal.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
    }

    @Test
    public void testCombo() throws Exception {
        BufferedImage big = Loader.load("testCorners.png");
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("NE.png"), null)).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("NW.png"), null)).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("SE.png"), null)).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("SW.png"), null)).size());
        TestCase.assertEquals(25, new BruteFinder().find(big, new SearchPattern(Loader.load("1.png"))).size());
        TestCase.assertEquals(12, new BruteFinder().find(big, new SearchPattern(Loader.load("2.png"))).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("3.png"), new SimplePoint(0, 2))).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("4.png"))).size());
        TestCase.assertEquals(0, new BruteFinder().find(big, new SearchPattern(Loader.load("5.png"))).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("normal.png"))).size());
    }


}