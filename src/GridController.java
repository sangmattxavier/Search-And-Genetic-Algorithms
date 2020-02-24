import model.Tile;

public class GridController {
    public static int evaluate(Tile[][] grid){
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

    public static void printMinimumDistance(Tile[][] grid){
        System.out.println();
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                if(grid[i][j].getMinimumDistance() == -1){
                    System.out.print("X\t");
                } else{
                    System.out.print(grid[i][j].getMinimumDistance()+"\t");
                }
            }
            System.out.println();
        }
    }

    public static Tile[][] clearMinimumDistance(Tile[][] grid){
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid.length; j++){
                grid[i][j].setMinimumDistance(-1);
                grid[i][j].setVisited(false);
                grid[i][j].children.clear();
                grid[i][j].parent = null;
            }
        }
        return grid;
    }

    public static long getTimeDifference( long start){
        long end = System.nanoTime();
        long timeDifference = end - start;
        return timeDifference;
    }
}
