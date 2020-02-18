import model.Tile;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter board size (5, 7, 9, or 11)");
        int max = read.nextInt();
        Tile[][] grid = new Tile[max][max];

        // i = row & y coordinate , j = column & x coordinate
        // (x,y) = (j,i)
        for(int i = 0; i <max; i++){
            for(int j = 0; j<max; j++){
                Tile t = new Tile(max);
                t.setyPosition(i);
                t.setxPosition(j);
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
}
