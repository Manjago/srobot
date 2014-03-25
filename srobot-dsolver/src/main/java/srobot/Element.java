package srobot;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

public class Element implements BagItem<Element, Element> {
    private final Bag<CellInfo, SimplePoint> cellInfos = new Bag<>();

    public Element(@Nonnull Element element) {
        Objects.requireNonNull(element);

        element.cellInfos.asStream().forEach(cellInfos::add);
    }

    public Element add(@Nonnull CellInfo cellInfo){
        Objects.requireNonNull(cellInfo);

        Element result = new Element(this);
        result.cellInfos.add(cellInfo);
        return result;
    }

    public Element(CellInfo cellInfo) {
        cellInfos.add(cellInfo);
    }

    public Bag<CellInfo, SimplePoint> getCellInfos() {
        return new Bag<>(cellInfos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Element element = (Element) o;

        if (!cellInfos.equals(element.cellInfos)) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        return cellInfos.hashCode();
    }

    @Override
    public Element getKey() {
        return this;
    }

    @Override
    public int compareTo(Element o) {
        //todo implement
        return 0;
    }
}
