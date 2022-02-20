package CARIN;

import CARIN.Model.Body;
import CARIN.Model.BodyImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameState {
    public static void main(String[] args) {
        SpringApplication.run( GameState.class, args);

    }

//    private static String state;
//    private static int time;
//    Body body;
//    public GameState(){
//        body = (Body) new BodyImp(4, 4, 20,2, 1, 0.5, 20,
//                5, 2, 20, 3, 1);
//        state = "play";
//        time = 20;
//    }
//    public static void main(String[] args){
//        while (state == "play"){
//            while (time > 0){
//
//            }time--;
//
//        }
//    }
}
