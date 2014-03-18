package srobot;

public enum CellType {

    EMPTY('.', State.OPENED), CLOSED('X', State.CLOSED),
    INFO_1('1', State.OPENED), INFO_2('2', State.OPENED), INFO_3('3', State.OPENED), INFO_4('4', State.OPENED), INFO_5('5', State.OPENED), INFO_6('6', State.OPENED), INFO_7('7', State.OPENED), INFO_8('8', State.OPENED),
    QUESTION('?', State.FINISHED),
    BOMB('*', State.FINISHED), ERROR_BOMB('!', State.FINISHED), EXPLODED_BOMB('$', State.FINISHED), FLAG('@', State.CLOSED);

    public enum State{
        OPENED, CLOSED, FINISHED
    }

    public char getDebugChar() {
        return debugChar;
    }

    private final char debugChar;

    public State getState() {
        return state;
    }

    private final State state;

    CellType(char debugChar, State state) {
        this.debugChar = debugChar;
        this.state = state;
    }
}
