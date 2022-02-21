package CARIN.State;

import CARIN.Game;

public abstract class State {
    private static State currentState = null;
    protected Game game;

    public State(Game game){
        this.game = game;
    }
    public static void setState(State current){
        currentState = current;
    }
    public static State getState(){
        return currentState;
    }

    public abstract void update();
    public abstract void render();

}
