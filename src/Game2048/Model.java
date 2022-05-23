package Game2048;

import java.util.Formatter;
import java.util.Random;

/** This is the logical part of Game2048.
 */
public class Model {
    final int BORAD_SIZE = 4;
    private int[][] board;

    /**
     * Initialize a model with game board which has 4 rows and 4 columns.
     */
    public Model() {
        this.board = new int[BORAD_SIZE][BORAD_SIZE];
    }

    /**
     * Initialize the model with given values of game board for test purpose.
     * @param values
     */
    public Model(int[][] values) {
        this.board = new int[BORAD_SIZE][BORAD_SIZE];

        for (int r = 0; r < BORAD_SIZE; r++) {
            for (int c = 0; c < BORAD_SIZE; c++) {
                this.board[r][c] = values[r][c];
            }
        }
    }

    public int getCellValue(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException("Row and Column index must be within the range of 0-3.");
        }
        return board[row][col];
    }

    public void setCellValue(int row, int col, int val) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException("Row and Column index must be within the range of 0-3.");
        }
        board[row][col] = val;
    }

    /**
     * Return the score on the current state.
     * @return
     */
    public int getScore() {
        int score = 0;
        for (int r = 0; r < BORAD_SIZE; r++) {
            for (int c = 0; c < BORAD_SIZE; c++) {
                score += board[r][c];
            }
        }
        return score;
    }


    /**
     * Randomly add a new number 2 to an empty cell on the game board.
     * @return true if add successfully.
     */
    public boolean addNewNumber() {
        int row, col;
        Random randPos = new Random();  // create a random position
        Random randNum = new Random();
        if (isFull()) {
            return false;
        }
        do {
            row = randPos.nextInt(this.BORAD_SIZE);  // index value from 0 to size-1
            col = randPos.nextInt(this.BORAD_SIZE);
        } while (board[row][col] != 0);
        // create a random number of 2 or 4
        int num = 2* (randNum.nextInt(2) + 1);
        board[row][col] = num;
        return true;
    }


    public boolean isFull() {
        for (int i = 0; i < BORAD_SIZE; i++) {
            for (int j = 0; j < BORAD_SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * If a cell has maximum score of 2048 or there is no possible move on board,
     * the game is over.
     * @return true if game is over.
     */
    public boolean isGameOver() {
        return !atLeastOneMoveExits() || this.getScore() == 2048;
    }

    /** Check if there exits at least one move on game board. */
    public boolean atLeastOneMoveExits() {
        // Iterate over the board
        for (int row = 0; row < BORAD_SIZE; row++) {
            for (int col = 0; col < BORAD_SIZE; col++) {
                if (board[row][col] == 0) return true;

                int nxt_row = row + 1, nxt_col = col + 1;
                // compare to the cell's right
                if (inBounds(row, nxt_col)) {
                    if (board[row][col] == board[row][nxt_col])
                        return true;
                }
                // compare to the cell's down
                if (inBounds(nxt_row, col)) {
                    if (board[row][col] == board[nxt_row][col])
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Slide tiles up. If two tiles of the same value slide on the same direction, merge will happen.
     * After merged, if the tail tile has the same value as the merged, it will not merge again.
     */
    public void slideUp() {
        for (int col = 0; col < BORAD_SIZE; col++) {
            int destRow = 0;
            for (int row = 1; row < BORAD_SIZE; row++) {
                // if the cell is empty or try move the cell to its own position, do nothing
                if (destRow == row || board[row][col] == 0) {
                    continue;
                } else if (board[row][col] == board[destRow][col]) {
                    board[destRow][col] *= 2;
                    board[row][col] = 0;
                    destRow++; // move destination row behind the merged cell to prevent another merge from cell below
                } else {
                    // if destination row is occupied, keep moving down
                    if (board[destRow][col] != 0)
                        destRow++;
                    // if destination row is empty, move a tile up to the empty destination
                    if (destRow != row) {
                        board[destRow][col] = board[row][col];
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /** Slide down. */
    public void slideDown() {
        for (int col = 0; col < BORAD_SIZE; col++) {
            int destRow = BORAD_SIZE -1;
            for (int row = BORAD_SIZE -2; row >= 0; row--) {
                if (destRow == row || board[row][col] == 0) {
                    continue;
                } else if (board[row][col] == board[destRow][col]) {
                    board[destRow][col] *= 2;
                    board[row][col] = 0;
                    destRow--;
                } else {
                    if (board[destRow][col] != 0)
                        destRow--;
                    if (destRow != row) {
                        board[destRow][col] = board[row][col];
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /** Slide toward left */
    public void slideLeft() {
        for (int row = 0; row < BORAD_SIZE; row++) {
            int destCol = 0;
            for (int col = 1; col < BORAD_SIZE; col++) {
                if (destCol == col || board[row][col] == 0) {
                    continue;
                } else if (board[row][col] == board[row][destCol]) {
                    board[row][destCol] *= 2;
                    board[row][col] = 0;
                    destCol++;
                } else {
                    if (board[row][destCol] != 0)
                        destCol++;
                    if (destCol != col) {
                        board[row][destCol] = board[row][col];
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /** Slide toward right */
    public void slideRight() {
        for (int row = 0; row < BORAD_SIZE; row++) {
            int destCol = BORAD_SIZE -1;
            for (int col = BORAD_SIZE -2; col >= 0; col--) {
                if (destCol == col || board[row][col] == 0) {
                    continue;
                } else if (board[row][col] == board[row][destCol]) {
                    board[row][destCol] *= 2;
                    board[row][col] = 0;
                    destCol--;
                } else {
                    if (board[row][destCol] != 0)
                        destCol--;
                    if (destCol != col) {
                        board[row][destCol] = board[row][col];
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /**
     * Return a 4x4 grid filling with the values on the board.
     * @return String representation of 4x4 grid
     */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n");
        for (int r = 0; r < BORAD_SIZE; r++) {
            for (int c = 0; c < BORAD_SIZE; c++) {
                out.format("%4d", board[r][c]);
            }
            out.format("%n");
        }
        return out.toString();
    }

    // A helper function to determine whether the index is out of boundary of game board's size
    private boolean inBounds(int row, int col) {
        return row >= 0 && row < BORAD_SIZE && col >= 0 && col < BORAD_SIZE;
    }
}
