import model.Tile;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class DFS {
    List<Tile> firstPath = new ArrayList<>();
    boolean goalReached = false;
    long start;
    long timeDifference;

    Stack<Tile> s = new Stack<>();
    int ctr = 0;

    public Tile[][] depthFirstSearch(Tile[][] grid) {
        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        shortestPath(t, grid);

        if(goalReached){
            System.out.println("DFS found path to goal:"+firstPath.toString());
        }
        return grid;
    }

    public void shortestPath(Tile root, Tile[][] grid) {
        //System.out.println(s.toString());
        start = System.nanoTime();
        s.push(root);
        while (!s.isEmpty()) {
            Tile curr = s.pop();
            if(curr == grid[grid.length-1][grid.length-1] && !curr.isVisited()){
                // found first time goal was reached, save the path
                firstPath.addAll(s);
                goalReached = true;
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