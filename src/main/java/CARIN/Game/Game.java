package CARIN.Game;

import CARIN.Event.EventQueue;
import CARIN.Event.Input.InputEvent;
import CARIN.Event.Input.addAntibody;
import CARIN.Event.Input.moveAntibody;
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
    private EventQueue<InputEvent> inputEventQueue;

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
        inputEventQueue = new EventQueue<>();
        running = true;
        try {
            loop();
        } catch (SyntaxError | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loop() throws InterruptedException {
        long gameLastTime = System.nanoTime();
        long inputLastTime = System.nanoTime();
        long evalDeltaTime, inputDeltaTime;

        while (running) {
            long currentTime = System.nanoTime();
            evalDeltaTime = currentTime - gameLastTime;
            inputDeltaTime = currentTime - inputLastTime;

            // waiting for inputs
            if (inputDeltaTime - inputLastTime >= 30 * 1000000) {
                inputLastTime = currentTime;
                receiveInputEvent();
            }

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
        body.addAntibody(new int[]{ (int) (Math.random()*(body.getMN()[0])+1), (int) (Math.random()*body.getMN()[1])+1},
        (int) (Math.random()*3));
        body.run();
    }

    public void addInputEvent(InputEvent event) {
        inputEventQueue.addEvent(event);
    }

    // processing move or add antibody
    private void receiveInputEvent(){
        if (!inputEventQueue.isEmpty()) {
            InputEvent input = inputEventQueue.removeEvent();
            if (input instanceof addAntibody addAntiEvent) {
                body.addAntibody(addAntiEvent.getLocation(), addAntiEvent.getGeneNum());
            } else if (input instanceof moveAntibody moveAntiEvent) {
                body.move(moveAntiEvent.getLocation(), moveAntiEvent.getDestination());
            }
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

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();

    }

}
