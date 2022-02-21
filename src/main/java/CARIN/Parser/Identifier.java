package CARIN.Parser;

import java.util.HashMap;

public class Identifier implements Expr{
    private final String identifier;
    private final HashMap<String,Expr> idenKeep;

    public Identifier(String identifier, HashMap<String,Expr> idenKeep){
        this.identifier = identifier;
        this.idenKeep = idenKeep;
    }
    @Override
    public int eval() throws EvalError{
        try {
            return idenKeep.get(identifier).eval();
        }catch (EvalError e) {
        throw new EvalError("Cannot evaluate "+identifier);
    }
    }
}
