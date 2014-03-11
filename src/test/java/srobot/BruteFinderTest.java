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
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
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
    public void testBomb() throws Exception {
        BufferedImage big = Loader.load("testBomb.png");
        BufferedImage test = Loader.load("bomb.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(6, r.size());
    }

    @Test
    public void testErrorBomb() throws Exception {
        BufferedImage big = Loader.load("testBomb.png");
        BufferedImage test = Loader.load("errorBomb.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(4, r.size());
    }

    @Test
    public void testExlodedBomb() throws Exception {
        BufferedImage big = Loader.load("testBomb.png");
        BufferedImage test = Loader.load("explodedBomb.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(1, r.size());
    }

    @Test
    public void testQuestion() throws Exception {
        BufferedImage big = Loader.load("testQuestion.png");
        BufferedImage test = Loader.load("question.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(2, r.size());
    }

    @Test
    public void testFlag() throws Exception {
        BufferedImage big = Loader.load("testQuestion.png");
        BufferedImage test = Loader.load("flag.png");
        List<SimplePoint> r = new BruteFinder().find(big, new SearchPattern(test));
        TestCase.assertNotNull(r);
        TestCase.assertEquals(6, r.size());
    }

    @Test
    public void testCombo() throws Exception {
        BufferedImage big = Loader.load("testCombo.png");
        TestCase.assertEquals(25, new BruteFinder().find(big, new SearchPattern(Loader.load("1.png"))).size());
        TestCase.assertEquals(12, new BruteFinder().find(big, new SearchPattern(Loader.load("2.png"))).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("3.png"))).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("4.png"))).size());
        TestCase.assertEquals(0, new BruteFinder().find(big, new SearchPattern(Loader.load("5.png"))).size());
        TestCase.assertEquals(1, new BruteFinder().find(big, new SearchPattern(Loader.load("normal.png"))).size());
    }


}
