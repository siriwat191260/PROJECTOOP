package CARIN.State;

import CARIN.Game;

public class GameOver extends State{

    public GameOver(Game game){
       super(game);
    }
    @Override
    public void update() {
        game.running = false;
    }

    @Override
    public void render() {

    }
}
