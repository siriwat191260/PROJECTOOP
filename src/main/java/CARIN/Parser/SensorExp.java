package CARIN.Parser;

import CARIN.Model.Host;

public class SensorExp implements Expr{

    private final String type;
    private final Host host;
    public SensorExp (String type, Host host){
        this.type = type;
        this.host = host;
    }
    @Override
    public int eval() {
        try{
        if(type.equals("virus") || type.equals("antibody"))
            return host.getNearest();
        else
            return host.getNearBy(type);
        }catch (EvalError e) {
            throw new EvalError("Cannot evaluate "+type);
        }
    }
}
