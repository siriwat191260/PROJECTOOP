package CARIN.Controller;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UploadfileController {
    

    @RequestMapping("/uploadfile"){
        public void upload(@RequestParam File f) {
            
        }
    }
}
