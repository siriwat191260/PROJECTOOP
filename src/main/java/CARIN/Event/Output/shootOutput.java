package CARIN.Event.Output;

public class shootOutput extends OutputEvent{
    private int[] location;
    private int[] destination;

    public shootOutput(int[] location, int[] destination) {
        super("shoot");
        this.location = location;
        this.destination =destination;
    }
}
