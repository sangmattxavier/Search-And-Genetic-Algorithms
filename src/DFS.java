import model.Tile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DFS {

    public Tile[][] depthFirstSearch(Tile[][] grid){
        BFS.clearMinimumDistance(grid);
        Tile t = grid[0][0];
        t.setMinimumDistance(0);
        Queue<Tile> q = new LinkedList<>();
        q.add(t);

        int counter = 0;
        do {
            try{

                System.out.println("Current node in q: "+q.peek());
                // gives t potential children
                t = checkForChildren(t, grid);
                t.setVisited(true);
                Tile backup = t;

                // traverse to detect unvisited children
                for(int i = 0; i<t.getChildren().size(); i++){
                    System.out.println("Comparing counter: "+counter+" to min dist: "+t.getChildren().get(i).getMinimumDistance()+"\n"+!t.getChildren().get(i).isMinimumDistance(counter));
                    if(!t.getChildren().get(i).isVisited() || (t.getChildren().get(i).isVisited() && (t.getMinimumDistance() + 1) < t.getChildren().get(i).getMinimumDistance())){
                        System.out.println("not visited: "+t.getChildren().get(i));
                        t = t.getChildren().get(i);

                        if(!t.isVisited()) {
                            t.setMinimumDistance(counter);
                        } else if((t.isVisited() && (t.parent.getMinimumDistance() + 1) < t.getMinimumDistance())) {
                            System.out.println("UNDER HERE");
                            System.out.println(t.getMinimumDistance()+" REPLACED BOIII with: " + (t.parent.getMinimumDistance() + 1));
                            System.out.println("Over HERE");

                            t.setMinimumDistance(t.parent.getMinimumDistance() + 1);
                        }
                    }
                }
                if(t==backup){ // all children visited, go back up
                    System.out.println("leaf, going up: "+t+" -> "+t.parent);
                    t.setMinimumDistance(counter);
                    System.out.println(t.getMinimumDistance());
                    t = t.parent;

//                    for(Tile ch : t.getChildren()){
//                        ch.setVisited(false);
//                    }

                    q.add(t);
                    counter--;
                } else{ // still children to visit, visit next one
                    q.add(t);
                    counter++;
                    System.out.println("Still children to visit: "+counter);
                }

//                1(0, 0)	2(1, 0)	1(2, 0)	3(3, 0)	1(4, 0)
                //2(0, 1)	3(1, 1)	3(2, 1)	2(3, 1)	2(4, 1)
                //2(0, 2)	4(1, 2)	3(2, 2)	3(3, 2)	2(4, 2)
                //3(0, 3)	3(1, 3)	1(2, 3)	2(3, 3)	2(4, 3)
                //4(0, 4)	2(1, 4)	3(2, 4)	3(3, 4)	4(4, 4)

                q.remove();
                t = q.peek();
                System.out.println();

            } catch(Exception e){
                System.out.println("Unreachable");
            }
        }
        while(q.peek() != null);
        return grid;
    }

    public Tile checkForChildren(Tile t, Tile[][] grid){

        // Move up
        if ((t.getyPosition() + t.getNumber()) < grid.length && !grid[t.getyPosition() + t.getNumber()][t.getxPosition()].isVisited()) {
            Tile up = grid[t.getyPosition() + t.getNumber()][t.getxPosition()];
            t.children.add(up);
        }
        // Move down
        if ((t.getyPosition() - t.getNumber()) > -1 && !grid[t.getyPosition() - t.getNumber()][t.getxPosition()].isVisited()) {
            Tile down = grid[t.getyPosition() - t.getNumber()][t.getxPosition()];
            t.children.add(down);
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

        for(Tile tile: t.children) {
            //tile.setVisited(false);
            tile.parent = t;
            System.out.println("\tchild: "+tile +", "+tile.isVisited());
            //tile.setMinimumDistance(tile.parent.getMinimumDistance()+1);
        }

        return t;
    }

//    public boolean hasChildren(Tile t){
//        if(t.getChildren().isEmpty()){
//            return true;
//        } else{
//
//        }
//    }
}
