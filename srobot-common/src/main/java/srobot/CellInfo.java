package srobot;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CellInfo implements BagItem<CellInfo, SimplePoint> {
    private final CellType cellType;
    private final SimplePoint coords;

    public CellType getCellType() {
        return cellType;
    }

    public SimplePoint getCoords() {
        return coords;
    }

    public CellInfo(CellType cellType, int i, int j) {
        this(cellType, new SimplePoint(i, j));
    }

    public CellInfo(CellType cellType, SimplePoint coords) {
        this.cellType = cellType;
        this.coords = coords;
    }

    @Override
    public String toString() {
        return  coords.strKey() + "-" +  cellType;
    }

    @Override
    public SimplePoint getKey() {
        return getCoords();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        CellInfo cellInfo = (CellInfo) o;

        if (cellType != cellInfo.cellType) {return false;}
        if (!coords.equals(cellInfo.coords)) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        int result = cellType.hashCode();
        final int prime = 31;
        result = prime * result + coords.hashCode();
        return result;
    }

    @Override
    public int compareTo(@Nonnull CellInfo o) {

        Objects.requireNonNull(o);

        int cmp = getCoords().compareTo(o.getCoords());
        if (cmp != 0){
            return cmp;
        }
        return getCellType().compareTo(o.getCellType());
    }
}
