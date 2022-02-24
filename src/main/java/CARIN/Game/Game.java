package CARIN.Game;

import CARIN.Model.Body;

import java.io.IOException;

public class Game implements Runnable{
    private Thread thread;
    public boolean running = false;
    private BodyManager bodyManager;
    private Body body;

    public Game() throws IOException {
        bodyManager = new BodyManager(this);
        body = bodyManager.getBody();
    }

    private void update(){
        // run evaluation

    }

    @Override
    public void run() {

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
