package CARIN.Parser;

public class Number implements Expr{
    int val;

    public Number(int val) {
        this.val = val;
    }
    @Override
    public int eval() {
            return val;
        }
}

