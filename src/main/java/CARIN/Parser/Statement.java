package CARIN.Parser;

import java.util.List;

public class Statement implements Program{
    private final String type;
    private final Expr condition;
    private final Program then;
    private final Program el;
    private final List<Program> block;
    // if constructor
    public Statement(String type,Expr con,Program then, Program el){
        this.type = type;
        this.condition = con;
        this.then = then;
        this.el = el;
        this.block = null;
    }
    // while constructor
    public Statement(String type,Expr con,Program then){
        this.type = type;
        this.condition = con;
        this.then = then;
        this.el = null;
        this.block = null;
    }
    // block constructor
    public Statement(String type,List<Program> block){
        this.type = type;
        this.block = block;
        condition = null;
        then = null;
        el = null;
    }

    public void eval() {
        try {
        Boolean check = null;
        if(condition!=null)
            check = condition.eval() > 0;
        switch (type) {
            case "if":
                if (Boolean.TRUE.equals(check)) {
                    assert then != null;
                    then.eval();
                }
                else {
                    assert el != null;
                    el.eval();
                }
                break;
            case "while":
                while (Boolean.TRUE.equals(check)) {
                    assert then != null;
                    then.eval();
                }
                break;
            case "block":
                assert block != null;
                for(Program inBlock:block){
                    inBlock.eval();
                }
                break;
            default:
                break;
        }
        }catch (EvalError e) {
            throw new EvalError("Cannot evaluate "+type);
        }
    }
}
