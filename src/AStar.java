import model.Tile;

import java.util.Stack;

public class AStar {
    Stack<Tile> s = new Stack<>();

    public Tile[][] manhattanAStar(Tile[][] grid){
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
