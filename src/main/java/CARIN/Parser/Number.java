package CARIN.Parser;

public class Number implements Expr{
    private final int val;

    public Number(int val) {
        this.val = val;
    }
    @Override
    public int eval() {
            return val;
        }
}

