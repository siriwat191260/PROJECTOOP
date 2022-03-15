package CARIN.Controller;

import CARIN.Game.Game;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EventController {

//    public EventController() throws IOException {
//        Game game = Game.initGame();
//        game.run();
//    }

    @CrossOrigin
    @GetMapping("/bodyData")
    public BodyData greeting() throws IOException {
        System.out.println("==== fetch Body ====");
        return new BodyData();
    }

}