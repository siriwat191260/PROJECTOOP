package CARIN.Model;

public class Virus extends HostImp {

    public Virus(String geneticCode, int health, int attackDamage,int gain, int[] location, Body body) {
        super(geneticCode, health, attackDamage, gain, location, body);
    }

    @Override
    public void cantMove() {
        System.out.println("Cannot move a virus!");
    }

    @Override
    public int getType(){
        return 1;
    }
    @Override
    public void isDeath(Host antibody){
        setStatus("death", antibody);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }
}
