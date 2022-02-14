package CARIN.Parser;

public class SyntaxError extends RuntimeException{

    public SyntaxError(){
        super("Syntax Error");
    }

    public SyntaxError(String message){
        super(message);
    }

}