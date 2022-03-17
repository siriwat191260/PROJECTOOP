package CARIN.Controller;

import CARIN.Game.*;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        @RequestMapping("/game/pause")   
        public void pauseGame(@RequestParam boolean p) throws InterruptedException, IOException{
            game.pause(p);
            System.out.println("Pause : "+p);
        }
        
        @RequestMapping("/game/speedUp")
        public void speedGameUp(@RequestParam boolean spUp){
            System.out.println("Speed : "+spUp);
            game.setSpeed(spUp);
        }

        @RequestMapping("/game/speedDown")
        public void speedGameDown(@RequestParam boolean spDown){
            System.out.println("Speed : "+spDown);
            game.setSpeed(spDown);
        }
}
