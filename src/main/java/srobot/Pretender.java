package srobot;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Pretender {
    public SimplePoint getInTest() {
        return inTest;
    }

    public SimplePoint getInBig() {
        return born.add(inTest);
    }

    private SimplePoint inTest;

    public SimplePoint getBorn() {
        return born;
    }

    private final SimplePoint born;

    public int getMatches() {
        return matches;
    }

    private int matches;

    public void incMatches() {
        ++matches;
    }


    public void setInTest(SimplePoint inTest) {
        this.inTest = inTest;
    }

    public Pretender(SimplePoint inTest, SimplePoint inBig) {
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
