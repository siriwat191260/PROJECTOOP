package CARIN.Controller;

import CARIN.Game.*;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewGameController {

    Game game ;
    @RequestMapping("/newgame")
        public void NewGame() throws IOException {
             game = Game.initGame();
            System.out.println("SAWADDEE");
        }

        @RequestMapping("/game/run")
        public void GameRun(){
            game.run();
            System.out.println("SAWADDEE");
        }
}
