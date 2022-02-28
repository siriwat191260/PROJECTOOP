package CARIN.Parser;

import java.util.HashMap;

public class Identifier implements Expr{
    private final String identifier;
    private final HashMap<String,Integer> idKeep;

    public Identifier(String identifier, HashMap<String,Integer> idKeep){
        this.identifier = identifier;
        this.idKeep = idKeep;
    }
    @Override
    public int eval() throws EvalError{
        try {
            return idKeep.get(identifier);
        }catch (EvalError e) {
        throw new EvalError("Cannot evaluate "+identifier);
    }
    }
}
