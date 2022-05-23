import org.junit.Test;

/** Test the slideLeft() in Game2048.Model. */
public class TestSlideLeft extends TestUtil {
    @Test
    public void downNoMerge() {
        int[][] before = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideLeft();
        checkModel(after);
    }

    @Test
    public void testDownBasicMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideLeft();
        checkModel(after);
    }

    @Test
    public void testUpTripleMerge() {
        int[][] before = new int[][]{
                {2, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideLeft();
        checkModel(after);
    }


    @Test
    public void testUpTrickyMerge() {
        int[][] before = new int[][]{
                {2, 2, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 4, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        updateModel(before);
        model.slideLeft();
        checkModel(after);
    }
}
