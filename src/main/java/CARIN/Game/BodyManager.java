package CARIN.Game;

import CARIN.Config.ConfigManager;
import CARIN.GeneticCode.GeneticManager;
import CARIN.Model.Body;
import CARIN.Model.BodyImp;
import java.io.IOException;

public class BodyManager {
    private Body body;

    public BodyManager(Game game) throws IOException {
        body = new BodyImp(game, new GeneticManager(), new ConfigManager());
    }

    protected Body getBody(){
        return body;
    }
}
