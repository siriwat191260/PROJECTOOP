package CARIN.Parser;

import CARIN.Model.Host;

public class Command implements Program {

    String type;
    String direction;
    Expr expression;
    Host host;

    public Command(String type, String direction, Host host){
        this.type = type;
        this.direction = direction;
        this.host = host;
    }
    public Command(String type, Expr expr){
        this.type = type;
        this.expression = expr;
    }

    @Override
    public void eval() {
        if(type.equals("move")){
            host.move(direction);
        }else if(type.equals("shoot")){
            host.shoot(direction);
        }else{
            // assignment statement
            // type = expression.eval();
        }
    }
}
