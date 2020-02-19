
import model.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public void BFS(Tile[][] grid) {

        Tile t = grid[0][0];

        Queue<Tile> q = new LinkedList<Tile>();

        do {
            // Move up
            if ((t.getyPosition() + t.getNumber()) < Main.max && grid[t.getxPosition()][t.getyPosition() + t.getNumber()].isVisited() == false) {
                t.up = grid[t.getxPosition()][t.getyPosition() + t.getNumber()];
                t.setHasMoves(true);
                q.add(t.up);
            }
            // Move down
            if ((t.getyPosition() - t.getNumber()) > -1 && grid[t.getxPosition()][t.getyPosition() - t.getNumber()].isVisited() == false) {
                t.down = grid[t.getxPosition()][t.getyPosition() - t.getNumber()];
                t.setHasMoves(true);
                q.add(t.down);
            }
            // Move right
            if ((t.getxPosition() + t.getNumber()) < Main.max && grid[t.getxPosition() + t.getNumber()][t.getyPosition()].isVisited() == false) {
                t.right = grid[t.getxPosition() + t.getNumber()][t.getyPosition()];
                t.setHasMoves(true);
                q.add(t.right);
            }
            // Move left
            if ((t.getxPosition() - t.getNumber()) > -1 && grid[t.getxPosition() - t.getNumber()][t.getyPosition()].isVisited() == false) {
                t.left = grid[t.getxPosition() - t.getNumber()][t.getyPosition()];
                t.setHasMoves(true);
                q.add(t.left);
            }
        } while(q.peek() != null);

        // If the current tile has no valid moves, dead-end reached
        if(t.hasMoves() == false)  {
            q.remove();
            System.out.print("Dead-end at: " + t.toString());
        }
    }
}
