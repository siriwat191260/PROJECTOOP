package CARIN.Game;

import CARIN.Model.Body;
import CARIN.Model.BodyImp;
import CARIN.Parser.SyntaxError;
import java.io.IOException;

public class Game implements Runnable{
    private long timeUnit = 7000;
    private int count = (int)timeUnit/1000;
    private static Game game;
    private float speed = 1.0f;
    private Thread thread;
    public boolean running = false;
    public Body body;

    public Game() throws IOException {
        body = BodyImp.createBody();
    }

    public int getCount(){
        return count;
    }

    // singleton
    public static Game initGame() throws IOException {
        if(game == null) game = new Game();
        return game;
    }

    public void setSpeed(boolean isUp){
        if(isUp){
            if(this.speed < 2.0f)
                this.speed += 0.5f;
        }else if (!isUp) {
            if(this.speed > 0)
                this.speed -= 0.5f;
                if(this.speed == 0)
                    this.speed = 0.5f;
        }
        System.out.println(this.speed);
    }

    @Override
    public void run() {
        thread = new Thread(this);
        running = true;
        try {
            loop();
        } catch (SyntaxError | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loop() throws InterruptedException {
        long gameLastTime = System.nanoTime();
        long countLastTime = System.nanoTime();
        long evalDeltaTime, countDeltaTime;
        

        
        while (running) {
            long currentTime = System.nanoTime();
            evalDeltaTime = currentTime - gameLastTime;
            countDeltaTime = currentTime - countLastTime;
            

            if (countDeltaTime >= 1000 * 1000000){
                countLastTime = currentTime;
                count--;
            }

            if (evalDeltaTime * speed >= timeUnit * 1000000) {
                gameLastTime = currentTime;
                evaluate();
                if(body.isGameOver())
                    stop();
            }
        }
    }

    public void evaluate(){
//         int g = (int) (Math.random()*3);
//         body.addAntibody(new int[]{ (int) (Math.random()*(body.getMN()[0])+1), (int) (Math.random()*body.getMN()[1])+1},
//         g);
//         System.out.println("anti gene: "+g);
        body.addVirus();
        body.run();
        count = (int)timeUnit/1000;
    }

    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() throws InterruptedException {
        if(!running) return;
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void pause(boolean p) throws InterruptedException, IOException{
        if(p){
            running = false;
        }else{
            running = true;
            run();
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();

    }

}
