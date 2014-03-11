package srobot;

public class Cells {
    private final CellType[][] cells;

    public Cells(int width, int height) {
        this.cells = new CellType[width][height];
    }
}
