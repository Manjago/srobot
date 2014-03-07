package srobot;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Pretender {
    public LamePoint getInTest() {
        return inTest;
    }

    public LamePoint getInBig() {
        return born.add(inTest);
    }

    private LamePoint inTest;

    public LamePoint getBorn() {
        return born;
    }

    private final LamePoint born;

    public int getMatches() {
        return matches;
    }

    private int matches;

    public void incMatches() {
        ++matches;
    }


    public void setInTest(LamePoint inTest) {
        this.inTest = inTest;
    }

    public Pretender(LamePoint inTest, LamePoint inBig) {
        this.inTest = inTest;
        this.born = inBig;
        matches = 1;
    }

    @Override
    public String toString() {
        return "Pretender{" +
                "inTest=" + inTest +
                ", inBig=" + getInBig() +
                ", born=" + born +
                ", matches=" + matches +
                '}';
    }
}
