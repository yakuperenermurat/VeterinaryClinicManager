import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    private char[][] gameBoard; // Game board shown to player
    private char[][] mineBoard; // The board storing the locations of the mines
    private boolean[][] revealed; // Board that keeps track of which cells are opened
    private int rows; // Number of rows of the board
    private int cols; // Number of columns of the board
    private int mines; // Number of mines on the game board
    private int remainingTiles; // Number of unopened cells

    // The constructor method and starts the game
    public MineSweeper(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        gameBoard = new char[rows][cols];
        mineBoard = new char[rows][cols];
        revealed = new boolean[rows][cols];
        remainingTiles = rows * cols - mines;
        initializeBoard();
        placeMines();
        calculateNumbers();
    }

    // Returns game boards to their initial state
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gameBoard[i][j] = '-';
                mineBoard[i][j] = '-';
                revealed[i][j] = false;
            }
        }
    }

    // Places mines randomly on the board
    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (mineBoard[row][col] != '*') {
                mineBoard[row][col] = '*';
                minesPlaced++;
            }
        }

    }

    // Calculates the number of mines adjacent to each cell
    private void calculateNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineBoard[i][j] != '*') {
                    int count = countAdjacentMines(i, j);
                    mineBoard[i][j] = (char) (count + '0');
                }
            }
        }
    }

    // Calculates the number of neighboring mines of a given cell
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && mineBoard[i][j] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    // Prints the game board to the screen
    private void printBoard(char[][] board) {
        System.out.println("===========================");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    // Opens a specific cell and checks the status of the game
    private void revealTile(int row, int col) {
        revealed[row][col] = true;
        if (mineBoard[row][col] == '*') {
            printBoard(mineBoard);
            System.out.println("Game Over!!");
            System.exit(0);
        }
        gameBoard[row][col] = mineBoard[row][col];
        remainingTiles--;

        if (remainingTiles == 0) {
            printBoard(mineBoard);
            System.out.println("Congratulations! You Won!!");
            System.exit(0);
        }
    }

    // Starts the game and receives input from the user
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard(gameBoard);
            System.out.println("Row: ");
            int row = scanner.nextInt();
            System.out.println("Column: ");
            int col = scanner.nextInt();
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            if (revealed[row][col]) {
                System.out.println("This coordinate has already been selected. Please choose another.");
                continue;
            }
            revealTile(row, col);
        }
    }

    // Main method that starts the game and takes inputs
    public static void main(String[] args) {
        System.out.println("Welcome to MineSweeper!");
        Scanner scanner = new Scanner(System.in);
        int rows;
        int cols;
        do {
            System.out.println("Enter number of rows (minimum 2): ");
            rows = scanner.nextInt();
            System.out.println("Enter number of columns (minimum 2): ");
            cols = scanner.nextInt();
            if (rows < 2 || cols < 2) {
                System.out.println("Invalid input. Please enter values of at least 2 for both rows and columns.");
            }
        } while (rows < 2 || cols < 2);
        int mines = rows * cols / 4; // Calculate the number of mines
        MineSweeper game = new MineSweeper(rows, cols, mines);
        game.play();
    }
}