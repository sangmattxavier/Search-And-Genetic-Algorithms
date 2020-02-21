import model.Tile;

import java.util.concurrent.ThreadLocalRandom;

public class HillClimb {

    public int evaluate(Tile[][] grid){
//        System.out.println();
        int k = 0;
        int value;
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                if(grid[i][j].getMinimumDistance() == -1){
//                    System.out.print("X\t");
                    k--;
                } else{
//                    System.out.print(grid[i][j].getMinimumDistance()+"\t");
                }
            }
//            System.out.println();
        }
        if (grid[grid.length-1][grid.length-1].getMinimumDistance() == -1) {
            value = k;
        } else {
            value = grid[grid.length-1][grid.length-1].getMinimumDistance();
        }
        return value;
    }

    public Tile[][] hillClimb(int iterations, Tile[][] grid) {
        System.out.println("\n\nStarting Hill Climb");

        for(int i = 0; i < iterations; i++) {
            int ogNum = evaluate(grid);
            int x = ThreadLocalRandom.current().nextInt(0, grid.length-1);
            int y = ThreadLocalRandom.current().nextInt(0, grid.length-1);

            int temp = grid[x][y].getNumber();
            System.out.println("THIS IS WHAT TEMP IS: " + temp);

            while(grid[x][y].getNumber() == temp) {
                grid[x][y].setNumber(ThreadLocalRandom.current().nextInt(1, grid.length-1));
            }
            System.out.println("NEW GRID NUM WENT FROM TEMP: "+ temp + "to " + grid[x][y].getNumber());
            BFS.clearMinimumDistance(grid);
            BFS.breadthFirstSearch(grid);
            int newNum = evaluate(grid);
            if(newNum >= ogNum) {
                System.out.println("FOUND A NEW NUM IS BIGGER: " + newNum + " vs OLD NUM: " + ogNum);
                continue;
            } else {
                System.out.println();
                System.out.println("FOUND A NEW NUM IS SMALLER: " + newNum + " vs OLD NUM: " + ogNum);
                System.out.println();
                grid[x][y].setNumber(temp);
                System.out.println("TEMP WINS HERE: " + grid[x][y].getNumber());
                BFS.clearMinimumDistance(grid);
                BFS.breadthFirstSearch(grid);
            }
        }

        System.out.println("returning val: "+evaluate(grid));
        return grid;
    }
}
