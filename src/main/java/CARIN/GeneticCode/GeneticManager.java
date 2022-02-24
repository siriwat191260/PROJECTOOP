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

    public GeneticManager() throws IOException {
        GeneticRef ref = new GeneticRef();
        for (String s : ref.getAntiGene()) {
            String content = Files.readString(Path.of(s), StandardCharsets.US_ASCII);
            antiGene.add(content);
        }
        for (String virus : ref.getVirusGene()) {
            String content = Files.readString(Path.of(virus), StandardCharsets.US_ASCII);
            virusGene.add(content);
        }
    }
    public List<String> getAntiGene(){
        return antiGene;
    }
    public List<String> getVirusGene(){
        return virusGene;
    }

    public static void main(String[] args) throws IOException {
        GeneticManager read = new GeneticManager();
        System.out.println(read.getAntiGene().get(0));
    }
}

