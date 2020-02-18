package model;
import java.util.concurrent.ThreadLocalRandom;

public class Tile {
    private int number;
    private boolean isOccupied;
    private int yPosition;
    private int xPosition;

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Tile(int arraySize){
        this.number = ThreadLocalRandom.current().nextInt(1, arraySize);
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString(){
        return "("+this.xPosition + ", "+ this.yPosition+")";
    }


}
