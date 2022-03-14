package CARIN.Model;

import CARIN.Config.ConfigManager;
import CARIN.Game.Game;
import CARIN.GeneticCode.GeneticManager;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BodyImp implements Body{
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
    private boolean gameOver = false;
    private final Game game;
    // input from config file
    // assume m and n is <=10 first
    public BodyImp(Game game, GeneticManager gene, ConfigManager config) {
        this.game = game;
        this.geneticCodeAnti  = gene.getAntiGene();
        this.geneticCodeVirus = gene.getVirusGene();
        cellLoc = new int[m+1][n+1];
        this.m = config.m; this.n = config.n;
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

    // random genetic code to virus
    private Host randomVirus(int[] location) {
        int x = (int) (Math.random() * geneticCodeVirus.size());
        return new Virus(geneticCodeVirus.get(x), virusHealth, virusAttack, virusGain, location, this);
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
            if(checkEmptyCell(location[0], location[1])){
                this.organismInOrder.add(new Antibody(geneticCodeAnti.get(geneNum),
                        antiHealth, antiAttack, antiGain, moveCost,location,this));
                addToCellLoc(location[0], location[1]);
                int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
                System.out.println("Added antibody to cell "+ loc);
                antiCredit -= placeCost;
                antibodyNum++;
                order++;
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
            int[] location = randomLocation();
            this.organismInOrder.add(randomVirus(location));
            addToCellLoc(location[0], location[1]);
            int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
            System.out.println("Added virus to cell "+ loc);
            virusNum++;
            order++;
        }else
            System.out.println("A virus did not spawn to the body this time unit.");
    }

    private void addVirusTurnAntiCredit() {
        virusNum--;
        antiCredit+=placeCost;
        System.out.println("Antibody credit added!");
        checkGameOver();
    }

    // when an antibody is dead and turn into a virus
    private void addAntiTurnVirus(String geneticCode, int[] location) {
        antibodyNum--;
        this.organismInOrder.add( new Virus(geneticCode, antiHealth, antiAttack, antiGain,location,this));
        cellLoc[location[0]][location[1]] = order;
        int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
        System.out.println("Antibody at cell"+ loc+"turned into virus!");
        virusNum++;
        checkGameOver();
    }
    // called to be evaluating organisms in order at each time unit
    @Override
    public void run() {
            for (Host each : organismInOrder) {
                if(!each.getStatus().equals("death")) {
                    if (gameOver) return;
                    else {
                        System.out.println("Eval organism " + (organismInOrder.indexOf(each) + 1));
                        each.eval();
                    }
                }
            }
            for (Host each : organismInOrder){
                if(each.getStatus().equals("death")){
                    int m = each.getLocation()[0];
                    int n = each.getLocation()[1];
                    int currentOrder = cellLoc[m][n];
                    System.out.println("Organism order: "+currentOrder+" is dead");
                    for(int i=1; i<=this.m; i++){
                        for(int j=1; j<=this.n; j++)
                            if(cellLoc[i][j]>currentOrder)
                                cellLoc[i][j]-=1;
                    }
                    cellLoc[m][n] = 0;
                    order--;
                    if(each.getType()==2){
                        addAntiTurnVirus(each.getGeneticCode(),each.getLocation());
                    }else if(each.getType()==1){
                        addVirusTurnAntiCredit();
                    }
                    organismInOrder.remove(each);
                }
            }

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
    public int getVirusNum() {
        return virusNum;
    }
    @Override
    public int getAntibodyNum() {
        return antibodyNum;
    }

    @Override
    public int[] getMN() {
        return new int[]{m,n};
    }

    private void removeOrganism(int[] location) {
        int m = location[0];
        int n = location[1];
        int currentOrder = cellLoc[m][n];
        organismInOrder.remove(currentOrder-1);
        cellLoc[m][n] = 0;
        for(int i=1; i<this.m; i++){
            for(int j=1; j<this.n; j++)
                if(cellLoc[i][j]!=0 && cellLoc[i][j]>currentOrder)
                    cellLoc[i][j]-=1;
        }
        order--;
        int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
        System.out.println("Organism at cell "+ loc+", order: "+currentOrder+" is dead");
    }

    private void checkGameOver(){
        if(virusNum==0 || antibodyNum==0 ) {
            System.out.println("Game over ");
            if (virusNum > antibodyNum) System.out.println("Viruses win");
            else System.out.println("Antibodies win!");
            gameOver = true;
        }
    }

    public static void main(String[] args){
//        BodyImp body;
//        String gene = "antiLoc = virus " +
//                "if (antiLoc / 10 - 1) " +
//                "then " +
//                "  if (antiLoc % 10 - 7) then move upleft " +
//                "  else if (antiLoc % 10 - 6) then move left " +
//                "  else if (antiLoc % 10 - 5) then move downleft " +
//                "  else if (antiLoc % 10 - 4) then move down " +
//                "  else if (antiLoc % 10 - 3) then move downright " +
//                "  else if (antiLoc % 10 - 2) then move right " +
//                "  else if (antiLoc % 10 - 1) then move upright " +
//                "  else move up " +
//                " else if (antiLoc) " +
//                "then  " +
//                "  if (antiLoc % 10 - 7) then shoot upleft " +
//                "  else if (antiLoc % 10 - 6) then shoot left " +
//                "  else if (antiLoc % 10 - 5) then shoot downleft " +
//                "  else if (antiLoc % 10 - 4) then shoot down " +
//                "  else if (antiLoc % 10 - 3) then shoot downright " +
//                "  else if (antiLoc % 10 - 2) then shoot right " +
//                "  else if (antiLoc % 10 - 1) then shoot upright " +
//                "  else shoot up " +
//                " else " +
//                "{ " +
//                "  dir = 10 % 8 " +
//                "  if (dir - 6) then move upleft " +
//                "  else if (dir - 5) then move left " +
//                "  else if (dir - 4) then move downleft " +
//                "  else if (dir - 3) then move down " +
//                "  else if (dir - 2) then move downright " +
//                "  else if (dir - 1) then move right " +
//                "  else if (dir) then move upright " +
//                "  else move up " +
//                "} ";
//        String gene2 = "antiLoc = antibody " +
//                "if (antiLoc / 10 - 1) " +
//                "then " +
//                "  if (antiLoc % 10 - 7) then move upleft " +
//                "  else if (antiLoc % 10 - 6) then move left " +
//                "  else if (antiLoc % 10 - 5) then move downleft " +
//                "  else if (antiLoc % 10 - 4) then move down " +
//                "  else if (antiLoc % 10 - 3) then move downright " +
//                "  else if (antiLoc % 10 - 2) then move right " +
//                "  else if (antiLoc % 10 - 1) then move upright " +
//                "  else move up " +
//                " else if (antiLoc) " +
//                "then  " +
//                "  if (antiLoc % 10 - 7) then shoot upleft " +
//                "  else if (antiLoc % 10 - 6) then shoot left " +
//                "  else if (antiLoc % 10 - 5) then shoot downleft " +
//                "  else if (antiLoc % 10 - 4) then shoot down " +
//                "  else if (antiLoc % 10 - 3) then shoot downright " +
//                "  else if (antiLoc % 10 - 2) then shoot right " +
//                "  else if (antiLoc % 10 - 1) then shoot upright " +
//                "  else shoot up " +
//                " else " +
//                "{ " +
//                "  dir = 10 % 8 " +
//                "  if (dir - 6) then move upleft " +
//                "  else if (dir - 5) then move left " +
//                "  else if (dir - 4) then move downleft " +
//                "  else if (dir - 3) then move down " +
//                "  else if (dir - 2) then move downright " +
//                "  else if (dir - 1) then move right " +
//                "  else if (dir) then move upright " +
//                "  else move up " +
//                "} ";
//        LinkedList<String> geneticCodeAnti = new LinkedList<>();
//        LinkedList<String> geneticCodeVirus = new LinkedList<>();
//        geneticCodeVirus.add(gene);
//        geneticCodeAnti.add(gene2);
//        body = new BodyImp(geneticCodeAnti,geneticCodeVirus,5, 5, 20,2, 1, 0.8, 20,
//                10, 2, 20, 10, 1);
//        body.addAntibody(new int[]{1, 3});
//        body.addVirus();
//        body.addAntibody(new int[]{2, 4});
//        body.addVirus();
//        int t=0;
//        while(body.getVirusNum()!=0 && body.getAntibodyNum()!=0) {
//            body.run();
//            t++;
//        }
//        System.out.println("time used = "+t);
    }


}