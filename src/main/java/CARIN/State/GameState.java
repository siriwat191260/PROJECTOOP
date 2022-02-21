package CARIN.State;

import CARIN.Game;
import CARIN.Model.Body;
import CARIN.Model.BodyImp;

import java.util.LinkedList;

public class GameState extends State{

    private Body body;
    public GameState(Game game){
        super(game);
        String gene1 = "antiLoc = virus " +
                "if (antiLoc / 10 - 1) " +
                "then " +
                "  if (antiLoc % 10 - 7) then move upleft " +
                "  else if (antiLoc % 10 - 6) then move left " +
                "  else if (antiLoc % 10 - 5) then move downleft " +
                "  else if (antiLoc % 10 - 4) then move down " +
                "  else if (antiLoc % 10 - 3) then move downright " +
                "  else if (antiLoc % 10 - 2) then move right " +
                "  else if (antiLoc % 10 - 1) then move upright " +
                "  else move up " +
                " else if (antiLoc) " +
                "then  " +
                "  if (antiLoc % 10 - 7) then shoot upleft " +
                "  else if (antiLoc % 10 - 6) then shoot left " +
                "  else if (antiLoc % 10 - 5) then shoot downleft " +
                "  else if (antiLoc % 10 - 4) then shoot down " +
                "  else if (antiLoc % 10 - 3) then shoot downright " +
                "  else if (antiLoc % 10 - 2) then shoot right " +
                "  else if (antiLoc % 10 - 1) then shoot upright " +
                "  else shoot up " +
                " else " +
                "{ " +
                "  dir = random % 8 " +
                "  if (dir - 6) then move upleft " +
                "  else if (dir - 5) then move left " +
                "  else if (dir - 4) then move downleft " +
                "  else if (dir - 3) then move down " +
                "  else if (dir - 2) then move downright " +
                "  else if (dir - 1) then move right " +
                "  else if (dir) then move upright " +
                "  else move up " +
                "} ";
        String gene2 = "antiLoc = antibody " +
                "if (antiLoc / 10 - 1) " +
                "then " +
                "  if (antiLoc % 10 - 7) then move upleft " +
                "  else if (antiLoc % 10 - 6) then move left " +
                "  else if (antiLoc % 10 - 5) then move downleft " +
                "  else if (antiLoc % 10 - 4) then move down " +
                "  else if (antiLoc % 10 - 3) then move downright " +
                "  else if (antiLoc % 10 - 2) then move right " +
                "  else if (antiLoc % 10 - 1) then move upright " +
                "  else move up " +
                " else if (antiLoc) " +
                "then  " +
                "  if (antiLoc % 10 - 7) then shoot upleft " +
                "  else if (antiLoc % 10 - 6) then shoot left " +
                "  else if (antiLoc % 10 - 5) then shoot downleft " +
                "  else if (antiLoc % 10 - 4) then shoot down " +
                "  else if (antiLoc % 10 - 3) then shoot downright " +
                "  else if (antiLoc % 10 - 2) then shoot right " +
                "  else if (antiLoc % 10 - 1) then shoot upright " +
                "  else shoot up " +
                " else " +
                "{ " +
                "  dir = random % 8 " +
                "  if (dir - 6) then move upleft " +
                "  else if (dir - 5) then move left " +
                "  else if (dir - 4) then move downleft " +
                "  else if (dir - 3) then move down " +
                "  else if (dir - 2) then move downright " +
                "  else if (dir - 1) then move right " +
                "  else if (dir) then move upright " +
                "  else move up " +
                "} ";
        LinkedList<String> geneticCodeAnti = new LinkedList<>();
        LinkedList<String> geneticCodeVirus = new LinkedList<>();
        geneticCodeVirus.add(gene1);
        geneticCodeAnti.add(gene2);
        body = new BodyImp(game,geneticCodeAnti,geneticCodeVirus,5, 5, 20,2, 1, 0.8, 20,
                20, 2, 20, 20, 1);
        body.addVirus();
        body.addAntibody(new int[]{1, 3});
        body.addVirus();
        body.addAntibody(new int[]{3, 4});
    }

    @Override
    public void update() {
        body.update();
    }

    @Override
    public void render() {
        body.render();
    }
}
