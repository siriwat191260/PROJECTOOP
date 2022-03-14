package CARIN.Model;

import java.util.*;

public interface Body {
    void addAntibody(int[] location, int geneNum);
    void addVirus();
    void run();
    int[][] getCellLoc();
    List<Host> getOrganism();
    void move(int[] location, int[] newLocation);
    int[] getMN();
    Host findOrganByLocation(int[] location);
    boolean checkEmptyCell(int row, int column);
}