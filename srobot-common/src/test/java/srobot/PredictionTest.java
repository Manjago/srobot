package srobot;

import junit.framework.TestCase;
import org.junit.Test;

public class PredictionTest {
    @Test
    public void testEquals() throws Exception {
        Prediction p = new Prediction(SimplePoint.ZERO, Prediction.PredictionType.MINE);
        TestCase.assertEquals(new Prediction(SimplePoint.ZERO, Prediction.PredictionType.MINE), p);
    }

}
