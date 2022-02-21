package CARIN.Model;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TimeCountDown {
    private int countdownStarter;
    boolean running;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Runnable runnable;
    public TimeCountDown(int time){
        this.countdownStarter = time;
        running = false;
    }
    public void run(){
        this.runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(countdownStarter);
                countdownStarter--;
                running = true;

                if (countdownStarter < 0) {
                    running = false;
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
    public boolean isRunning(){
        System.out.println("now: "+ countdownStarter);
        return running;
    }
    public static void main(String[] args) {
        TimeCountDown count = new TimeCountDown(20);
        count.run();
        int i = 0;
        while(count.isRunning()){
            i++;
            System.out.println("running: "+i);
        }
    }
}