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
        int originalGridValue = evaluate(grid);


//        System.out.println(temp);
//        System.out.println(grid);

        for(int i = 0; i < iterations; i++) {
            Tile[][] temp  = new Tile[grid.length][];
            temp = BFS.clearMinimumDistance(grid);


            int x = ThreadLocalRandom.current().nextInt(0, grid.length-1);
            int y = ThreadLocalRandom.current().nextInt(0, grid.length-1);
            System.out.println("Switching original tile: "+grid[x][y].getxPosition()+", "+grid[x][y].getyPosition()+" ... value: "+grid[x][y].getNumber());
            Tile randomTile = temp[x][y];
            int initNum = randomTile.getNumber();
            while (randomTile.getNumber() == initNum) {
                randomTile.setNumber(ThreadLocalRandom.current().nextInt(1, grid.length-1));
            }

            System.out.println("To random valued tile: "+randomTile.getxPosition()+", "+randomTile.getyPosition()+" ... value: "+randomTile.getNumber());
            temp[x][y]=randomTile;

            temp = BFS.clearMinimumDistance(temp);
            temp = BFS.breadthFirstSearch(temp);

            // print regular board
            for(int k = 0; k <grid.length; k++){
                for(int j = 0; j<grid.length; j++){
                    System.out.print(grid[k][j]+"\t");
                }
                System.out.println();
            }

            System.out.println("orig "+originalGridValue);
            System.out.println("Eval of temp: "+evaluate(temp));

            grid = BFS.clearMinimumDistance(grid);
            temp = BFS.clearMinimumDistance(temp);
            grid = BFS.breadthFirstSearch(grid);
            temp = BFS.breadthFirstSearch(temp);
            if (evaluate(temp) > originalGridValue) {
                System.out.println("Random tile change made the board longer to solve!");
                originalGridValue = evaluate(temp);

                for (int r = 0; r < temp.length; r++) {
                    grid[r] = temp[r].clone();
                }
                // print regular board
                for(int k = 0; k <grid.length; k++){
                    for(int j = 0; j<grid.length; j++){
                        System.out.print(grid[k][j]+"\t");
                    }
                    System.out.println();
                }
                //grid = b.BFS(grid);
                //grid = temp;
            } else if (evaluate(temp) == originalGridValue) {
                System.out.println("Random tile change made the board the same!");
            }  else{
                System.out.println("Random tile change made it simpler.");
            }
            System.out.println();
//            System.out.print(evaluate(grid));
        }

        System.out.println("returning val: "+evaluate(grid));
        return grid;
    }
}
