
import model.Tile;

import java.util.*;

public class BFS {

    public void BFS(Tile[][] grid) {

        Tile t = grid[0][0];
        int counter = 1;

        Queue<Tile> q = new LinkedList<Tile>();

        do {
            try{
                System.out.println("\nNew Root Node");
                // Move up
                if ((t.getyPosition() + t.getNumber()) < grid.length && !grid[t.getxPosition()][t.getyPosition() + t.getNumber()].isVisited()) {
                    t.up = grid[t.getxPosition()][t.getyPosition() + t.getNumber()];
                    t.up.setVisited(true);
                    t.setHasMoves(true);
                    q.add(t.up);
                    t.up.setMinimumDistance(counter);
                }
                // Move down
                if ((t.getyPosition() - t.getNumber()) > -1 && !grid[t.getxPosition()][t.getyPosition() - t.getNumber()].isVisited()) {
                    t.down = grid[t.getxPosition()][t.getyPosition() - t.getNumber()];
                    t.down.setVisited(true);
                    t.setHasMoves(true);
                    q.add(t.down);
                    t.down.setMinimumDistance(counter);
                }
                // Move right
                if ((t.getxPosition() + t.getNumber()) < grid.length && !grid[t.getxPosition() + t.getNumber()][t.getyPosition()].isVisited()) {
                    t.right = grid[t.getxPosition() + t.getNumber()][t.getyPosition()];
                    t.right.setVisited(true);
                    t.setHasMoves(true);
                    q.add(t.right);
                    t.right.setMinimumDistance(counter);
                }
                // Move left
                if ((t.getxPosition() - t.getNumber()) > -1 && !grid[t.getxPosition() - t.getNumber()][t.getyPosition()].isVisited()) {
                    t.left = grid[t.getxPosition() - t.getNumber()][t.getyPosition()];
                    t.left.setVisited(true);
                    t.setHasMoves(true);
                    q.add(t.left);
                    t.left.setMinimumDistance(counter);
                }

                if(grid[grid.length-1][grid.length-1].isVisited()){
                    System.out.println("GOTTEM");
                }
                q.remove();
                System.out.println("Dead-end at: " + t.toString());

                t = q.peek();

                counter++;
            } catch(Exception e){
                System.out.println("Unreachable");
            }

        }
        while(q.peek() != null);


        if(grid[grid.length-1][grid.length-1].isVisited()){
            System.out.println("GOTTEM");
        }

        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                System.out.println("min distance for: "+grid[i][j]+" -> "+grid[i][j].getMinimumDistance());
            }
        }

        printMinimumDistance(grid);
    }

    public void printMinimumDistance(Tile[][] grid){

        System.out.println(Arrays.deepToString(grid)
                .replace("], ", "]\n")
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }
}
