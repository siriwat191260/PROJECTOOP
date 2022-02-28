package CARIN.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PathWeb {
    
    @RequestMapping(value = "/")
    public String start_page() {
        return "index";
    }
    
    @RequestMapping(value = "/uploadfile")
    public String upload_page() {
        return "uploadfile";
    }

    @RequestMapping(value = "/howtoplay")
    public String howtoplay_page() {
        return "howtoplay";
    }

    @RequestMapping(value = "/game")
    public String game_page() {
        return "game";
    }


}

