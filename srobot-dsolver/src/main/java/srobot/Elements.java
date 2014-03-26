package srobot;

import java.util.List;
import java.util.stream.Collectors;

public class Elements {
    private final Bag<Element, Element> elementBag;

    public Elements() {
        elementBag = new Bag<>();
    }

    public void add(Element element){
        elementBag.add(element);
    }

    public Elements(Elements elements) {
        this.elementBag = elements.getElementBag();
    }

    public Bag<Element, Element> getElementBag() {
        return new Bag<>(elementBag);
    }

    public boolean contains(Element elementPretender) {
        return elementBag.contains(elementPretender);
    }

    @Override
    public String toString() {
        List<Element> list = elementBag.asStream().sorted().collect(Collectors.toList());
        return list.toString();
    }
}
