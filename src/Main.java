import model.Tile;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int max;
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter board size (5, 7, 9, or 11)");
        max = read.nextInt();
        Tile[][] grid = new Tile[max][max];

        // use BFS to get minimum distance and check if board is valid
        boolean validGrid = false;
        while(!validGrid){

            // i = row & y coordinate , j = column & x coordinate
            // (x,y) = (j,i)
            for(int i = 0; i <max; i++){
                for(int j = 0; j<max; j++){
                    Tile t = new Tile(j, i, max);
                    grid[i][j]=t;
                }
            }

            BFS b = new BFS();
            grid = b.BFS(grid);
            if(grid[grid.length-1][grid.length-1].isVisited()){
                System.out.println("good board found");
                validGrid = true;
            } else{
                System.out.println("bad board found");
            }
        }

        // print regular board
        for(int i = 0; i <max; i++){
            for(int j = 0; j<max; j++){
                System.out.print(grid[i][j]+"\t");
            }
            System.out.println();
        }

        // print min distance board
        BFS.printMinimumDistance(grid);
        HillClimb hc = new HillClimb();
        System.out.println("The value of this grid is: " + hc.evaluate(grid));

        // Hill Climb
        grid = hc.hillClimb(100, grid);
        BFS.printMinimumDistance(grid);
        System.out.println("The value of this grid after Hill Climb is: " + hc.evaluate(grid));

    }
}
