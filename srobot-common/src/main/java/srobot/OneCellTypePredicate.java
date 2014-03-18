package srobot;

import srobot.lamelinq.Predicate;

public class OneCellTypePredicate implements Predicate<CellInfo> {

    private final CellType cellType;

    public OneCellTypePredicate(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public boolean test(CellInfo item) {
        return cellType == null || cellType.equals(item.getCellType());
    }
}
