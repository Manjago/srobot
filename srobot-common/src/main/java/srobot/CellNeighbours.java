package srobot;

import srobot.lamelinq.Linqable;
import srobot.lamelinq.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CellNeighbours implements Linqable<CellInfo>{

    private final List<CellInfo> data = new ArrayList<>();

    public Stream<CellInfo> asStream(){
        return data.stream();
    }

    public CellNeighbours(Cells cells, SimplePoint start) {
        for (int iShift = -1; iShift <= 1; ++iShift){
            for (int jShift = -1; jShift <= 1; ++jShift){

                final int i = start.getX() + iShift;
                final int j = start.getY() + jShift;
                if (cells.isCorrect(i, j)){
                    data.add(cells.get(i, j));
                }

            }
        }

    }

    @Override
    public CellInfo findFirst(Predicate<CellInfo> predicate) {

        for(CellInfo cellInfo : data){
            if (predicate.test(cellInfo)){
                return cellInfo;
            }
        }

        return null;
    }

    @Override
    public List<CellInfo> findAll(Predicate<CellInfo> predicate) {
        List<CellInfo> result = new ArrayList<>();
        for(CellInfo cellInfo : data){
            if (predicate.test(cellInfo)){
               result.add(cellInfo);
            }
        }

        return result;
    }
}
