package srobot;

public class BoardStateIcon {
    private final BoardState boardState;
    private final SimplePoint coord;

    public BoardStateIcon(BoardState boardState, SimplePoint coord) {
        this.boardState = boardState;
        this.coord = coord;
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public SimplePoint getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return "BoardStateIcon{" +
                "boardState=" + boardState +
                ", coord=" + coord +
                '}';
    }
}
