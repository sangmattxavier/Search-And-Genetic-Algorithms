import model.Tile;

import java.util.Scanner;

public class Main {
    public static long BFSavg = 0;
    public static long DFSavg = 0;
    public static long AStarAvg = 0;
    public static int functionValueTarget = 0;
    public static int max;
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter board size (5, 7, 9, or 11)");
        max = read.nextInt();
        Tile[][] grid = new Tile[max][max];

        // time stamps
        int algCap = 5;

        for(int k = 0; k<algCap; k++){
            System.out.println("---------------------- ITERATION START ----------------------\n");

            // i = row & y coordinate , j = column & x coordinate
            // (x,y) = (j,i)
            for(int i = 0; i <grid.length; i++){
                for(int j = 0; j<grid.length; j++){
                    Tile t = new Tile(j, i, grid.length);
                    grid[i][j]=t;
                }
            }
            //        // print regular board
            System.out.println("ORIGINAL BOARD");
            for(int i = 0; i <max; i++){
                for(int j = 0; j<max; j++){
                    System.out.print(grid[i][j]+"\t");
                }
                System.out.println();
            }

            runBFS(grid, k);
            runDFS(grid, k);
            runAStar(grid, k);

            System.out.println("Grid Length: "+grid.length);
            System.out.println("Function Value: "+GridController.evaluate(grid));
            System.out.println();
            System.out.println("---------------------- ITERATION DONE ----------------------\n");
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Average BFS: "+BFSavg/algCap+ "ms");
        System.out.println("Average AStar: "+AStarAvg/algCap+ "ms");
        System.out.println("\tAverage time for reach goal first time: "+AStar.totalGoalsTime/algCap+"ms");
        System.out.println("Average DFS: "+DFSavg/algCap+ "ms");
        System.out.println("\tAverage time for reach goal first time: "+DFS.totalGoalsTime/algCap+"ms");

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        runHillClimb(grid);
        System.out.println("NEW BOARD");
        for(int i = 0; i <max; i++){
            for(int j = 0; j<max; j++){
                System.out.print(grid[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void runBFS(Tile[][] grid, int k){

        // BFS
//        int fnValue = 0;
//        while(fnValue!=functionValueTarget){
            System.out.println("\n\nStarting BFS");

            long start0 = System.nanoTime();
            Tile[][] BFSgrid = BFS.breadthFirstSearch(grid);
            long end0 = System.nanoTime();
            long timeDifference0 = end0 - start0;
            if (k > 0)
                System.out.println("BFS took " + timeDifference0/100 + "ms");
            BFSavg = BFSavg + timeDifference0/100;

            GridController.printMinimumDistance(BFSgrid);
            //fnValue = GridController.evaluate(BFSgrid);
            System.out.println("The value of this grid using BFS is: " + GridController.evaluate(BFSgrid));
//        }

    }

    public static void runDFS(Tile[][] grid, int k){

        // DFS
//        int fnValue = 0;
//        while(fnValue!=functionValueTarget) {
            System.out.println("\n\nStarting DFS");
            GridController.clearMinimumDistance(grid);
            DFS d = new DFS();

            Tile[][] DFSgrid = grid.clone();
            GridController.fillAllChildren(DFSgrid);
            long start1 = System.nanoTime();
            //System.out.println("START: "+start1/100);
            d.depthFirstSearch(DFSgrid);
            long end1 = System.nanoTime();
            //System.out.println("END: "+end1/100);
            long timeDifference1 = end1 - start1;
            //System.out.println("DIFFERENCE: "+timeDifference1/100);
            if (k > 0)
                System.out.println("DFS took " + timeDifference1 / 100 + "ms");
            if (k != 0) {
                DFSavg = DFSavg + timeDifference1 / 100;
            }
            GridController.printMinimumDistance(DFSgrid);
            //fnValue = GridController.evaluate(DFSgrid);
            System.out.println("The value of this grid after DFS is: " + GridController.evaluate(DFSgrid));
//        }
    }

    public static void runAStar(Tile[][] grid, int k) {
        // AStar
//        int fnValue = 0;
//        while (fnValue != functionValueTarget) {
            System.out.println("\n\nStarting AStar");
            GridController.clearMinimumDistance(grid);
            AStar a = new AStar();

            Tile[][] AStarGrid = grid.clone();
            GridController.fillAllChildrenAStar(AStarGrid);
            long start2 = System.nanoTime();
            //System.out.println("START: "+start2/100);
            a.manhattanAStar(AStarGrid);
            long end2 = System.nanoTime();
            //System.out.println("END: "+end2/100);
            long timeDifference2 = end2 - start2;
            //System.out.println("DIFFERENCE: "+timeDifference2/100);
            if (k > 0)
                System.out.println("AStar took " + timeDifference2 / 100 + "ms");
            if (k != 0) {
                AStarAvg = AStarAvg + timeDifference2 / 100;
            }
            GridController.printMinimumDistance(AStarGrid);
//            fnValue = GridController.evaluate(AStarGrid);
            System.out.println("The value of this grid after AStar is: " + GridController.evaluate(AStarGrid));

//        }
    }

    public static void runHillClimb(Tile[][] grid){
        // Hill Climb
        System.out.println("\n\nStarting Hill Climb example");
        HillClimb hc = new HillClimb();
        long start4 = System.nanoTime();
        grid = hc.hillClimb(100, grid);
        long end4 = System.nanoTime();
        long timeDifference4 = end4 - start4;
        System.out.println("Hill Climb took " + timeDifference4/100 + "ms");
        GridController.printMinimumDistance(grid);
        System.out.println("The value of this grid after Hill Climb is: " + GridController.evaluate(grid));

    }

}
