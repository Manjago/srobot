package srobot;

public class Prediction {

    public enum PredictionType {
        FREE, MINE
    }

    private final SimplePoint simplePoint;
    private final PredictionType predictionType;

    public Prediction(SimplePoint simplePoint, PredictionType predictionType) {
        this.simplePoint = simplePoint;
        this.predictionType = predictionType;
    }

    public SimplePoint getSimplePoint() {
        return simplePoint;
    }

    public PredictionType getPredictionType() {
        return predictionType;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "simplePoint=" + simplePoint +
                ", predictionType=" + predictionType +
                '}';
    }
}
