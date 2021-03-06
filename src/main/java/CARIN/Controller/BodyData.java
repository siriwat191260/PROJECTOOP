package CARIN.Controller;

import CARIN.Model.BodyImp;
import CARIN.Model.Host;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BodyData {

    public int m, n;
    public int antiCredit;
    public List<Integer> type = new LinkedList<>();
    public List<Integer> posX = new LinkedList<>();
    public List<Integer> posY = new LinkedList<>();
    public List<Integer> order = new LinkedList<>();
    public List<Integer> hp = new LinkedList<>();
    public int antiCreditCost;
    public int antiNum, virusNum;
    public int antiHealth, virusHealth;

    public BodyData() throws IOException {
        BodyImp body = BodyImp.createBody();
        m = body.getMN()[0];
        n = body.getMN()[1];
        antiCredit = body.getAntiCredit();
        // get type
        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(body.getCellLoc()[i][j] !=0){
                    Host host = body.getOrganism().get((body.getCellLoc()[i][j])-1);
                    int geneNum = host.getGeneNum();
                    // genetic code number of antibody is 1-3
                    // while virus is 4-6
                    if(host.getType() == 2)
                        type.add(geneNum+1);
                    else type.add(geneNum+4);
                    posX.add(i);
                    posY.add(j);
                    order.add(body.getCellLoc()[i][j]);
                    hp.add(host.getHealth());
                }
            }
        }
        antiCreditCost = body.getAntiCreditCost();
        antiNum = body.getAntiNum();
        virusNum = body.getVirusNum();
        antiHealth = body.getAntiMaxHp();
        virusHealth = body.getVirusMaxHp();
    }
}
