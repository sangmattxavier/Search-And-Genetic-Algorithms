import model.Tile;

import java.util.concurrent.ThreadLocalRandom;

public class HillClimb {

    public Tile[][] hillClimb(int iterations, Tile[][] grid) {

        for(int i = 0; i < iterations; i++) {
            int ogNum = GridController.evaluate(grid);
            int x = ThreadLocalRandom.current().nextInt(0, grid.length-1);
            int y = ThreadLocalRandom.current().nextInt(0, grid.length-1);

            int temp = grid[x][y].getNumber();
            //System.out.println("THIS IS WHAT TEMP IS: " + temp);

            while(grid[x][y].getNumber() == temp) {
                grid[x][y].setNumber(ThreadLocalRandom.current().nextInt(1, grid.length-1));
            }
            //System.out.println("NEW GRID NUM WENT FROM TEMP: "+ temp + "to " + grid[x][y].getNumber());
            GridController.clearMinimumDistance(grid);
            BFS.breadthFirstSearch(grid);
            int newNum = GridController.evaluate(grid);
            if(newNum >= ogNum) {
                //System.out.println("FOUND A NEW NUM IS BIGGER: " + newNum + " vs OLD NUM: " + ogNum);
                continue;
            } else {
                //System.out.println();
                //System.out.println("FOUND A NEW NUM IS SMALLER: " + newNum + " vs OLD NUM: " + ogNum);
                //System.out.println();
                grid[x][y].setNumber(temp);
                //System.out.println("TEMP WINS HERE: " + grid[x][y].getNumber());
                GridController.clearMinimumDistance(grid);
                BFS.breadthFirstSearch(grid);
            }
        }

        //System.out.println("returning val: "+evaluate(grid));
        return grid;
    }
}
