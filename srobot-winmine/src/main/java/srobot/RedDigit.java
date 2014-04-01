package srobot;

public enum RedDigit {
    EMPTY(null), DEFIS(null), DIGIT1(1),
    DIGIT2(2), DIGIT3(3), DIGIT4(4),
    DIGIT5(5), DIGIT6(6), DIGIT7(7),
    DIGIT8(8), DIGIT9(9) ,DIGIT0(0);

    public Integer getValue() {
        return value;
    }

    private final Integer value;

    RedDigit(Integer value) {
        this.value = value;
    }
}
