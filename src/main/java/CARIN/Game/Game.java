package CARIN.Game;

import CARIN.Event.EventQueue;
import CARIN.Event.Input.InputEvent;
import CARIN.Event.Input.addAntibody;
import CARIN.Event.Input.moveAntibody;
import CARIN.Model.Body;
import CARIN.Parser.SyntaxError;
import java.io.IOException;

public class Game implements Runnable{
    private float speed = 1.0f;
    private Thread thread;
    public boolean running = false;
    private final BodyManager bodyManager;
    private Body body;
    private EventQueue<InputEvent> inputEventQueue;

    public Game() throws IOException {
        bodyManager = new BodyManager(this);
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    @Override
    public void run() {
        body = bodyManager.getBody();
        inputEventQueue = new EventQueue<>();
        try {
            loop();
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
    }

    private void loop(){
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

            long timeUnit = 7000;
            if (evalDeltaTime * speed >= timeUnit * 1000000) {
                gameLastTime = currentTime;
                body.run();
            }
        }

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

}
