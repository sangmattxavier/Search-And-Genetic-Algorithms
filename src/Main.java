import model.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int max;
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter board size (5, 7, 9, or 11)");
        max = read.nextInt();
        Tile[][] grid = new Tile[max][max];

        // i = row & y coordinate , j = column & x coordinate
        // (x,y) = (j,i)
        for(int i = 0; i <max; i++){
            for(int j = 0; j<max; j++){
                Tile t = new Tile(j, i, max, grid);
                grid[i][j]=t;
            }
        }


        System.out.println(Arrays.deepToString(grid)
            .replace("], ", "]\n")
            .replace("[", "")
            .replace("]", "")
            .replace(",", ""));

        BFS d = new BFS();
        d.BFS(grid);
    }

//    public static void setValidMoves( Tile t, Tile[][] grid) {
//        // Move up
//        if ((t.getyPosition() + t.getNumber()) > Main.max - 1) {
//            t.up = grid[t.getxPosition()][t.getyPosition()+t.getNumber()];
//        }
//        // Move down
//        if ((t.getyPosition() - t.getNumber()) < 0) {
//            t.down = grid[t.getxPosition()][t.getyPosition()-t.getNumber()];
//        }
//        // Move right
//        if ((t.getxPosition() + t.getNumber()) > Main.max - 1) {
//            t.up = grid[t.getxPosition() + t.getNumber()][t.getyPosition()];
//        }
//        // Move left
//        if ((t.getxPosition() - t.getNumber()) < 0) {
//            t.up = grid[t.getxPosition() - t.getNumber()][t.getyPosition()];
//        }
//    }
}
