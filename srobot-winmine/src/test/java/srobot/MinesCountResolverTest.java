package srobot;

import junit.framework.TestCase;
import org.junit.Test;

import java.awt.image.BufferedImage;

public class MinesCountResolverTest {
    @Test
    public void testGetMinesCount10() throws Exception {
        BufferedImage big = Loader.load("digit-010-012.png");
        SimplePoint p = new BruteFinder().findOne(big, new SearchPattern(Loader.load("normalState.png")));

        MinesCountResolver m = new MinesCountResolver(big);
        TestCase.assertEquals(10, m.getMinesCount(p));
    }

    @Test
    public void testGetMinesCount35() throws Exception {
        BufferedImage big = Loader.load("digit-035-006.png");
        SimplePoint p = new BruteFinder().findOne(big, new SearchPattern(Loader.load("normalState.png")));

        MinesCountResolver m = new MinesCountResolver(big);
        TestCase.assertEquals(35, m.getMinesCount(p));
    }

}
