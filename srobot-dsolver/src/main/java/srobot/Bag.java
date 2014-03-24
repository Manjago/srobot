package srobot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Bag<E> {
    private final Set<E> items = new HashSet<>();

    public Bag() {
    }

    public Bag(Bag<E> bag) {
        items.addAll(bag.items);
    }

    public void add(E item) {
        items.add(item);
    }

    public Stream<E> asStream() {
        return items.stream();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Bag bag = (Bag) o;

        return Arrays.equals(asStream().sorted().toArray(), bag.asStream().sorted().toArray());
    }

    @Override
    public int hashCode() {

        int result = 1;

        for (E item : items) {
            final int prime = 31;
            result = prime * result + (item == null ? 0 : item.hashCode());
        }

        return result;
    }
}
