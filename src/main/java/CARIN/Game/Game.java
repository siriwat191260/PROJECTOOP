package CARIN.Game;

import CARIN.Model.Body;
import CARIN.Model.BodyImp;
import CARIN.Parser.SyntaxError;
import java.io.IOException;

public class Game implements Runnable{
    private static Game game;
    private float speed = 1.0f;
    private Thread thread;
    public boolean running = false;
    public Body body;

    public Game() throws IOException {
        body = BodyImp.createBody();
    }

    // singleton
    public static Game initGame() throws IOException {
        if(game == null) game = new Game();
        return game;
    }

    public void setSpeed(float speed){
        this.speed = speed;
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
        long evalDeltaTime;

        
        while (running) {
            long currentTime = System.nanoTime();
            evalDeltaTime = currentTime - gameLastTime;

            long timeUnit = 1000;
            if (evalDeltaTime * speed >= timeUnit * 1000000) {
                gameLastTime = currentTime;
                evaluate();
                if(body.isGameOver())
                    stop();
            }
        }
    }

    public void evaluate(){
        body.addVirus();
        int g = (int) (Math.random()*3);
        body.addAntibody(new int[]{ (int) (Math.random()*(body.getMN()[0])+1), (int) (Math.random()*body.getMN()[1])+1},
        g);
        System.out.println("anti gene: "+g);
        body.run();
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
