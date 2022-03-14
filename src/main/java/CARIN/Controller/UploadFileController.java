package CARIN.Controller;

import CARIN.GeneticCode.GeneticManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UploadFileController {

    @RequestMapping("/uploadfile")
    public void upload(@RequestParam String file ,@RequestParam int number) throws IOException {
         try {
             GeneticManager geneticManager = GeneticManager.createGeneticManager();
             // if number is 1-3 then set antibody genetic code
             if(number <= 3) {
                 geneticManager.setAntibody(number - 1, file);
//                 System.out.println("num:" + (number - 1) + " = " + file);
                 // if number is 4-6 then set virus genetic code
             }else if(number <=6) {
                 geneticManager.setVirus(number - 4, file);
//                 System.out.println("num:" + (number - 1) + " = " + file);
             }else
                 throw new IllegalArgumentException();

         }catch (IllegalArgumentException e){
             e.printStackTrace();
         }
    }

}
