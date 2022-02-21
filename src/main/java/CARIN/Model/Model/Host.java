package CARIN.Model;

import CARIN.Parser.Expr;

import java.util.HashMap;

public interface Host {
    void shoot(String direction);
    void move(String newLocation);
    int getHealth();
    boolean setHealth(int damage);
    int getNearest();
    int getNearBy(String direction);
    void move(int[] newLocation);
    int[] getLocation();
    int getType();
    void isDeath(Host host);
    void eval();
    HashMap<String, Expr> getIdentifier();
    String getGeneticCode();
    void setStatus(String s, Host host);
    String getStatus();
}
