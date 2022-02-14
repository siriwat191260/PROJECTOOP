package CARIN.Model;

public class Antibody extends HostImp{

    public Antibody(String geneticCode, int health, int attackDamage,int gain, int[] location) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
        this.geneticCode = geneticCode;
        this.m = m;
        this.n = n;
    }

    @Override
    public void cantmove() {
        System.out.println("Cannot move a antibody!");
    }
}
