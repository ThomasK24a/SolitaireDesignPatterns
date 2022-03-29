package main.java.com.nhlstenden.solitaire.Classes;

public class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addCoordinates(Coordinates coordinates){
        this.x = this.x + coordinates.getX();
        this.y = this.y + coordinates.getY();
    }
}
