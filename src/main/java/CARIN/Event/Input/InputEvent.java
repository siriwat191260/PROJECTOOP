package CARIN.Event.Input;
import CARIN.Event.Event;

public class InputEvent extends Event {
    public InputEvent currentInput;
    private final String action;

    public InputEvent(String action){
        super("input");
        this.action = action;
    }

}
