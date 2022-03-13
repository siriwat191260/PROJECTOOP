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
    
}

