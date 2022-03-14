package CARIN.GeneticCode;

import java.io.IOException;

public class GeneticRef {
    private static GeneticRef geneticRef;
    private static final int antiType = 3;
    private static final int virusType = 3;
    private static String[] antiPath = new String[antiType];
    private static String[] virusPath = new String[virusType];

    public GeneticRef(){
        String antibody1 = "src/main/java/CARIN/GeneticCode/antibody1.txt";
        antiPath[0] = antibody1;
        String antibody2 = "src/main/java/CARIN/GeneticCode/antibody2.txt";
        antiPath[1] = antibody2;
        String antibody3 = "src/main/java/CARIN/GeneticCode/antibody3.txt";
        antiPath[2] = antibody3;
        String virus1 = "src/main/java/CARIN/GeneticCode/virus1.txt";
        virusPath[0] = virus1;
        String virus2 = "src/main/java/CARIN/GeneticCode/virus2.txt";
        virusPath[1]  = virus2;
        String virus3 = "src/main/java/CARIN/GeneticCode/virus3.txt";
        virusPath[2] = virus3;
    }

    // singleton
    public static GeneticRef createGeneticManager() throws IOException {
        if(geneticRef != null) geneticRef = new GeneticRef();
        return geneticRef;
    }

    public String[] getAntiGene() {
        return antiPath;
    }
    public String[] getVirusGene() {
        return virusPath;
    }

}
