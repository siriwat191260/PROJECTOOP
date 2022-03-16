package CARIN.Model;

import java.util.Arrays;

public class Antibody extends HostImp{

    public Antibody(int gene, String geneticCode, int health, int attackDamage,int gain,int moveCost,
                    int[] location,Body body) {
        super(gene, geneticCode, health, attackDamage, gain, location, body);
        this.moveCost = moveCost;
    }

    @Override
    public void shoot(String direction) {
        int[] shootLoc = findLoc(direction);
        Host shoot = body.findOrganByLocation(shootLoc);
        if (!body.checkEmptyCell(shootLoc[0], shootLoc[1]) &&
                !Arrays.equals(shootLoc, location) && shoot.getStatus().equals("normal")) {
            System.out.println(this.location[0] + "" + this.location[1] + " shoot " +
                    direction + shoot.getLocation()[0] + "" + shoot.getLocation()[1]);
            /* send shoot output */
            if (shoot.setHealth(attackDamage)) {
                shoot.isDeath(this);
                health += gain;
            }
            /* send gain output */
        } else System.out.println("can't shoot");
    }

    @Override
    public void cantMove() {
        System.out.println(location[0]+""+location[1]+"Cannot move a antibody!");
    }

    @Override
    public int getType(){
        return 2;
    }

    @Override
    public void isDeath(Host virus){
        setStatus("death", virus);
        this.geneticCode = virus.getGeneticCode();
        this.gene = virus.getGeneNum();
        /* send dead output */
    }

}
