package CARIN.Model;

public class HostImp implements Host{

    int health, attackDamage, gain;
    int[] location = new int[2];
    int m,n;
    String geneticCode;

    @Override
    public void shoot(String location) {

    }

    @Override
    public void move(String newLocation) {
        String dir = newLocation.toLowerCase();
        if(dir.equals("up") && location[0]>1 ){
            location[0]-=1;
        }else if (dir.equals("down") && location[0]<m){
            location[0]+=1;
        }else if (dir.equals("left") && location[1]>0){
            location[1]-=1;
        }else if (dir.equals("right") && location[1]<n){
            location[1]+=1;
        }else if (dir.equals("upleft") && location[0]>1 && location[1]>0){
            location[0]-=1;
            location[1]-=1;
        }else if(dir.equals("upright") && location[0]>1 && location[1]<n){
            location[0]-=1;
            location[1]+=1;
        }else if (dir.equals("downleft") && location[0]<m && location[1]>0){
            location[0]+=1;
            location[1]-=1;
        }else if (dir.equals("downright") && location[0]<m && location[1]<n){
            location[0]+=1;
            location[1]+=1;
        }else this.cantmove();
    }

    public void move(int[] newLocation){
        location = newLocation;
    }

    public void cantmove(){}

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int damage) {
        health-=damage;
    }

    @Override
    public int getNearest() {
        return 0;
    }

    @Override
    public int getNearBy(String direction) {
        return 0;
    }

    // Parser needed here (protected declaration)
}
