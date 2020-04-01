import model.Tile;

import java.util.*;

public class AStar {
    List<Tile> firstPath = new ArrayList<>();
    boolean goalReached = false;
    Stack<Tile> s = new Stack<>();
    long start;
    static long totalGoalsTime;

    public void manhattanAStar(Tile[][] grid){
        start = System.nanoTime();
        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        shortestPath(t, grid); // start recursion method
    }

    public void shortestPath(Tile root, Tile[][] grid) {
        s.push(root);
        while (!s.isEmpty()) {
            Tile curr = s.pop();

            // if found first time goal was reached, save the path
            if(curr == grid[grid.length-1][grid.length-1] && !curr.isVisited()){
                firstPath.addAll(s);
                goalReached = true;
                long end = System.nanoTime();
                long timeDifference = end - start;
                totalGoalsTime = totalGoalsTime + timeDifference/100;
                System.out.println("AStar found path to goal at "+timeDifference/100+": "+firstPath.toString());
            }

            // set current tile to visited
            curr.setVisited(true);

            // set parent of children to current tile
            for (Tile t : curr.getChildren()) {
                t.setParent(curr);

                // if child hasn't been visited or it was visited but still a shorter path, update minimum distance of child
                if (!t.isVisited() || (t.isVisited() && (t.getParent().getMinimumDistance() + 1) < t.getMinimumDistance())) {
                    t.setMinimumDistance(curr.getMinimumDistance() + 1);
                    s.push(t);
                    shortestPath(t, grid);
                }
            }
        }
    }
}
