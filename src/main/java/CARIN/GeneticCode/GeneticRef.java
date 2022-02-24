package CARIN.GeneticCode;

public class GeneticRef {
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

    public void setAntibody1(String antibody1) {
        GeneticRef.antiPath[0] = antibody1;
    }
    public void setAntibody2(String antibody2) {
        GeneticRef.antiPath[1] = antibody2;
    }
    public void setAntibody3(String antibody3) {
        GeneticRef.antiPath[2] = antibody3;
    }
    public void setVirus1(String virus1) {
        GeneticRef.antiPath[0] = virus1;
    }
    public void setVirus2(String virus2) {
        GeneticRef.antiPath[1] = virus2;
    }
    public void setVirus3(String virus3) {
        GeneticRef.antiPath[2] = virus3;
    }
    public String[] getAntiGene() {
        return antiPath;
    }
    public String[] getVirusGene() {
        return virusPath;
    }

}
