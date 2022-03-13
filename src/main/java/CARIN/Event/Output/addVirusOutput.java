package CARIN.Event.Output;

public class addVirusOutput extends OutputEvent{
    private int[] location;

    public addVirusOutput(){
        super("add");
        outputEvents.add(this);
    }
}
