package srobot;

public class Elements {
    // todo сделать ключ
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
}
