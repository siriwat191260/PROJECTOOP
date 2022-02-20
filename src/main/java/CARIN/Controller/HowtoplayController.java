package CARIN.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HowtoplayController {
    @RequestMapping(value = "/howtoplay")
    public String index() {
        return "howtoplay.html";
    }
}
