package Game2048;

import Game2048.TileSquare;

import javax.swing.*;
import java.awt.*;

/**
 * GamePanel is a customized panel to store a grid of numbers. It is the main game grid
 * the user interacts with.
 */
public class GamePanel extends JPanel {
    private int COLUMNS;
    private int ROWS;
    private TileSquare[][] tiles;

    /** Initialize the grid */
    public void init(int xSize, int ySize) {
        removeAll();   // remove all components on panel
        COLUMNS = xSize;
        ROWS = ySize;
        setLayout(new GridLayout(ROWS, COLUMNS));
        tiles = new TileSquare[COLUMNS][ROWS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                tiles[col][row] = new TileSquare(0);
                this.add(tiles[col][row]);  // add each tile to Game2048.GamePanel
            }
        }
    }

    /** Constructor call init() to setup the grid for game board. */
    public GamePanel(int xSize, int ySize) {
        init(xSize, ySize);
    }

    /**
     * Return the number at a specific tile on game board.
     * @param col column index of game board
     * @param row row index of game board
     * @return Integer value at board. Throw exception if the specific indices are out of bounds.
     */
    public int getValue(int col, int row) {
        if (col < 0 || col >= COLUMNS || row < 0 || row >= ROWS) {
            throw new IndexOutOfBoundsException("Game2048.GamePanel: try to get value out of index bounds.");
        }
        return tiles[col][row].getValue();
    }

    /**
     * Set a value at specific tile on game board.
     * @param col column index of game board
     * @param row row index of game board
     * @param val Integer value to set on game board. Throw exception if the specific indices are out of bounds
     */
    public void setValue(int col, int row, int val) {
        if (col < 0 || col >= COLUMNS || row < 0 || row >= ROWS) {
            throw new IndexOutOfBoundsException("Game2048.GamePanel: try to set value out of index bounds.");
        }
        tiles[col][row].setValue(val);
    }

}
