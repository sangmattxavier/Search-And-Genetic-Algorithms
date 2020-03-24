package model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Tile {
    private int number;
    private int yPos;
    private int xPos;
    private int manhattanDistance;
    public Tile parent = null;
    public Tile next;
    public ArrayList<Tile> children = new ArrayList<>();
    private boolean visited = false;
    private int minimumDistance = -1;

    public ArrayList<Tile> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tile> children) {
        this.children = children;
    }

    public Tile getParent() {
        return this.parent;
    }

    public void setParent(Tile t) {
        this.parent = t;
    }

    public int getMinimumDistance() {
        return minimumDistance;
    }

    public void setMinimumDistance(int minimumDistance) {
        if (this.isMinimumDistance(minimumDistance))
            this.minimumDistance = minimumDistance;
    }

    public boolean isMinimumDistance(int d){
        if(d>minimumDistance && minimumDistance != -1){
            return false;
        } else{
            return true;
        }
    }

    public int getManhattanDistance() {
        return this.manhattanDistance;
    }

    public void setManhattanDistance(int d) {
        this.manhattanDistance = d;
    }

    public Tile(int xPos, int yPos, int arraySize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.number = ThreadLocalRandom.current().nextInt(1, arraySize);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getxPosition() {
        return xPos;
    }

    public void setxPosition(int xPos) {
        this.xPos = xPos;
    }

    public int getyPosition() {
        return yPos;
    }

    public void setyPosition(int yPos) {
        this.yPos = yPos;
    }

    public String getPosition(){
        return this.xPos+", "+this.yPos;
    }

    public String toString(){
//        return this.number + "(" + this.xPos + ", " + this.yPos + ")";
        return Integer.toString(this.number);
    }

}
