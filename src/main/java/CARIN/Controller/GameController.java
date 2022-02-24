package CARIN.Controller;

import CARIN.Game.*;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/game")
public class GameController {
    private Game game;

    @RequestMapping("/start")
    public void start(){
    }

    @RequestMapping("/pause")
    public void pause(){
    }

    @RequestMapping("/resume")
    public void resume(){
    }

    @RequestMapping("/speedup")
    public void speedup(){
    }

    @RequestMapping("/speed_decrease")
    public void  speed_decrease(){
    }


}
