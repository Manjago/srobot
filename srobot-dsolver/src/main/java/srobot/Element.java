package srobot;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Element implements BagItem<Element, Element> {
    private final Bag<CellInfo, SimplePoint> cellInfos = new Bag<>();

    public Element(@Nonnull Element element) {
        Objects.requireNonNull(element);

        element.cellInfos.asStream().forEach(cellInfos::add);
    }

    public Element(@Nonnull CellType cellType, int i, int j){
        this(new CellInfo(cellType, new SimplePoint(i, j)));
    }

    public Element(CellInfo cellInfo) {
        cellInfos.add(cellInfo);
    }

    public Element(@Nonnull Collection<CellInfo> cellInfo) {
        Objects.requireNonNull(cellInfo);
        cellInfo.forEach(cellInfos::add);
    }

    public Element(@Nonnull CellInfo... cellInfo) {
        Objects.requireNonNull(cellInfo);
        Arrays.stream(cellInfo).forEach(cellInfos::add);
    }

    public Stream<CellInfo> asStream() {
        return cellInfos.asStream();
    }

    public Element add(@Nonnull CellInfo cellInfo) {
        Objects.requireNonNull(cellInfo);

        Element result = new Element(this);
        result.cellInfos.add(cellInfo);
        return result;
    }

    public Bag<CellInfo, SimplePoint> getCellInfos() {
        return new Bag<>(cellInfos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Element element = (Element) o;

        if (!cellInfos.equals(element.cellInfos)) {
            return false;
        }

        return true;
    }

    public List<SimplePoint> asPoints() {
        return asStream().map(CellInfo::getCoords).sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return asStream()
                .sorted()
                .collect(Collectors.toList())
                .toString();
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
    public int compareTo(@Nonnull Element o) {

        List<SimplePoint> me = asPoints();
        List<SimplePoint> he = o.asPoints();

        int cmp = me.size() - he.size();

        if (cmp != 0) {
            return cmp;
        }

        for (int i = 0; i < me.size(); ++i) {
            int c = me.get(i).compareTo(he.get(i));
            if (c != 0) {
                return c;
            }
        }

        return 0;
    }
}
