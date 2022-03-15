package CARIN.Config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ConfigManager {
    private static ConfigManager config;
    protected static String inFile = "src/main/java/CARIN/Config/configFile.in";
    public int m, n;                           // positive
    public double virusSpawn;                  // between  0 and 1
    public int antiCredit;                     // positive
    public int antiCost;                       // positive and <= antiCredit
    public int virusHealth, antibodyHealth;    // positive
    public int virusAttack, virusGain;         // positive
    public int antiAttack, antiKillGain;       // positive
    public int antiMoveCost;                   // non-negative and <= antiHealth
    public int antiCreditGain;                 // non-negative (gain only when destroy a virus)

    public ConfigManager() {
        try (FileReader fr = new FileReader(inFile);
             Scanner s = new Scanner(fr)) {
            m = s.nextInt(); n = s.nextInt();
            virusSpawn = s.nextDouble(); antiCredit = s.nextInt();
            antiCost = s.nextInt();
            virusHealth = s.nextInt(); antibodyHealth = s.nextInt();
            virusAttack = s.nextInt(); virusGain = s.nextInt();
            antiAttack = s.nextInt(); antiKillGain = s.nextInt();
            antiMoveCost = s.nextInt(); antiCreditGain = s.nextInt();
            check();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigManager getConfig(){
        if(config == null) config = new ConfigManager();
        return config;
    }

    public void setConfig(String path){
        inFile = path;
    }

    private void check(){
        if(m<=0 || n<=0 || virusHealth<=0 || antibodyHealth<=0 ||
                virusAttack<=0 || virusGain<=0 || antiAttack<=0 || antiKillGain<=0)
            throw new IllegalArgumentException();
        if(!(virusSpawn>=0&&virusSpawn<=1))
            throw new IllegalArgumentException();
        if(!(antiCost>0&&antiCost<=antiCredit))
            throw new IllegalArgumentException();
        if(!(antiMoveCost>=0&&antiMoveCost<=antibodyHealth))
            throw new IllegalArgumentException();
        if(antiCreditGain < 0)
            throw new IllegalArgumentException();
    }

    private void read() {
        System.out.println("m: "+m+" n: "+n);
        System.out.println("virusSpawn: "+virusSpawn+" antiCredit: "+antiCredit+" antiCost: "+antiCost);
        System.out.println("virusHealth: "+virusHealth+" antiHealth: "+antibodyHealth);
        System.out.println("virusAttack: "+virusAttack+" virusGain: "+virusGain);
        System.out.println("antiAttack: "+antiAttack+" antiGain: "+antiKillGain);
        System.out.println("antiMoveCost: "+antiMoveCost+" antiCreditGain: "+antiCreditGain);

    }

    public static void main(String[] args) {

        ConfigManager config = new ConfigManager();
        config.read();


    }
}

