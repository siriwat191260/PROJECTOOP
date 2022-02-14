package CARIN.Model;

import java.util.*;

public interface Body {
    void addAntibody(int[] location);
    void addVirus();
    void run();
    int[][] getCellLoc();
    List<Host> getOrganism();
    void moveAntibody(int[] location, int[] newLocation);
    int getVirusNum();
    int getAntibodyNum();
}
