package srobot;

import java.util.HashSet;
import java.util.Set;

public class Element {
    private final Set<CellInfo> items = new HashSet<>();
    private final CellInfo source;

    public Element(CellInfo source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Element{" +
                "items=" + items +
                ", source=" + source +
                '}';
    }
}
