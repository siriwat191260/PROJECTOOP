package CARIN.Event.Input;

public class addAntibody extends InputEvent {
    int [] location = new int[2];

    public addAntibody(){
        super("add");
        currentInput = this;
    }

    public int[] getLocation(){
        return  location;
    }
}
