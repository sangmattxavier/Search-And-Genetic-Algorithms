import model.Tile;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class DFS {
    Stack<Tile> s = new Stack<>();
    int ctr = 0;

    public Tile[][] depthFirstSearch(Tile[][] grid) {
        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        GridController.fillAllChildren(grid);
        shortestPath(t);

        return grid;
    }

    public void shortestPath(Tile root) {
        //System.out.println(s.toString());
        s.push(root);
        while (!s.isEmpty()) {
            Tile curr = s.pop();
            curr.setVisited(true);
            for (Tile t : curr.getChildren()) {
                t.setParent(curr);
                if (!t.isVisited() || (t.isVisited() && (t.getParent().getMinimumDistance() + 1) < t.getMinimumDistance())) {
                    t.setMinimumDistance(curr.getMinimumDistance() + 1);
                    s.push(t);
                    shortestPath(t);
                }
            }
        }
    }


}