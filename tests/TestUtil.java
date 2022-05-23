import Game2048.Model;

import static org.junit.Assert.*;

public class TestUtil {
    /** The Game2048.Model we'll be testing. */
    static Model model;
    /** The size of the Board on these tests. */
    public static final int SIZE = 4;

    /**
     * Updates the static variable model to be the Game2048.Model with board attribute
     * as described by VALUES.
     */
    public static void updateModel(int[][] values) {
        // assert expression1: expression2
        // assert the expression1 is true, if not, throw AssertionError with detail message provided in expression2
        assert values.length == SIZE : "board must have 4x4 dimensions";
        assert values[0].length == SIZE : "board must have 4x4 dimensions";
        model = new Model(values);
    }

    /**
     * Check the static model is configured as given values.
     * @param values
     */
    public static void checkModel(int[][] values) {
        Model expected = new Model(values);
        // compare expected model with actual model
        assertEquals(expected.toString(), model.toString());
    }
}
