package model;

import java.util.concurrent.ThreadLocalRandom;

public class Tile {
    private int number;
    private boolean isOccupied;
    private int yPos;
    private int xPos;
    public Tile next;
    public Tile up = null;
    public Tile down = null;
    public Tile right = null;
    public Tile left = null;
    private boolean visited = false;
    private boolean hasMoves = false;
    private int minimumDistance = -1;

    public int getMinimumDistance() {
        return minimumDistance;
    }

    public void setMinimumDistance(int minimumDistance) {
        this.minimumDistance = minimumDistance;
    }

    public boolean isMinimumDistance(int d){
        //System.out.println(this.toString()+"Comparing current distance: "+minimumDistance+" -> "+d);
        if (d>minimumDistance && minimumDistance != -1){
            //System.out.println("false");
            return false;
        } else{
            //System.out.println("true");
            return true;
        }
    }

    public Tile(int xPos, int yPos, int arraySize, Tile[][] grid) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.number = ThreadLocalRandom.current().nextInt(1, arraySize);
//        // Move up
//        if ((this.yPos + this.number) < arraySize) {
//            this.up = grid[this.xPos][this.yPos+this.number];
//        }
//        // Move down
//        if ((this.yPos - this.number) > -1) {
//            this.down = grid[this.xPos][this.yPos - this.number];
//        }
//        // Move right
//        if ((this.xPos + this.number) < arraySize) {
//            this.right = grid[this.xPos + this.number][this.yPos];
//        }
//        // Move left
//        if ((this.xPos - this.number) > -1) {
//            this.left = grid[this.xPos - this.number][this.yPos];
//        }
    }

    public void setValidMoves(Tile[][] grid, int arraySize) {
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean hasMoves() {
        return hasMoves;
    }

    public void setHasMoves(boolean hasMoves) {
        this.hasMoves = hasMoves;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString(){
        // return Integer.toString(this.number);
            return this.number + "(" + this.xPos + ", " + this.yPos + ")";
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
//
//
//    public boolean isOccupied() {
//        return isOccupied;
//    }
//
//    public void setOccupied(boolean occupied) {
//        isOccupied = occupied;
//    }
}
