package CARIN;

import CARIN.State.GameOver;
import CARIN.State.GameState;
import CARIN.State.State;

public class Game implements Runnable{
    private Thread thread;
    public boolean running = false;
    public State gameState;
    public State menuState;
    public State gameOver;

    public Game(){

    }
    private void init(){
        // initialization
        gameState = new GameState(this);
        gameOver = new GameOver(this);
        State.setState(gameState);
    }
    private void update(){
        // run evaluation
        if(State.getState() != null)
            State.getState().update();
    }
    private void render(){
        if(State.getState() != null)
            State.getState().render();
    }
    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                update();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
