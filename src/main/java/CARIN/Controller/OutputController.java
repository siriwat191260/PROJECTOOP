package CARIN.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class OutputController {

    @CrossOrigin
    @GetMapping("/bodyData")
    public BodyData sendBody() throws IOException {
        System.out.println("==== fetch Body ====");
        return new BodyData();
    }



}