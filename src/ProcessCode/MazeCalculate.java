package ProcessCode;

import InputPackage.MatrixReader;

public class MazeCalculate extends MatrixReader {

    /* A utility function to print solution matrix sol[][] */
    void printSolution(int sol[][]) {
        int n = sol.length;
        int m = sol[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    /* A utility function to check if x, y is valid index for maze */
    boolean isSafe(int maze[][], int x, int y) {
        int n = maze.length;
        int m = maze[0].length;
        if (n == 0 || m == 0) {
            return false;
        }
        return (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1);
    }

    boolean solveMaze(int maze[][]) {
        int n = maze.length;
        int m = maze[0].length;
        int sol[][] = new int[n][m];

        if (solveMazeUtil(maze, 0, 0, sol) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol);
        return true;
    }

    /* A recursive utility function to solve maze problem */
    boolean solveMazeUtil(int[][] maze, int x, int y, int[][] sol) {
        int n = maze.length;
        int m = maze[0].length;

        // if (x, y is goal) return true
        if (x == n - 1 && y == m - 1 && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y)) {
            // Check if the current block is already part of solution path.
            if (sol[x][y] == 1)
                return false;

            // mark x, y as part of solution path
            sol[x][y] = 1;

            // Move forward in x direction
            if (x + 1 < n && solveMazeUtil(maze, x + 1, y, sol))
                return true;

            // Move down in y direction
            if (y + 1 < m && solveMazeUtil(maze, x, y + 1, sol))
                return true;

            // If none of the above movements work then BACKTRACK:
            // unmark x, y as part of solution path
            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    public static void main(String args[]) {
        MazeCalculate rat = new MazeCalculate();

        MatrixReader mxr = new MatrixReader();
        int[][] matrix = mxr.readMatrixFromFile("D:/Maze Problem Solution/MazeChallenge/src/matrix.txt");

        rat.solveMaze(matrix);

    }
}
