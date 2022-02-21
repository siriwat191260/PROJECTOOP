package CARIN.Model;

public class Antibody extends HostImp{

    public Antibody(String geneticCode, int health, int attackDamage,int gain,int moveCost,
                    int[] location,Body body) {
        super(geneticCode, health, attackDamage, gain, location, body);
        this.moveCost = moveCost;
    }

    @Override
    public void cantMove() {
        System.out.println("Cannot move a antibody!");
    }

    @Override
    public int getType(){
        return 2;
    }

    @Override
    public void isDeath(Host virus){
        setStatus("death", virus);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

}
