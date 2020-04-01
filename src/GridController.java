import model.Tile;

import java.util.Collections;
import java.util.Comparator;

public class GridController {
    public static int evaluate(Tile[][] grid){
//        System.out.println();
        int k = 0;
        int value;
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                if(grid[i][j].getMinimumDistance() == -1){
//                    System.out.print("X\t");
                    k--;
                } else{
//                    System.out.print(grid[i][j].getMinimumDistance()+"\t");
                }
            }
//            System.out.println();
        }
        if (grid[grid.length-1][grid.length-1].getMinimumDistance() == -1) {
            value = k;
        } else {
            value = grid[grid.length-1][grid.length-1].getMinimumDistance();
        }
        return value;
    }

    public static void printMinimumDistance(Tile[][] grid){
        System.out.println();
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                if(grid[i][j].getMinimumDistance() == -1){
                    System.out.print("X\t");
                } else{
                    System.out.print(grid[i][j].getMinimumDistance()+"\t");
                }
            }
            System.out.println();
        }
    }

    public static Tile[][] clearMinimumDistance(Tile[][] grid){
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                grid[i][j].setMinimumDistance(-1);
                grid[i][j].setVisited(false);
                grid[i][j].children.clear();
                grid[i][j].parent = null;
            }
        }
        return grid;
    }

    public static long getTimeDifference( long start){
        long end = System.nanoTime();
        long timeDifference = end - start;
        return timeDifference;
    }

    public static void fillAllChildrenAStar(Tile[][] grid) {
        Tile t;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {

                t = grid[i][j];
                // Move up
                if ((t.getyPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition() + t.getNumber()][t.getxPosition()].isVisited()) {
                    Tile up = grid[t.getyPosition() + t.getNumber()][t.getxPosition()];
                    t.children.add(up);
                }
                // Move down
                if ((t.getyPosition() - t.getNumber()) > -1 && !grid[t.getyPosition() - t.getNumber()][t.getxPosition()].isVisited()) {
                    Tile down = grid[t.getyPosition() - t.getNumber()][t.getxPosition()];
                    t.children.add(down);
                    //System.out.println("DOWN ADDED");
                }
                // Move right
                if ((t.getxPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition()][t.getxPosition() + t.getNumber()].isVisited()) {
                    Tile right = grid[t.getyPosition()][t.getxPosition() + t.getNumber()];
                    t.children.add(right);
                }
                // Move left
                if ((t.getxPosition() - t.getNumber()) > -1 && !grid[t.getyPosition()][t.getxPosition() - t.getNumber()].isVisited()) {
                    Tile left = grid[t.getyPosition()][t.getxPosition() - t.getNumber()];
                    t.children.add(left);
                }
                for (Tile tile : t.children) {
                    tile.parent = t;
                    manhattanDistance(t, grid[grid.length-1][grid.length-1]);
                }
                Collections.sort(t.children, Comparator.comparingInt(Tile::getManhattanDistance));
            }
        }
    }

    public static void fillAllChildren(Tile[][] grid) {
        Tile t;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {

                t = grid[i][j];
                // Move up
                if ((t.getyPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition() + t.getNumber()][t.getxPosition()].isVisited()) {
                    Tile up = grid[t.getyPosition() + t.getNumber()][t.getxPosition()];
                    t.children.add(up);
                }
                // Move down
                if ((t.getyPosition() - t.getNumber()) > -1 && !grid[t.getyPosition() - t.getNumber()][t.getxPosition()].isVisited()) {
                    Tile down = grid[t.getyPosition() - t.getNumber()][t.getxPosition()];
                    t.children.add(down);
                    //System.out.println("DOWN ADDED");
                }
                // Move right
                if ((t.getxPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition()][t.getxPosition() + t.getNumber()].isVisited()) {
                    Tile right = grid[t.getyPosition()][t.getxPosition() + t.getNumber()];
                    t.children.add(right);
                }
                // Move left
                if ((t.getxPosition() - t.getNumber()) > -1 && !grid[t.getyPosition()][t.getxPosition() - t.getNumber()].isVisited()) {
                    Tile left = grid[t.getyPosition()][t.getxPosition() - t.getNumber()];
                    t.children.add(left);
                }
                for (Tile tile : t.children) {
                    tile.parent = t;
                    manhattanDistance(t, grid[grid.length-1][grid.length-1]);
                }
                //TODO: USE LAMBDA EXPRESSION TO SORT THE TILE.GETCHILDREN()
                // LIST FROM LEAST TO GREAT MANHATTAN DISTANCE
            }
        }
    }

    public static void manhattanDistance(Tile curr, Tile goal){
        int distance;
        curr.setManhattanDistance(Math.abs(curr.getxPosition() - goal.getxPosition()) + Math.abs(curr.getyPosition() - goal.getyPosition()));
        //return distance;
    }
}
