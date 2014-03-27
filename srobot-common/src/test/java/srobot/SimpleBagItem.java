package srobot;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SimpleBagItem implements BagItem<SimpleBagItem, Integer> {

    private final int data;

    public SimpleBagItem(int data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SimpleBagItem that = (SimpleBagItem) o;

        return data == that.data;

    }

    @Override
    public int hashCode() {
        return data;
    }

    @Override
    public Integer getKey() {
        return data;
    }

    @Override
    public int compareTo(@Nonnull SimpleBagItem o) {
        Objects.requireNonNull(o);

        return Integer.valueOf(data).compareTo(o.data);
    }
}
