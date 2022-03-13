package CARIN.Event.Output;

public class deadOutput extends OutputEvent {
    private int[] location;

    public deadOutput(){
        super("dead");
        outputEvents.add(this);
    }
}
