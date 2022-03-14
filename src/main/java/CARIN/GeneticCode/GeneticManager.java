package CARIN.GeneticCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GeneticManager {
    private static GeneticManager geneticManager;
    List<String> antiGene = new LinkedList<>();
    List<String> virusGene = new LinkedList<>();

    public GeneticManager() throws IOException {
        GeneticRef ref = new GeneticRef();
        for (String gene : ref.getAntiGene()) {
            File fp = new File(gene);
            FileReader fr = new FileReader(fp);
            BufferedReader br = new BufferedReader(fr);

            String content="";
            String line;
            while((line = br.readLine()) != null) { content = content+" "+line; }
            antiGene.add(content);
        }
        for (String gene : ref.getVirusGene()) {
            File fp = new File(gene);
            FileReader fr = new FileReader(fp);
            BufferedReader br = new BufferedReader(fr);

            String content="";
            String line;
            while((line = br.readLine()) != null) { content = content+" "+line; }
            virusGene.add(content);
        }
    }

    // singleton
    public static GeneticManager createGeneticManager() throws IOException {
        if(geneticManager == null) geneticManager = new GeneticManager();
        return geneticManager;
    }

    public void setAntibody(int kind, String gene){
        antiGene.add(kind, gene);
    }

    public void setVirus(int kind, String gene){
        virusGene.add(kind, gene);
    }

    public List<String> getAntiGene(){
        return antiGene;
    }
    public List<String> getVirusGene(){
        return virusGene;
    }

    public static void main(String[] args) throws IOException {
        GeneticManager read = new GeneticManager();
        System.out.println(read.getVirusGene().get(0));
    }
}

