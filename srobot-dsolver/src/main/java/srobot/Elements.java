package srobot;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Elements {
    private final Bag<Element, Element> elementBag;

    public Elements() {
        elementBag = new Bag<>();
    }

    public Elements(@Nonnull Collection<Element> element) {
        this();

        Objects.requireNonNull(element);
        element.forEach(elementBag::add);
    }

    public Elements(@Nonnull Element element) {
        this();

        Objects.requireNonNull(element);
        elementBag.add(element);
    }

    public Elements(Elements elements) {
        this.elementBag = elements.getElementBag();
    }

    public void add(@Nonnull Element element) {
        Objects.requireNonNull(element);
        elementBag.add(element);
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

    public int size() {
        return elementBag.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Elements elements = (Elements) o;

        return elementBag.equals(elements.elementBag);

    }

    @Override
    public int hashCode() {
        return elementBag.hashCode();
    }
}
