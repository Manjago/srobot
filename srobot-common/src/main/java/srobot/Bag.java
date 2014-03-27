package srobot;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bag<E extends BagItem<E, K>, K> {
    private final Map<K, E> items = new HashMap<>();

    public Bag() {
    }

    public Bag(Bag<E, K> bag) {
        items.putAll(bag.items);
    }

    public void add(E item) {
        items.put(item.getKey(), item);
    }

    public Stream<E> asStream() {
        return items.values().stream();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Bag bag = (Bag) o;

        return Arrays.equals(asStream().sorted().toArray(), bag.asStream().sorted().toArray());
    }

    @Override
    public int hashCode() {

        int result = 1;

        for (E item : asStream().sorted().collect(Collectors.toList())) {
            final int prime = 31;
            result = prime * result + (item == null ? 0 : item.hashCode());
        }

        return result;
    }

    public boolean contains(@Nonnull K key) {
        Objects.requireNonNull(key);
        return items.containsKey(key);
    }

    public E get(@Nonnull K key) {
        Objects.requireNonNull(key);
        if (!contains(key)) {
            throw new IllegalArgumentException(String.format("badKey %s", key.toString()));
        }
        return items.get(key);
    }

    public int size() {
        return items.size();
    }
}
