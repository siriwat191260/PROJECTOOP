package CARIN.Model;

import java.util.Arrays;

public class Virus extends HostImp {

    public Virus(String geneticCode, int health, int attackDamage,int gain, int[] location, Body body) {
        super(geneticCode, health, attackDamage, gain, location, body);
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
            health += gain;
            /* send gain output */
        } else System.out.println("can't shoot");
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

}
