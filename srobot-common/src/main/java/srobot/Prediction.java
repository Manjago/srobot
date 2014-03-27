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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Prediction that = (Prediction) o;

        if (predictionType != that.predictionType) {return false;}
        if (!simplePoint.equals(that.simplePoint)) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        int result = simplePoint.hashCode();
        final int prime = 31;
        result = prime * result + predictionType.hashCode();
        return result;
    }
}
