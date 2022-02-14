package CARIN.Parser;

public class Statement implements Program{
    String type;
    Expr condition;
    Program then;
    Program el;
    // if constructor
    public Statement(String type,Expr con,Program then, Program el){
        this.type = type;
        this.condition = con;
        this.then = then;
        this.el = el;
    }
    // while constructor
    public Statement(String type,Expr con,Program then){
        this.type = type;
        this.condition = con;
        this.then = then;
    }

    public void eval() {
        Boolean check;
        check = condition.eval() > 0;
        if (condition.equals("if")) {
            if (check) then.eval();
            else el.eval();
        }else if(condition.equals("while")){
            while (check){
                then.eval();
            }
        }
    }
}
