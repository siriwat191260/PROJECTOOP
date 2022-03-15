package CARIN.Game;

import CARIN.Config.ConfigManager;
import CARIN.GeneticCode.GeneticManager;
import CARIN.Model.Body;
import CARIN.Model.BodyImp;
import java.io.IOException;

public class BodyManager {
    private final Body body;

    public BodyManager(Game game) throws IOException {
        body = BodyImp.createBody(game, GeneticManager.createGeneticManager(), ConfigManager.getConfig());
    }

    public Body getBody(){
        return body;
    }
}
