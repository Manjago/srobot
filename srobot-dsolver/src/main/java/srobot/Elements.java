package srobot;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Elements {
    private final Bag<Element, Element> elements;

    public Elements() {
        elements = new Bag<>();
    }

    public Stream<Element> asStream(){
        return elements.asStream();
    }

    public Elements(@Nonnull Element... element) {
        this();

        Objects.requireNonNull(element);
        Arrays.stream(element).forEach(elements::add);
    }

    public Elements(@Nonnull Collection<Element> element) {
        this();

        Objects.requireNonNull(element);
        element.forEach(elements::add);
    }

    public Elements(@Nonnull Element element) {
        this();

        Objects.requireNonNull(element);
        elements.add(element);
    }

    public Elements(Elements elements) {
        this.elements = elements.getElements();
    }

    public void add(@Nonnull Element element) {
        Objects.requireNonNull(element);
        elements.add(element);
    }

    public Bag<Element, Element> getElements() {
        return new Bag<>(elements);
    }

    public boolean contains(Element elementPretender) {
        return elements.contains(elementPretender);
    }

    @Override
    public String toString() {
        return elements.asStream().sorted().collect(Collectors.toList()).toString();
    }

    public int size() {
        return elements.size();
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

        return this.elements.equals(elements.elements);

    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }
}
