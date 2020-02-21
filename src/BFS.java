
import model.Tile;

import java.util.*;

public class BFS {

    public Tile[][] BFS(Tile[][] grid) {

        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        Queue<Tile> q = new LinkedList<>();
        q.add(t);

        do {
            try{
                // Move up
                if ((t.getyPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition() + t.getNumber()][t.getxPosition()].isVisited()) {
                    Tile up = grid[t.getyPosition() + t.getNumber()][t.getxPosition()];
                    t.children.add(up);
                    q.add(up);
                }
                // Move down
                if ((t.getyPosition() - t.getNumber()) > -1 && !grid[t.getyPosition() - t.getNumber()][t.getxPosition()].isVisited()) {
                    Tile down = grid[t.getyPosition() - t.getNumber()][t.getxPosition()];
                    t.children.add(down);
                    q.add(down);
                }
                // Move right
                if ((t.getxPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition()][t.getxPosition() + t.getNumber()].isVisited()) {
                    Tile right = grid[t.getyPosition()][t.getxPosition() + t.getNumber()];
                    t.children.add(right);
                    q.add(right);
                }
                // Move left
                if ((t.getxPosition() - t.getNumber()) > -1 && !grid[t.getyPosition()][t.getxPosition() - t.getNumber()].isVisited()) {
                    Tile left = grid[t.getyPosition()][t.getxPosition() - t.getNumber()];
                    t.children.add(left);
                    q.add(left);
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
        return grid;
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

    public Tile[][] clearMinimumDistance(Tile[][] grid){
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
}
