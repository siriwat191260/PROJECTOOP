package CARIN.Parser;

import CARIN.Model.Host;

import java.util.HashMap;
import java.util.Map;

public class Command implements Program {

    private final String type;
    private final String direction;
    private final Expr expression;
    private final Host host;
    private final Map<String, Expr> identifier;

    public Command(String type, String direction, Host host){
        this.type = type;
        this.direction = direction;
        this.host = host;
        expression = null;
        identifier = null;
    }
    public Command(String type, Expr expr, HashMap<String, Expr> identifier){
        this.type = type;
        this.expression = expr;
        this.identifier = identifier;
        direction = null;
        host = null;
    }

    @Override
    public void eval() {
        try {
            if (type.equals("move")) {
                assert host != null;
                host.move(direction);
//            System.out.println("move"+direction);
            } else if (type.equals("shoot")) {
                assert host != null;
                host.shoot(direction);
//            System.out.println("shoot"+direction);
            } else {
                // assignment statement
                assert identifier != null;
                identifier.put(type, expression);
            }
        }catch (EvalError e) {
            throw new EvalError("Cannot evaluate "+type);
        }
    }
}
