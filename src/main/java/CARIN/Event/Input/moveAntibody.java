package CARIN.Event.Input;

public class moveAntibody extends InputEvent{
    int [] location = new int[2];
    int [] destination = new int[2];

    public moveAntibody(){
        super("move");
        currentInput = this;
    }

    public int[] getLocation(){
        return location;
    }

    public int[] getDestination(){
        return destination;
    }


}
