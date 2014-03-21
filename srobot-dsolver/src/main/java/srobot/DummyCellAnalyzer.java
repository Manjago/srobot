package srobot;

public class DummyCellAnalyzer implements CellAnalyzer {

    private final Prediction.PredictionType predictionType;

    public DummyCellAnalyzer(Prediction.PredictionType predictionType) {
        this.predictionType = predictionType;
    }

    @Override
    public Prediction makePredict(CellInfo cellInfo) {

        if (predictionType == null){
            return null;
        }

        return new Prediction(cellInfo.getCoords(), predictionType);
    }
}
