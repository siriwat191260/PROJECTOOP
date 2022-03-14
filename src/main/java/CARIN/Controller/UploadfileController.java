package CARIN.Controller;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UploadfileController {
    

         @RequestMapping("/uploadfile")
        public void upload(@RequestParam String file ,@RequestParam int number) {
            
        }
       
        

}
