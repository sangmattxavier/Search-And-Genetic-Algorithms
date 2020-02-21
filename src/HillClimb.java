import model.Tile;

import java.util.concurrent.ThreadLocalRandom;

public class HillClimb {
    public int evaluate(Tile[][] grid){
//        System.out.println();
        int k = 0;
        int value = 0;
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
        Tile[][] temp = grid;
        for(int i = 0; i < iterations; i++) {
            int x = ThreadLocalRandom.current().nextInt(0, grid.length-1);
            int y = ThreadLocalRandom.current().nextInt(0, grid.length-1);
            System.out.println("Switching original tile: "+grid[x][y].getxPosition()+", "+grid[x][y].getyPosition()+" ... value: "+grid[x][y].getNumber());
            Tile randomTile = temp[x][y];
            int initNum = randomTile.getNumber();
            while (randomTile.getNumber() == initNum) {
                randomTile.setNumber(ThreadLocalRandom.current().nextInt(1, grid.length-1));
            }
            System.out.println("To random valued tile: "+randomTile.getxPosition()+", "+randomTile.getyPosition()+" ... value: "+randomTile.getNumber());
            if (evaluate(temp) > evaluate(grid)) {
                System.out.println("Random tile change made the board longer to solve!");
                grid = temp;
            } else{
                System.out.println("Random tile change did not complicate the board.");
            }
            System.out.println();
//            System.out.print(evaluate(grid));
        }

        return grid;
    }
}
