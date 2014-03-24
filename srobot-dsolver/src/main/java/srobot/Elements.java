package srobot;

public class Elements {
    private final Bag<Element> elementBag;

    public Elements() {
        elementBag = new Bag<>();
    }

    public Elements(Elements elements) {
        this.elementBag = elements.getElementBag();
    }

    public Bag<Element> getElementBag() {
        return new Bag<>(elementBag);
    }
}
