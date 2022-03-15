package CARIN.Controller;

import CARIN.Config.ConfigManager;
import CARIN.Game.BodyManager;
import CARIN.Game.Game;
import CARIN.GeneticCode.GeneticManager;
import CARIN.Model.Body;
import CARIN.Model.BodyImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EventController {

    private final Game game;

    public EventController() throws IOException {
        game = Game.initGame();
    }

    @CrossOrigin
    @GetMapping("/bodyData")
    public BodyImp greeting() {
        System.out.println("==== get Body ====");
        return (BodyImp) game.body;
    }

}