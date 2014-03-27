package srobot;

import javax.annotation.Nonnull;
import java.util.Objects;

public class TestBagItem implements BagItem<TestBagItem, Integer> {

    private final int data;

    public TestBagItem(int data) {
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

        TestBagItem that = (TestBagItem) o;

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
    public int compareTo(@Nonnull TestBagItem o) {
        Objects.requireNonNull(o);

        return Integer.valueOf(data).compareTo(o.data);
    }
}
