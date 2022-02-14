package CARIN.Parser;

import CARIN.Model.Host;

public class SensorExp implements Expr{

    String type;
    Host host;
    public SensorExp (String type, Host host){
        this.type = type;
        this.host = host;
    }
    @Override
    public int eval() {
        if(type.equals("virus") || type.equals("antibody"))
            return host.getNearest();
        else
            // type = direction
            return host.getNearBy(type);
    }
}
