package CARIN.Event.Input;

public class addAntibody extends InputEvent {
    int [] location = new int[2];
    int geneNum;

    public addAntibody(){
        super("add");
        currentInput = this;
    }

    public int[] getLocation(){
        return  location;
    }
    public int getGeneNum() { return geneNum; }
}
