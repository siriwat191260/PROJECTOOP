package CARIN.Parser;

import java.util.HashMap;
import java.util.Random;

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
            if(identifier.equals("random")){
                Random random = new Random();
                return random.nextInt(99);
            }
            return idKeep.get(identifier);
        }catch (EvalError e) {
        throw new EvalError("Cannot evaluate "+identifier);
    }
    }
}
