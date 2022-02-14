package CARIN.Model;

import java.util.*;

public class BodyImp implements Body{
    List<Host>organismInOrder = new LinkedList<>();
    int[][] cellLoc;
    int timeUnit;
    int antiCredit;
    int placeCost;
    int moveCost;
    double virusSpawn;
    int antiHealth, antiAttack, antiGain;
    int virusHealth, virusAttack, virusGain;
    int m, n;
    int order;
    private int virusNum, antibodyNum;
    List<String> geneticCode;

    // input from config file
    // assume m and n is <=10 first
    public BodyImp(
            List<String> geneticCode,
            int m, int n,
            int antiCredit,
            int placeCost,
            int moveCost,
            double virusSpawn,
            int antiHealth,
            int antiAttack,
            int antiGain,
            int virusHealth,
            int virusAttack,
            int virusGain) {

        cellLoc = new int[m][n];
        buildField();
        this.antiCredit = antiCredit;
        this.virusSpawn = virusSpawn;
        this.placeCost = placeCost;
        this.moveCost = moveCost;
        this.antiHealth = antiHealth;
        this.antiAttack = antiAttack;
        this.antiGain = antiGain;
        this.virusHealth = virusHealth;
        this.virusAttack = virusAttack;
        this.virusGain = virusGain;
        this.order = 1;
        this.geneticCode = geneticCode;
    }
    private void buildField(){
        for (int i=0;i<m;i++){
            for (int j=0;i<n;i++){
                cellLoc[i][j] = 0;
            }
        }
    }

    private Host randomAnti(int[] location) {
        int x = (int) (Math.random() * 10) + 1;
        return new Antibody(geneticCode.get(x),antiHealth, antiAttack, antiGain,location);
    }

    private Host randomVirus(int[] location) {
        int x = (int) (Math.random() * geneticCode.size());
        return new Virus(geneticCode.get(x),antiHealth, antiAttack, antiGain,location,m,n);
    }

    private void addToCellLoc(int[] location){
        cellLoc[location[0]][location[1]] = order;
    }
    private void changeCellLoc(int[] location, int[] newLocation, int order){
        cellLoc[location[0]][location[1]] = 0;
        cellLoc[newLocation[0]][newLocation[1]] = order;
    }

    private int[] randomLocation(){
        int m = (int) (Math.random() * this.m) + 1;
        int n = (int) (Math.random() * this.n) + 1;
        int[] location = new int[2];
        location[0] = m;
        location[1] = n;
        return location;
    }

    private Host findOrganByLocation(int[] location){
        int m = location[0];
        int n = location[1];
        int order = cellLoc[m][n];
        return organismInOrder.get(order);
    }

    @Override
    public void addAntibody(int[] location) {
        if(antiCredit>0) {
            this.organismInOrder.add(randomAnti(location));
            addToCellLoc(location);
            antiCredit-=placeCost;
            antibodyNum++;
            order++;
        }else
            System.out.println("Run out of antibody credit! Please try to move your existed antibody instead.");
    }

    @Override
    public void addVirus() {
        // have to use virusSpawn variable as probability.
        int[] location = randomLocation();
        this.organismInOrder.add(randomVirus(location));
        addToCellLoc(location);
        virusNum++;
        order++;
    }

    @Override
    public void run() {
        Host current;
        for(int i=1; i<(order-1); i++){
            current = organismInOrder.get(i);
        }
    }

    @Override
    public int[][] getCellLoc() {
        return cellLoc;
    }

    @Override
    public List<Host> getOrganism() {
        return organismInOrder;
    }

    @Override
    public void moveAntibody(int[] location, int[] newLocation) {
        Host host = findOrganByLocation(location);
        host.move(newLocation);
        int order = organismInOrder.indexOf(host);
        changeCellLoc(location,newLocation,order);
    }

    @Override
    public int getVirusNum() {
        return virusNum;
    }

    @Override
    public int getAntibodyNum() {
        return antibodyNum;
    }

}
