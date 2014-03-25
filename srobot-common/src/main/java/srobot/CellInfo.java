package srobot;

public class CellInfo implements BagItem<CellInfo, SimplePoint> {
    private final CellType cellType;
    private final SimplePoint coords;

    public CellType getCellType() {
        return cellType;
    }

    public SimplePoint getCoords() {
        return coords;
    }

    public CellInfo(CellType cellType, SimplePoint coords) {
        this.cellType = cellType;
        this.coords = coords;
    }

    @Override
    public String toString() {
        return "CellInfo{" +
                "cellType=" + cellType +
                ", coords=" + coords +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        CellInfo cellInfo = (CellInfo) o;

        if (!coords.equals(cellInfo.coords)) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        return coords.hashCode();
    }

    @Override
    public int compareTo(CellInfo o) {
        return getCoords().compareTo(o.getCoords());
    }

    @Override
    public SimplePoint getKey() {
        return getCoords();
    }
}
