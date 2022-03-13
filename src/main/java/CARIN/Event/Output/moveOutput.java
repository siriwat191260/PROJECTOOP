package CARIN.Event.Output;

public class moveOutput extends OutputEvent {
    private int[] location;
    private int[] destination;

    public moveOutput(int[] location, int[] destination) {
        super("move");
        this.location = location;
        this.destination =destination;
    }

}
