package srobot;

import srobot.lamelinq.Linqable;
import srobot.lamelinq.Predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cells implements Linqable<CellInfo> {
    private final Map<SimplePoint, CellType> cells;
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
        this.cells = new HashMap<>();
        this.width = width;
        this.height = height;

    }

    public String debugLine(int line) {

        if (line < 0 || line >= height){
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < width; ++i){
            sb.append(get(i, line).getDebugChar());
        }

        return sb.toString();
    }

    public boolean isCorrect(int i, int j){
        return (i >=0 && i < width && j >=0 && j < height );
    }

    public void put(int i, int j, CellType cellType) {
        if (!isCorrect(i, j)){
            throw new AppException(String.format("Bad put for %d %d", i, j));
        }
        cells.put(new SimplePoint(i, j), cellType);
    }

    public CellType get(int i, int j) {
        if (isCorrect(i, j)){
            return cells.get(new SimplePoint(i, j));
        }
        throw new AppException(String.format("bad get %d %d for cells %d %d", i, j, width, height));
    }

    @Override
    public CellInfo findFirst(Predicate<CellInfo> predicate) {
        for(int i = 0; i < getWidth(); ++i){
            for (int j = 0; j < getHeight(); ++j){

                final CellInfo cellInfo = new CellInfo(get(i, j), new SimplePoint(i, j));
                if (predicate.test(cellInfo)){
                    return cellInfo;
                }
            }
        }
        return null;
    }

    @Override
    public List<CellInfo> findAll(Predicate<CellInfo> predicate) {
        List<CellInfo> result = new ArrayList<>();
        for(int i = 0; i < getWidth(); ++i){
            for (int j = 0; j < getHeight(); ++j){

                final CellInfo cellInfo = new CellInfo(get(i, j), new SimplePoint(i, j));
                if (predicate.test(cellInfo)){
                    result.add(cellInfo);
                }
            }
        }
        return result;
    }
}
