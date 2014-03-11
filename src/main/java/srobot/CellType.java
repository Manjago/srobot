package srobot;

public enum CellType {
    OPENED('.'), CLOSED('X'),
    INFO_1('1'), INFO_2('2'), INFO_3('3'), INFO_4('4'), INFO_5('5'), INFO_6('6'), INFO_7('7'), INFO_8('8'),
    QUESTION('?'),
    BOMB('*'), ERROR_BOMB('!'), EXPLODED_BOMB('$'), FLAG('@');

    public char getDebugChar() {
        return debugChar;
    }

    private final char debugChar;

    CellType(char debugChar) {
        this.debugChar = debugChar;
    }
}
