package CARIN.Event.Output;

public class gainOutput extends OutputEvent {
    private int[] location;

    public gainOutput(int[] location){
        super("gain");
        this.location = location;
    }
}
