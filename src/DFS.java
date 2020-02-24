import model.Tile;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class DFS {
    Stack<Tile> s = new Stack<>();
    int ctr = 0;

    public Tile[][] realDFS(Tile[][] grid) {
        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        fillAllChildren(grid);
        shortestPath(t, grid);
        return grid;
    }

    public void shortestPath(Tile root, Tile[][] grid) {
        System.out.println(s.toString());
        s.push(root);
        while (!s.isEmpty()) {
            Tile curr = s.pop();
            curr.setVisited(true);
            for (Tile t : curr.getChildren()) {
                t.setParent(curr);
                if (!t.isVisited() || (t.isVisited() && (t.getParent().getMinimumDistance() + 1) < t.getMinimumDistance())) {
                    t.setMinimumDistance(curr.getMinimumDistance() + 1);
                    s.push(t);
                    shortestPath(t, grid);
                } else {
                    continue;
                }
            }
        }
    }

    public void fillAllChildren(Tile[][] grid) {
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
                }
            }
        }
    }
}