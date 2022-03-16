package CARIN.Model;

import CARIN.Config.ConfigManager;
import CARIN.GeneticCode.GeneticManager;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BodyImp extends Thread implements Body{
    Thread thread;
    private static BodyImp body;
    private List<Host>organismInOrder = new CopyOnWriteArrayList<>();
    private int[][] cellLoc;
    public int antiCredit;
    private final int placeCost;
    private final int moveCost;
    private final double virusSpawn;
    private final int antiHealth, antiAttack, antiGain;
    private final int virusHealth, virusAttack, virusGain;
    private int m, n;
    private int order;
    private int virusNum, antibodyNum;
    private final List<String> geneticCodeAnti;
    private final List<String> geneticCodeVirus;
    public boolean gameOver = false;
    private int startGameCheck = 0;
    // input from config file
    // assume m and n is <=10 first
    public BodyImp(GeneticManager gene, ConfigManager config) {
        thread = new Thread(this);
        this.geneticCodeAnti  = gene.getAntiGene();
        this.geneticCodeVirus = gene.getVirusGene();
        this.m = config.m; this.n = config.n;
        cellLoc = new int[m+1][n+1];
        this.antiCredit  = config.antiCredit;
        this.virusSpawn  = config.virusSpawn;
        this.placeCost   = config.antiCost;
        this.moveCost    = config.antiMoveCost;
        this.antiHealth  = config.antibodyHealth;
        this.antiAttack  = config.antiAttack;
        this.antiGain    = config.antiKillGain;
        this.virusHealth = config.virusHealth;
        this.virusAttack = config.virusAttack;
        this.virusGain   = config.virusGain;
        this.order = 1;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getAntiCredit() {
        return antiCredit;
    }

    @Override
    public int getAntiCreditCost() {
        return placeCost;
    }

    @Override
    public int getAntiNum() {
        return antibodyNum;
    }

    @Override
    public int getVirusNum() {
        return virusNum;
    }

    @Override
    public int getVirusMaxHp() {
        return virusHealth;
    }

    @Override
    public int getAntiMaxHp() {
        return antiHealth;
    }

    // singleton
    public static BodyImp createBody() throws IOException {
        if(body == null) body = new BodyImp(GeneticManager.createGeneticManager(), ConfigManager.getConfig());
        return body;
    }

    // random genetic code to virus
    private Host randomVirus(int[] location) {
        int x = (int) (Math.random() * geneticCodeVirus.size());
        return new Virus(x, geneticCodeVirus.get(x), virusHealth, virusAttack, virusGain, location, this);
    }
    // when adding new organism -> add to cell field
    private void addToCellLoc(int row, int column){
        cellLoc[row][column] = order;
    }
    // when moving an organism -> change location in field
    private void changeCellLoc(int[] location, int[] newLocation, int order){
        cellLoc[location[0]][location[1]] = 0;
        cellLoc[newLocation[0]][newLocation[1]] = order;
    }
    // random location when adding a new virus
    // location[0] is row and location[1] is column
    private int[] randomLocation(){
        int[] location = new int[2];
        // avoiding adding to owned cell
        do {
            location[0] = (int) (Math.random() * m) + 1;
            location[1] = (int) (Math.random() * n) + 1;
        } while (cellLoc[location[0]][location[1]] != 0);
        return location;
    }
    //
    public Host findOrganByLocation(int[] location){
        int m = location[0];
        int n = location[1];
        int order = cellLoc[m][n];
//        for (int i = 1; i <= this.m; i++) {
//            for (int j = 1; j <= this.n; j++)
//                System.out.println("order: "+cellLoc[i][j]);
//        }
        return organismInOrder.get(order-1);
    }

    @Override
    public boolean checkEmptyCell(int row, int column) {
        return cellLoc[row][column] == 0 ;
    }

    // adding new antibody
    @Override
    public void addAntibody(int[] location, int geneNum) {
        if(antiCredit>0) {
            if(startGameCheck == 1) startGameCheck = 2;
            if(checkEmptyCell(location[0], location[1])){
                this.organismInOrder.add(new Antibody(geneNum, geneticCodeAnti.get(geneNum),
                        antiHealth, antiAttack, antiGain, moveCost,location,this));
                addToCellLoc(location[0], location[1]);
                int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
                System.out.println("Added antibody to cell "+ loc);
                antiCredit -= placeCost;
                antibodyNum++;
                order++;
                /* send decrease antibody credit output */
            }
            else System.out.println("This cell is not empty!.");
        }else
            System.out.println("Run out of antibody credit! Please try to move your existed antibody instead.");
    }

    // adding new virus
    @Override
    public void addVirus() {
        // have to use virusSpawn variable as probability.
        // if random number less than virus spawn rate then a virus spawned.
        double probability = Math.random();
        if(probability<=this.virusSpawn){
            if(startGameCheck == 0) startGameCheck = 1;
            int[] location = randomLocation();
            this.organismInOrder.add(randomVirus(location));
            addToCellLoc(location[0], location[1]);
            int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
            System.out.println("Added virus to cell "+ loc);
            virusNum++;
            order++;
            /* send add virus output */
        }else
            System.out.println("A virus did not spawn to the body this time unit.");
    }

    private void addVirusTurnAntiCredit() {
        virusNum--;
        antiCredit+=placeCost;
        System.out.println("Antibody credit added!");
        /* send add antibody credit output */
        checkGameOver();
    }

    // when an antibody is dead and turn into a virus
    private void addAntiTurnVirus(int geneNum, String geneticCode, int[] location) {
        antibodyNum--;
        this.organismInOrder.add( new Virus(geneNum, geneticCode, antiHealth, antiAttack, antiGain,location,this));
        cellLoc[location[0]][location[1]] = order;
        int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
        System.out.println("Antibody at cell"+ loc+"turned into virus!");
        virusNum++;
        order++;
        /* send add virus output */
        checkGameOver();
    }
    // called to be evaluating organisms in order at each time unit
    @Override
    public void run() {
            for (Host each : organismInOrder) {
                if(!each.getStatus().equals("death")) {
                    if (gameOver) {
                        /* send game over output */
                        sendGameOverOutput();
                        return;
                    }
                    else {
                        System.out.println("Eval organism " + (organismInOrder.indexOf(each) + 1));
                        System.out.println("Type: "+each.getType());
                        each.eval();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            removeOrganism();
    }
    // return cell field that contains order of organisms
    @Override
    public int[][] getCellLoc() {
        return cellLoc;
    }
    // return list that contains location of organisms
    @Override
    public List<Host> getOrganism() {
        return organismInOrder;
    }
    // when an antibody is moved by player
    @Override
    public void move(int[] location, int[] newLocation) {
        if(this.checkEmptyCell(newLocation[0], newLocation[1])) {
            Host host = findOrganByLocation(location);
            host.move(newLocation);
            int order = organismInOrder.indexOf(host);
            changeCellLoc(location, newLocation, order + 1);
        }
    }

    @Override
    public int[] getMN() {
        return new int[]{m,n};
    }

    private void removeOrganism() {
        for (Host each : organismInOrder){
//            System.out.println(each.getLocation()[0]+""+each.getLocation()[1]+" ");
            if(each.getStatus().equals("death")) {
                int m = each.getLocation()[0];
                int n = each.getLocation()[1];
                int currentOrder = cellLoc[m][n];
                if (each.getType() == 1)
                    System.out.println(m + "" + n + " Virus order: " + currentOrder + " is dead" + each.getType());
                else
                    System.out.println(m + "" + n + "Antibody order: " + currentOrder + " is dead" + each.getType());
                for (int i = 1; i <= this.m; i++) {
                    for (int j = 1; j <= this.n; j++)
                        if (cellLoc[i][j] > currentOrder)
                            cellLoc[i][j] -= 1;
                }
                cellLoc[m][n] = 0;
                order--;
                if (each.getType() == 2) {
                    addAntiTurnVirus(each.getGeneNum(), each.getGeneticCode(), each.getLocation());
                } else if (each.getType() == 1) {
                    addVirusTurnAntiCredit();
                }
                organismInOrder.remove(each);
            }
        }
    }

    private void sendGameOverOutput(){
        /* send game over output */
    }

    private void checkGameOver(){
        if(virusNum==0 || antibodyNum==0 && startGameCheck >= 2) {
            System.out.println("Game over ");
            if (virusNum > antibodyNum) System.out.println("Viruses win");
            else System.out.println("Antibodies win!");
            gameOver = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BodyImp body = BodyImp.createBody();
        for (int i=0;i<5;i++){
            body.addVirus();
            body.addAntibody(new int[]{ (int) (Math.random()*(body.getMN()[0])+1), (int) (Math.random()*body.getMN()[1])+1},
                    (int) (Math.random()*3));
            body.run();
        }

    }


}