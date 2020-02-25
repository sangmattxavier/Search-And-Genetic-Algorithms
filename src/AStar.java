import model.Tile;

import java.util.*;

public class AStar {
    List<Tile> firstPath = new ArrayList<>();
    boolean goalReached = false;
    Stack<Tile> s = new Stack<>();

    public Tile[][] manhattanAStar(Tile[][] grid){
        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        shortestPath(t, grid);

        if(goalReached){
            System.out.println("AStar found path to goal:"+firstPath.toString());
        }
        return grid;
    }

    public void shortestPath(Tile root, Tile[][] grid) {
        //System.out.println(s.toString());
        s.push(root);
        while (!s.isEmpty()) {
            Tile curr = s.pop();
            if(curr == grid[grid.length-1][grid.length-1] && !curr.isVisited()){
                // found first time goal was reached, save the path for AStar
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
