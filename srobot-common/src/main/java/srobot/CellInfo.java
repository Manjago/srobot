package srobot;

public class CellInfo {
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
}
