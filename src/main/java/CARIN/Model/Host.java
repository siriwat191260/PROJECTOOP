package CARIN.Model;

public interface Host {
    void shoot(String Location);
    // must be Overridden to do nothing in Virus classes
    void move(String newLocation);
    int getHealth();
    void setHealth(int damage);
    int getNearest();
    int getNearBy(String direction);
    void move(int[] newLocation);
}
