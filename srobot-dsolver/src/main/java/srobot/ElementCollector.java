package srobot;

import java.util.function.Consumer;

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

    private String level(int i){
        return "";
    }

    public void accept(CellInfo cellInfo) {
        final Element element = new Element(cellInfo);
        elements.add(element);
        System.out.println(String.format("add %s, now elements size %d %s", element, elements.size(), elements));
        tryAdd(element, 0);
    }

    public void accept(ElementCollector other) {
        other.elements.getElementBag().asStream().forEach(elements::add);
    }

    void tryAdd(Element element, int debugLevel){

        int dl = debugLevel + 4;

        System.out.println(StringUtils.padLeft(String.format("try process %s", element), dl));

        element.asStream().forEach(cellInfo -> {
            for(SimplePoint shift : SHIFTS){
                SimplePoint pretender = cellInfo.getCoords().add(shift);


                System.out.println(StringUtils.padLeft(String.format("shift %s to %s by %s", cellInfo, pretender, shift), dl));

                if (cellNeighbours.containsOpened(pretender)){
                    System.out.println(StringUtils.padLeft(String.format("check pretender %s for element %s", pretender, element), dl));
                    CellInfo cellInfoPretender = cellNeighbours.get(pretender);
                    Element elementPretender = element.add(cellInfoPretender);

                    if (!elements.contains(elementPretender)){
                        elements.add(elementPretender);
                        System.out.println(StringUtils.padLeft(String.format("add %s, now elements size %d %s", elementPretender, elements.size(), elements), dl));
                        tryAdd(elementPretender, dl);
                    } else {
                        System.out.println(StringUtils.padLeft(String.format("elements %s already contain %s", elements, elementPretender), dl));
                    }
                }
            }


        });


    }

}
