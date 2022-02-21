package CARIN.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PathWeb {
    
    @RequestMapping(value = "/")
    public String start_page() {
        return "start.html";
    }
    
    @RequestMapping(value = "/uploadfile")
    public String upload_page() {
        return "uploadfile.html";
    }

    @RequestMapping(value = "/howtoplay")
    public String howtoplay_page() {
        return "howtoplay.html";
    }

    @RequestMapping(value = "/game")
    public String game_page() {
        return "game.html";
    }

    @RequestMapping(value = "/pause")
    public String pause_page() {
        return "index.html";
    }

    
}

