package srobot;

import junit.framework.TestCase;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void testPadRight() throws Exception {
        TestCase.assertEquals("test    ", StringUtils.padRight("test", 4));
    }

    @Test
    public void testPadLeft() throws Exception {
        TestCase.assertEquals("    test", StringUtils.padLeft("test", 4));
    }

    @Test
    public void testPadRight0() throws Exception {
        TestCase.assertEquals("test", StringUtils.padRight("test", 0));
    }

    @Test
    public void testPadLeft0() throws Exception {
        TestCase.assertEquals("test", StringUtils.padLeft("test", 0));
    }

    @Test
    public void testPadRightM() throws Exception {
        TestCase.assertEquals("test", StringUtils.padRight("test", -1));
    }

    @Test
    public void testPadLeftM() throws Exception {
        TestCase.assertEquals("test", StringUtils.padLeft("test", -1));
    }
}
