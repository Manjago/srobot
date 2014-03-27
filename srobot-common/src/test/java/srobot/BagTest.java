package srobot;

import junit.framework.TestCase;
import org.junit.Test;

public class BagTest {

    @Test(expected = NullPointerException.class)
    public void testNullKey() throws Exception {
        Bag<SimpleBagItem, Integer> b = new Bag<>();
        b.get(null);
    }

    @Test
    public void testContains() throws Exception {
        Bag<SimpleBagItem, Integer> b = new Bag<>();
        b.add(new SimpleBagItem(0));
        b.add(new SimpleBagItem(2));
        TestCase.assertEquals(2, b.size());
        TestCase.assertTrue(b.contains(0));
        TestCase.assertFalse(b.contains(1));
        TestCase.assertTrue(b.contains(2));
    }

    @Test
    public void testGet() throws Exception {
        Bag<SimpleBagItem, Integer> b = new Bag<>();
        b.add(new SimpleBagItem(0));
        b.add(new SimpleBagItem(2));
        TestCase.assertEquals(new SimpleBagItem(2), b.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadGet() throws Exception {
        Bag<SimpleBagItem, Integer> b = new Bag<>();
        b.add(new SimpleBagItem(0));
        b.get(1);
    }

    @Test
    public void testCopy() throws Exception {
        Bag<SimpleBagItem, Integer> b = new Bag<>();
        b.add(new SimpleBagItem(0));
        b.add(new SimpleBagItem(2));

        Bag<SimpleBagItem, Integer> b2 = new Bag<>(b);
        TestCase.assertEquals(2, b2.size());
        TestCase.assertTrue(b2.contains(0));
        TestCase.assertFalse(b2.contains(1));
        TestCase.assertTrue(b2.contains(2));
    }

    @Test
    public void testEquals() throws Exception {
        Bag<SimpleBagItem, Integer> b = new Bag<>();
        b.add(new SimpleBagItem(0));
        b.add(new SimpleBagItem(2));

        Bag<SimpleBagItem, Integer> b2 = new Bag<>();
        b2.add(new SimpleBagItem(2));
        b2.add(new SimpleBagItem(0));

        Bag<SimpleBagItem, Integer> b3 = new Bag<>();
        b3.add(new SimpleBagItem(2));

        Bag<SimpleBagItem, Integer> b4 = new Bag<>();
        b4.add(new SimpleBagItem(2));
        b4.add(new SimpleBagItem(1));

        TestCase.assertEquals(b, b2);
        TestCase.assertFalse(b.equals(b3));
        TestCase.assertFalse(b.equals(b4));
    }
}
