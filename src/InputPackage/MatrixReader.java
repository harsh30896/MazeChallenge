package InputPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class MatrixReader {

    public static int[][] readMatrixFromFile(String filename) {
        int[][] matrix = null;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Determine the number of rows and columns
            int rows = 0, cols = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.trim().split("\\s+");
                if (cols == 0) {
                    cols = values.length;
                }
                rows++;
            }

            // Initialize the matrix
            matrix = new int[rows][cols];

            // Read the matrix values
            scanner = new Scanner(file);
            int row = 0;
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().trim().split("\\s+");
                for (int col = 0; col < values.length; col++) {
                    try {
                        matrix[row][col] = Integer.parseInt(values[col]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in file: " + values[col]);
                        return null;
                    }
                }
                row++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Matrix is null.");
            return;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


}
