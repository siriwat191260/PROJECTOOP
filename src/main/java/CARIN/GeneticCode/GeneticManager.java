package CARIN.GeneticCode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class GeneticManager {
    List<String> antiGene = new LinkedList<>();
    List<String> virusGene = new LinkedList<>();

    public GeneticManager(String[] pathAnti, String[] pathVirus ) throws IOException {
        for (String s : pathAnti) {
            String content = Files.readString(Path.of(s), StandardCharsets.US_ASCII);
            antiGene.add(content);
        }
        for (String virus : pathVirus) {
            String content = Files.readString(Path.of(virus), StandardCharsets.US_ASCII);
            virusGene.add(content);
        }
    }
    public String getAntiGene(int index){
        System.out.println(antiGene.get(index));
        return antiGene.get(index);
    }
    public String getVirusGene(int index){
        System.out.println(virusGene.get(index));
        return virusGene.get(index);
    }

    public static void main(String[] args) throws IOException {
        GeneticRef ref = new GeneticRef();
        GeneticManager read = new GeneticManager(ref.getAntiGene() , ref.getVirusGene() );
        read.getAntiGene(0);
    }
}

