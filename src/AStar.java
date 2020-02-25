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
        shortestPath(t, grid);


//        return grid;
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
                long end = System.nanoTime();
                long timeDifference = end - start;
                totalGoalsTime = totalGoalsTime + timeDifference/100;
                System.out.println("AStar found path to goal at "+timeDifference/100+": "+firstPath.toString());
                //System.out.println("AStar Total Goals Time: "+totalGoalsTime);
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
