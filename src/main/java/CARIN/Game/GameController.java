package CARIN.Game;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/game")
public class GameController {
    private Game game;

    @RequestMapping("/startgame")
    public void start(){
       game.start();
    }

    @RequestMapping("/pause")
    public void pause(){
    }

    @RequestMapping("/resume")
    public void resume(){

    }

    

}
