package CARIN.Game;

import CARIN.Game.Game;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }

}
