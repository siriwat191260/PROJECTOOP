package CARIN.Event.Output;

public class antiCreditOutput extends OutputEvent{
    int credit;

    public antiCreditOutput(int credit){
        super("credit");
        this.credit = credit;
    }
}
