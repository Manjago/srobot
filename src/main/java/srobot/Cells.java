package srobot;

public class Cells {
    private final CellType[][] cells;
    private final int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private final int height;

    public Cells(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        this.cells = new CellType[width][height];
        this.width = width;
        this.height = height;
    }

    public String debugLine(int line) {

        if (line < 0 || line >= height){
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < width; ++i){
            sb.append(cells[i][line].getDebugChar());
        }

        return sb.toString();
    }

    public void put(int i, int j, CellType cellType) {
        cells[i][j] = cellType;
    }
}
