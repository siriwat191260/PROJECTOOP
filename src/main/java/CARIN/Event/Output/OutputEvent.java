package CARIN.Event.Output;
import CARIN.Event.Event;

public class OutputEvent extends Event {
    private String action;

    public OutputEvent(String action){
        super("input");
        this.action = action;
    }

}
