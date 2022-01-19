import java.util.Scanner;

public class Sudoku {
    private static int[][] Board;
    private final static int N = 9;

    public void sudokuSolver(int[][] board) {
        Board = board;
        solveSudoku(0);
    }

    private boolean solveSudoku(int index) {
        // if there is no empty cell in the matrix M
        if (index == 81) return true;

        int i = index / N, j = index % N;

        if (Board[i][j] != 0) return solveSudoku(index + 1);
        else {
            for (int k = 1; k <= N; k++) {
                // if k is not present in the row i, in column j
                // and the 3x3 sub-matrix of (i, j),
                // fill the board in row i, column j with k.
                if (isValidFill(i, j, k)) {
                    Board[i][j] = k;
                    // recursively try to fill in remaining empty cells
                    // if recursion was successful: return true
                    if (solveSudoku(index + 1)) return true;
                    Board[i][j] = 0;
                }
            }
            return false;
        }
    }

    // checks whether k is in row i, in column j
    // and below the 3x3 sub-matrix of (i, j)
    private boolean isValidFill(int i, int j, int fill) {
        for (int k = 0; k < N; k++) {
            int r = (i / 3) * 3 + j / 3; // select the 3x3 sub-matrix of (i, j)
            // check row, column and 3x3 sub-matrix
            if (Board[i][k] == fill || Board[k][j] == fill
                    || Board[(r / 3) * 3 + k / 3][(r % 3) * 3 + k % 3] == fill)
                return false;
        }
        return true;
    }

    // print the board
    public static void print(int[][] board) {
        for (int i = 0; i < N; i++) {
            if (i % 3 == 0)
                System.out.println("- - - - - - - - - - - - -");
            System.out.print("| ");
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
                if ((j + 1) % 3 == 0)
                    System.out.print("| ");
            }
            System.out.println();
        }
        System.out.println("- - - - - - - - - - - - -");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = input.nextInt();
            }
        }
        Sudoku solution = new Sudoku();
        solution.sudokuSolver(board);
        print(board);
    }
}
