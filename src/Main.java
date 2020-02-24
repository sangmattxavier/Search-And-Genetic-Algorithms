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
            for(int i = 0; i <grid.length; i++){
                for(int j = 0; j<grid.length; j++){
                    Tile t = new Tile(j, i, grid.length);
                    grid[i][j]=t;
                }
            }

            grid = BFS.breadthFirstSearch(grid);
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
        System.out.println("\n\nStarting BFS");
        // grid = BFS.breadthFirstSearch(grid);
        GridController.printMinimumDistance(grid);
        HillClimb hc = new HillClimb();
        System.out.println("The value of this grid using BFS is: " + GridController.evaluate(grid));

        // Hill Climb
        System.out.println("\n\nStarting Hill Climb");
        grid = hc.hillClimb(100, grid);
        GridController.printMinimumDistance(grid);
        System.out.println("The value of this grid after Hill Climb is: " + GridController.evaluate(grid));

        // DFS
        System.out.println("\n\nStarting DFS using new board made from Hill Climb");
        GridController.clearMinimumDistance(grid);
        DFS d = new DFS();
        grid = d.realDFS(grid);
        GridController.printMinimumDistance(grid);
        System.out.println("The value of this grid after Hill Climb is: " + GridController.evaluate(grid));
    }
}
