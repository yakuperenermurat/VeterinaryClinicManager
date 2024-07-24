import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    private char[][] gameBoard; // Game board shown to the player
    private char[][] mineBoard; // Board that holds the locations of mines
    private boolean[][] revealed; // Board that keeps track of which cells are revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int mines; // Number of mines on the game board
    private int remainingTiles; // Number of unopened cells

    // Constructor, initializes the game
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
        printBoard(mineBoard);
    }

    // Initializes the game boards to their initial state
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

    // Calculates the number of adjacent mines for each cell
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

    // Counts the number of adjacent mines for a given cell
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

    // Prints the game board to the console
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

    // Reveals a specific cell and checks the game status
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

    // Starts the game and takes input from the user
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

    // Main method to start the game and take inputs
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