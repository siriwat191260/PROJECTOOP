package CARIN.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CARIN.Game.Game;

@RestController
public class GameController {
   
    @RequestMapping("/game/orderlocation")
    public void game_position(@RequestParam int x,@RequestParam int y,@RequestParam String type,@RequestParam int order){
        System.out.println(x);
        System.out.println(y);
        System.out.println(type);
        System.out.println(order);
        System.out.println("Hello");
    }
    
}
