package CARIN.Controller;

import CARIN.Game.Game;
import java.io.IOException;

public class CountData {

    public int count;

    public CountData() throws IOException {
        count = Game.initGame().getCount();
    }
}