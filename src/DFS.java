import model.Tile;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class DFS {
    List<Tile> firstPath = new ArrayList<>();
    boolean goalReached = false;
    long start;
    static long totalGoalsTime;

    Stack<Tile> s = new Stack<>();
    int ctr = 0;

    public void depthFirstSearch(Tile[][] grid) {
        totalGoalsTime = 0;
        start = System.nanoTime();
        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        shortestPath(t, grid);

//        return grid;
    }

    public void shortestPath(Tile root, Tile[][] grid) {
        //System.out.println(s.toString());
        s.push(root);
        while (!s.isEmpty()) {
            Tile curr = s.pop();
            if(curr == grid[grid.length-1][grid.length-1] && !curr.isVisited()){
                // found first time goal was reached, save the path
                firstPath.addAll(s);
                goalReached = true;
                long end = System.nanoTime();
                long timeDifference = end - start;
                totalGoalsTime = totalGoalsTime + timeDifference;
                System.out.println("DFS found path to goal at "+timeDifference/100+": "+firstPath.toString());
            }
            curr.setVisited(true);
            for (Tile t : curr.getChildren()) {
                t.setParent(curr);
                if (!t.isVisited() || (t.isVisited() && (t.getParent().getMinimumDistance() + 1) < t.getMinimumDistance())) {
                    t.setMinimumDistance(curr.getMinimumDistance() + 1);
                    s.push(t);
                    shortestPath(t, grid);
                }
            }

        }
    }


}