
import model.Tile;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BFS {

    public void BFS(Tile[][] grid) {

        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        Queue<Tile> q = new LinkedList<Tile>();
        q.add(t);

        do {
            try{
                // Move up
                if ((t.getyPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition() + t.getNumber()][t.getxPosition()].isVisited()) {
                    t.up = grid[t.getyPosition() + t.getNumber()][t.getxPosition()];
                    t.children.add(t.up);
                    q.add(t.up);
                }
                // Move down
                if ((t.getyPosition() - t.getNumber()) > -1 && !grid[t.getyPosition() - t.getNumber()][t.getxPosition()].isVisited()) {
                    t.down = grid[t.getyPosition() - t.getNumber()][t.getxPosition()];
                    t.children.add(t.down);
                    q.add(t.down);
                }
                // Move right
                if ((t.getxPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition()][t.getxPosition() + t.getNumber()].isVisited()) {
                    t.right = grid[t.getyPosition()][t.getxPosition() + t.getNumber()];
                    t.children.add(t.right);
                    q.add(t.right);
                }
                // Move left
                if ((t.getxPosition() - t.getNumber()) > -1 && !grid[t.getyPosition()][t.getxPosition() - t.getNumber()].isVisited()) {
                    t.left = grid[t.getyPosition()][t.getxPosition() - t.getNumber()];
                    t.children.add(t.left);
                    q.add(t.left);
                }
                for(Tile tile: t.children) {
                    tile.setVisited(true);
                    tile.parent = t;
                    tile.setMinimumDistance(tile.parent.getMinimumDistance()+1);
                }

                q.remove();
                t = q.peek();

            } catch(Exception e){
                System.out.println("Unreachable");
            }

        }
        while(q.peek() != null);

        System.out.println("The value of this grid is: " + evaluate(grid));
    }

    public int evaluate(Tile[][] grid){
        System.out.println();
        int k = 0;
        int value = 0;
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                if(grid[i][j].getMinimumDistance() == -1){
                    System.out.print("X\t");
                    k--;
                } else{
                    System.out.print(grid[i][j].getMinimumDistance()+"\t");
                }
            }
            System.out.println();
        }
        if (grid[grid.length-1][grid.length-1].getMinimumDistance() == -1) {
            value = k;
        } else {
            value = grid[grid.length-1][grid.length-1].getMinimumDistance();
        }
        return value;
    }

    public Tile[][] hillClimb(int iterations, Tile[][] grid) {
        Tile[][] temp = grid;
        for(int i = 0; i < iterations; i++) {
            Tile randomTile = temp[ThreadLocalRandom.current().nextInt(0, grid.length-1)][ThreadLocalRandom.current().nextInt(0, grid.length-1)];
            int initNum = randomTile.getNumber();
            while (randomTile.getNumber() == initNum) {
                randomTile.setNumber(ThreadLocalRandom.current().nextInt(1, grid.length-1));
            }
            if (evaluate(temp) > evaluate(grid)) {
                grid = temp;
            }
            System.out.print(evaluate(grid));
        }
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                System.out.print(grid[i][j]+"\t");
            }
            System.out.println();
        }
        return grid;
    }

}
