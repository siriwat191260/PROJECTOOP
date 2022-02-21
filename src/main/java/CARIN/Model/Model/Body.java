package CARIN.Model;

import java.util.*;

public interface Body {
    void addAntibody(int[] location);
    void addVirus();
    void run();
    int[][] getCellLoc();
    List<Host> getOrganism();
    void move(int[] location, int[] newLocation);
    int getVirusNum();
    int getAntibodyNum();
    int[] getMN();
    void addvirus(int[] location);
    Host findOrganByLocation(int[] location);
    boolean checkEmptyCell(int[] location);
    void update();
    void render();
}