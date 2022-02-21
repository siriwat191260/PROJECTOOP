package CARIN.Parser;

import java.util.regex.Pattern;
import java.lang.*;

public class Tokenizer {
    private String src;
    private String next;
    private int pos;

    public Tokenizer(String src) {
        this.src = src;
        pos = 0;
        computeNext();
    }

    private boolean isCharacter(char ch) {
        return Pattern.matches("[a-zA-z]", new StringBuilder(1).append(ch));
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%' || ch == '(' || ch == ')'
                || ch == '{' || ch == '}' || ch == '^' || ch == '=';
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private boolean isSpace(char ch) {
        return ch == ' ';
    }

    private void computeNext() {
        StringBuilder s = new StringBuilder();

        while (pos < src.length()) {
            char ch = src.charAt(pos);

            if (isDigit(ch)) {
                s.append(ch);
                for (pos++; pos < src.length() && Character.isDigit(src.charAt(pos)); pos++) {
                    s.append(src.charAt(pos));
                }
                break;
            } else if (isOperator(ch)) {
                s.append(ch);
                pos++;
                break;
            } else if (isCharacter(ch)) {
                s.append(ch);
                for (pos++; pos < src.length() && isCharacter(src.charAt(pos)); pos++) {
                    s.append(src.charAt(pos));
                }
                break;
            } else if (isSpace(ch)) {
                pos++;
            } else
                throw new SyntaxError("Unknown character: " + ch);
        }

        next = s.toString();
    }

    public String peek() {
        return next;
    }

    public boolean peek(String s) {
        return peek().equals(s);
    }

    public String consume() {
        String result = next;
        if (pos < src.length())
            computeNext();
        else
            next = "";
        return result;
    }

    public void consume(String s) throws SyntaxError {
        if (peek(s))
            consume();
        else
            throw new SyntaxError(peek());
    }

}

