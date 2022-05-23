import org.junit.Test;

import static org.junit.Assert.*;

public class TestModel extends TestUtil {

    @Test
    public void addNewNumberTest() {
        System.out.println("Before adding:");
        System.out.println(model.toString());

        model.addNewNumber();
        System.out.println("After adding:");
        System.out.println(model.toString());
    }

    @Test
    /** No possible move exits. The board is full*/
    public void atLeastOneMoveTestFullBoard() {
        int[][] board = new int[][]{
                {2, 4, 8, 16},
                {32, 64, 128, 256},
                {2, 4, 8, 16},
                {32, 64, 128, 256}
        };
        updateModel(board);
        assertFalse(model.atLeastOneMoveExits());
    }

    @Test
    /** Tests a board where a tilt in any direction would cause a change. */
    public void testAnyDir() {
        int[][] board = new int[][] {
                {2, 4, 2, 2},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
        };
        updateModel(board);
        assertTrue(model.atLeastOneMoveExits());
    }

    @Test
    /** Tests a board where some move exists (max tile is on the board).
     *
     * While having the max tile on the board does mean the game is over, it
     * should not be handled in this method. */
    public void testMoveExistsMaxPiece() {
        int[][] board = new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 2, 2, 4},
                {4, 2, 4, 2048},
        };
        updateModel(board);
        assertTrue(model.atLeastOneMoveExits());
    }
    @Test
    /** One emtpy cell exits on the board. */
    public void atLeastOneMoveExits() {
        int[][] board = new int[][]{
                {2, 4, 8, 16},
                {32, 0, 128, 256},
                {2, 4, 8, 16},
                {32, 64, 128, 16}
        };
        updateModel(board);
        assertTrue(model.atLeastOneMoveExits());
    }

    @Test
    /** No tilt can cause a change. */
    public void testGameOver() {
        int[][] board = {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        };
        updateModel(board);
        assertTrue(model.isGameOver());
    }

    @Test
    /** Any tilt will change the board. */
    public void testGameNotOver() {
        int[][] board = {
                {2, 4, 2, 2},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        };
        updateModel(board);
        assertFalse(model.isGameOver());
    }

    @Test
    /** The max score(2048) tile is on the board. */
    public void testGameOverMaxPiece() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 2048}
        };
        updateModel(board);
        assertTrue(model.isGameOver());
    }

}
