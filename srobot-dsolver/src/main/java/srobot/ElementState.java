package srobot;

public class ElementState {
    private int min;
    private int max;

    public ElementState() {
        min = 0;
        max = 0;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "ElementState{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

}
