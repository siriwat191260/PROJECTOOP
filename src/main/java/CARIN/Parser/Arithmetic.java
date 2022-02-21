package CARIN.Parser;

public class Arithmetic implements Expr {
    private final Expr left;
    private final Expr right;
    private final String operator;

    public Arithmetic(Expr left,String operator,Expr right){
        this.left =left;
        this.operator = operator;
        this.right = right;
    }
    @Override
    public int eval() throws EvalError{
        int lv = this.left.eval();
        int rv = this.right.eval();
        if (operator.equals("+"))
            return lv + rv;
        if (operator.equals("-"))
            return lv - rv;
        if (operator.equals("*"))
            return lv * rv;
        if (operator.equals("/"))
            return lv / rv;
        if (operator.equals("%"))
            return lv % rv;
        if (operator.equals("^"))
            return (int)Math.pow(lv,rv);
        throw new EvalError("Unknown operator: " + operator);
    }
}
