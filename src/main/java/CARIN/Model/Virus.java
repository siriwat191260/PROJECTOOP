package CARIN.Model;

public class Virus extends HostImp {

    public Virus(String geneticCode, int health, int attackDamage,int gain, int[] location,int m,int n) {
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
        System.out.println("Cannot move a virus!");
    }

}
