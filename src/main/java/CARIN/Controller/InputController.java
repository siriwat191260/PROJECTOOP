package CARIN.Controller;

import CARIN.Model.BodyImp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class InputController {
   
    @RequestMapping("/game/addAntibody")
    public void game_position(@RequestParam int x,@RequestParam int y,@RequestParam String type) throws IOException {
        System.out.println("PosX: "+x);
        System.out.println("PosY: "+y);
        System.out.println("Type: "+type);
        BodyImp body = BodyImp.createBody();
        // add antibody
        int[] location = new int[]{x,y};
        int geneNum = switch (type) {
            case "anti1" -> 0;
            case "anti2" -> 1;
            case "anti3" -> 2;
            default -> 10;
        };
        if(geneNum<=2)
            body.addAntibody(location, geneNum);

    }

    
}
