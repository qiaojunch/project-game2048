import org.junit.Test;

/** Test slideRight() in Game2048.Model. */
public class TestSlideRight extends TestUtil {
    @Test
    public void rightNoMerge() {
        int[][] before = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 0, 4},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideRight();
        checkModel(after);
    }

    @Test
    public void testRightBasicMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideRight();
        checkModel(after);
    }

    @Test
    public void testRightTripleMerge() {
        int[][] before = new int[][]{
                {2, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 2, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideRight();
        checkModel(after);
    }


    @Test
    public void testRightTrickyMerge() {
        int[][] before = new int[][]{
                {4, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideRight();
        checkModel(after);
    }
}

