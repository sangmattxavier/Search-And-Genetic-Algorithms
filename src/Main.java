import model.Tile;

import java.util.Scanner;

public class Main {
    public static int max;
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter board size (5, 7, 9, or 11)");
        max = read.nextInt();
        Tile[][] grid = new Tile[max][max];


        // time stamps
        long start;
        long timeDifference;
        int algCap = 50;
        long BFSavg = 0;
        long DFSavg = 0;
        long AStarAvg = 0;

        for(int k = 0; k<algCap; k++){
            // i = row & y coordinate , j = column & x coordinate
            // (x,y) = (j,i)
            for(int i = 0; i <grid.length; i++){
                for(int j = 0; j<grid.length; j++){
                    Tile t = new Tile(j, i, grid.length);
                    grid[i][j]=t;
                }
            }

//        // print regular board
//        for(int i = 0; i <max; i++){
//            for(int j = 0; j<max; j++){
//                System.out.print(grid[i][j]+"\t");
//            }
//            System.out.println();
//        }

            // BFS
            System.out.println("\n\nStarting BFS");

            start = System.nanoTime();
            Tile[][] BFSgrid = BFS.breadthFirstSearch(grid);
            timeDifference = GridController.getTimeDifference(start);
            System.out.println("BFS took " + timeDifference/100 + "ms");
            BFSavg = BFSavg + timeDifference/100;

            //GridController.printMinimumDistance(BFSgrid);
            System.out.println("The value of this grid using BFS is: " + GridController.evaluate(BFSgrid));

            // DFS
            System.out.println("\n\nStarting DFS");
            GridController.clearMinimumDistance(grid);
            GridController.fillAllChildren(grid);
            DFS d = new DFS();

            start = System.nanoTime();
            Tile[][] DFSgrid = d.depthFirstSearch(grid);
            timeDifference = GridController.getTimeDifference(start);
            System.out.println("DFS took " + timeDifference/100 + "ms");
            DFSavg = DFSavg + timeDifference/100;

            //GridController.printMinimumDistance(DFSgrid);
            System.out.println("The value of this grid after DFS is: " + GridController.evaluate(DFSgrid));

            // AStar
            System.out.println("\n\nStarting AStar");
            GridController.clearMinimumDistance(grid);
            GridController.fillAllChildrenAStar(grid);
            AStar a = new AStar();

            start = System.nanoTime();
            Tile[][] AStartGrid = a.manhattanAStar(grid);
            timeDifference = GridController.getTimeDifference(start);
            System.out.println("AStar took " + timeDifference/100 + "ms");
            AStarAvg = AStarAvg + timeDifference/100;

            //GridController.printMinimumDistance(AStartGrid);
            System.out.println("The value of this grid after AStar is: " + GridController.evaluate(AStartGrid));

//        // Hill Climb
//        System.out.println("\n\nStarting Hill Climb");
//        HillClimb hc = new HillClimb();
//        start = System.nanoTime();
//        grid = hc.hillClimb(100, grid);
//        timeDifference = GridController.getTimeDifference(start);
//        System.out.println("BFS took " + timeDifference/1000000 + "ms");
//        GridController.printMinimumDistance(grid);
//        System.out.println("The value of this grid after Hill Climb is: " + GridController.evaluate(grid));

            System.out.println("-----------------------------------");
        }

        System.out.println("Average BFS: "+BFSavg/algCap+ "ms");
        System.out.println("Average AStar: "+AStarAvg/algCap+ "ms");
        System.out.println("\tAverage time for reach goal first time: "+AStar.totalGoalsTime/algCap+"ms");
        System.out.println("Average DFS: "+DFSavg/algCap+ "ms");
        System.out.println("\tAverage time for reach goal first time: "+DFS.totalGoalsTime/algCap+"ms");

    }
}
