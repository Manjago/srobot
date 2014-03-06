package srobot;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Pretender {
    public LamePoint getInTest() {
        return inTest;
    }

    public LamePoint getInBig() {
        return inBig;
    }

    private LamePoint inTest;
    private LamePoint inBig;
    private int matches;

    public void inMatches() {
        ++matches;
    }


    public void setInTest(LamePoint inTest) {
        this.inTest = inTest;
    }

    public void setInBig(LamePoint inBig) {
        this.inBig = inBig;
    }

    public Pretender(LamePoint inTest, LamePoint inBig) {
        this.inTest = inTest;
        this.inBig = inBig;
        matches = 1;
    }

    @Override
    public String toString() {
        return "Pretender{" +
                "inTest=" + inTest +
                ", inBig=" + inBig +
                ", matches=" + matches +
                '}';
    }
}
