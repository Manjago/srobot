package srobot;

public class ElementCollector {

    private final CellNeighbours cellNeighbours;

    public static final SimplePoint[] SHIFTS = {new SimplePoint(0, 1),
            new SimplePoint(0, -1),
            new SimplePoint(1, 0),
            new SimplePoint(-1, 0)};
    private final Elements elements = new Elements();

    public ElementCollector(CellNeighbours cellNeighbours) {
        this.cellNeighbours = cellNeighbours;
    }

    public Elements getElements() {
        return new Elements(elements);
    }

    public void accept(CellInfo cellInfo) {
        final Element element = new Element(cellInfo);
        elements.add(element);
        tryAdd(element, cellInfo);
    }

    public void accept(ElementCollector other) {
        other.elements.getElementBag().asStream().forEach(elements::add);
    }

    void tryAdd(Element element, CellInfo lastAdded){
        for(SimplePoint shift : SHIFTS){
            SimplePoint pretender = lastAdded.getCoords().add(shift);

            if (cellNeighbours.contains(pretender)){
                CellInfo cellInfoPretender = cellNeighbours.get(pretender);
                Element elementPretender = element.add(cellInfoPretender);

                if (!elements.contains(elementPretender)){
                    elements.add(elementPretender);
                    tryAdd(elementPretender, cellInfoPretender);
                }
            }
        }

    }

}
