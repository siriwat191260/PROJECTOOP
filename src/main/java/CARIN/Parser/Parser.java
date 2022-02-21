package CARIN.Parser;

import CARIN.Model.Host;
import CARIN.Model.HostImp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    private Tokenizer tkz;
    private Host host;
    private HashMap<String, Expr> idenKeep;
    private List<Program> statement;

    public Parser(String src, Host host){
        this.tkz = new Tokenizer(src);
        this.host = host;
        idenKeep = host.getIdentifier();
        this.statement = new LinkedList<>();
        parse();
    }

    public void parse() throws SyntaxError{
        parseProgram();
        if(!tkz.peek().equals(""))
            throw new SyntaxError();
    }

    public boolean isNumber(String s) throws NumberFormatException{
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public void parseProgram() {
        while(!tkz.peek(""))
            this.statement.add(parseStatement());
    }
    // Statement → Command | BlockStatement | IfStatement | WhileStatement
    public Program parseStatement() throws SyntaxError{
        Program s;
            if (tkz.peek("if")) s = parseIf();
            else if (tkz.peek("while")) s = parseWhile();
            else if (tkz.peek("{")) s = parseBlock();
            else s = parseCommand();
        return s;
    }
    // BlockStatement → { Statement* }
    public Program parseBlock() throws SyntaxError{
        List<Program> block = new LinkedList<>();
        tkz.consume("{");
        while (!tkz.peek("}")) {
            block.add(parseStatement());
            /*block to be fixed*/
        }
        tkz.consume("}");
        return new Statement("block", block);
    }
    // IfStatement → if ( Expression ) then Statement else Statement
    public Program parseIf() throws SyntaxError{
        tkz.consume("if");
        tkz.consume("(");
        Expr condition = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Program then = parseStatement();
        tkz.consume("else");
        Program el = parseStatement();
        return new Statement("if", condition, then, el);
    }
    // WhileStatement → while ( Expression ) Statement
    public Program parseWhile() throws SyntaxError{
        tkz.consume("while");
        tkz.consume("(");
        Expr condition = parseExpression();
        tkz.consume(")");
        Program then = parseStatement();
        return new Statement("while", condition, then);
    }
    // Command → AssignmentStatement | ActionCommand
    // AssignmentStatement → <identifier> = Expression
    public Program parseCommand() throws SyntaxError{
        Program s;
        String identifier;
        if(tkz.peek("move") || tkz.peek("shoot")){
            s = parseAction();
        }else {
            identifier = tkz.consume();
            tkz.consume("=");
            s = new Command(identifier, parseExpression(), idenKeep);
        }
        return s;
    }
    // ActionCommand → MoveCommand | AttackCommand
    // MoveCommand → move Direction
    // AttackCommand → shoot Direction
    public Program parseAction() throws SyntaxError{
        Program action = null;
            if(tkz.peek("move")){
                tkz.consume();
                String dir = parseDirection();
                action = new Command("move", dir, host);
            }else if(tkz.peek("shoot")){
                tkz.consume();
                String dir = parseDirection();
                action = new Command("shoot", dir, host);
            }
            return action;
    }
    // Direction → left | right | up | down | upleft | upright | downleft | downright
    public String parseDirection() throws SyntaxError{
        return tkz.consume();
    }
    // Expression → Expression + Term | Expression - Term | Term
    public Expr parseExpression() throws SyntaxError{
        Expr e = parseTerm();

        while (tkz.peek("+") || tkz.peek("-")){
            if (tkz.peek("+")){
                tkz.consume();
                e = new Arithmetic(e,"+",parseTerm());
            }else{
                tkz.consume();
                e = new Arithmetic(e, "-", parseTerm());
            }
        }
        return e;
    }
    // Term → Term * Factor | Term / Factor | Term % Factor | Factor
    public Expr parseTerm() throws SyntaxError{
        Expr e = parseFactor();
        while(tkz.peek("*") || tkz.peek("/") || tkz.peek("%")){
            if(tkz.peek("*")){
                tkz.consume();
                e = new Arithmetic(e,"*",parseFactor());
            }else if(tkz.peek("/")){
                tkz.consume();
                e = new Arithmetic(e,"/",parseFactor());
            }else{
                tkz.consume();
                e = new Arithmetic(e,"%",parseFactor());
            }
        }
        return  e;
    }
    // Factor → Power ^ Factor | Power
    public Expr parseFactor() throws SyntaxError{
        Expr e = parsePower();
        while(tkz.peek("^")){
                tkz.consume("^");
                e = new Arithmetic(e,"^",parsePower());
        }
        return  e;
    }
    // Power → <number> | <identifier> | ( Expression ) | SensorExpression
    public Expr parsePower() throws SyntaxError{
        Expr e;

            if (isNumber(tkz.peek())) {
                return new Number(Integer.parseInt(tkz.consume()));
            } else if(tkz.peek("(") ) {
                tkz.consume();
                e = parseExpression();
                tkz.consume(")");
            } else if(tkz.peek("antibody") || tkz.peek("virus")
            || tkz.peek("nearby")) {
                e = parseSensor();
            }else
                e = new Identifier(tkz.consume(), idenKeep);

        return e;
    }
    // SensorExpression → virus | antibody | nearby Direction
    public Expr parseSensor() throws SyntaxError{
        if(tkz.peek("virus")){
            tkz.consume();
            return new SensorExp("virus",host);
        }else if(tkz.peek("antibody")) {
            tkz.consume();
            return new SensorExp("antibody", host);
        }else if(tkz.peek("nearby")){
            tkz.consume();
            String dir = parseDirection();
            return new SensorExp(dir, host);
        }else throw new SyntaxError(tkz.consume());
    }

    public void eval(){
        for(Program each:statement){
            each.eval();
        }
    }

    public static void main(String[] args) {
//         example genetic code in spec doc
        String gene = "virusLoc = 0 " +
                "if (random / 10 - 2^2) " +
                "then move up else move down";
//        Parser parser = new Parser(gene, new HostImp());
//        parser.eval();
    }

}
