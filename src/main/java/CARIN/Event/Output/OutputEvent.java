package CARIN.Event.Output;
import CARIN.Event.Event;
import java.util.LinkedList;
import java.util.Queue;

public class OutputEvent extends Event {
    public static Queue<OutputEvent> outputEvents = new LinkedList<>();
    private String action;

    public OutputEvent(String action){
        super("input");
        this.action = action;
    }


}
