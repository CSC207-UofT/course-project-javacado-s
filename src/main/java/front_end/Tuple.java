package front_end;

public class Tuple<X, Y> {
    public final X x;
    public final Y y;

    /**
     * Constructor for Tuple
     * @param x first element
     * @param y second element
     */
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for first element
     * @return first element
     */
    public X getFirst() {
        return x;
    }

    /**
     * Getter for second element
     * @return second element
     */
    public Y getSecond() {
        return y;
    }
}