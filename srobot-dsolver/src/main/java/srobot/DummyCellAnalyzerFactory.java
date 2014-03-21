package srobot;

public class DummyCellAnalyzerFactory implements CellAnalyzerFactory {

    private final Prediction.PredictionType predictionType;

    public DummyCellAnalyzerFactory(Prediction.PredictionType predictionType) {
        this.predictionType = predictionType;
    }


    @Override
    public CellAnalyzer create() {
        return new DummyCellAnalyzer(predictionType);
    }
}
