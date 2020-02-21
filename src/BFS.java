
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
}
