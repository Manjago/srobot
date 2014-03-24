package srobot;

public class ElementCollector {

    public Elements getElements() {
        return new Elements(elements);
    }

    private final Elements elements = new Elements();

    public void accept(CellInfo cellInfo) {

    }

    public void accept(ElementCollector other) {

    }

}
