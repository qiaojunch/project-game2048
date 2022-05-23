import org.junit.Test;

/** Test the slideUp() method in Game2048.Model class. */
public class TestSlideUp extends TestUtil {
    @Test
    /** Test slide down and no merge happen. */
    public void upNoMerge() {
        int[][] before = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideUp();
        checkModel(after);
    }

    @Test
    /** A basic merge. */
    public void testUpBasicMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideUp();
        checkModel(after);
    }

    @Test
    /** A triple merge. Only the leading 2 tiles should merge. */
    public void testUpTripleMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideUp();
        checkModel(after);
    }


    @Test
    /** A tricky merge.
     *
     * The tricky part here is that the 4 tile on the bottom row shouldn't
     * merge with the newly created 4 tile on the top row. */
    public void testUpTrickyMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideUp();
        checkModel(after);
    }


}
