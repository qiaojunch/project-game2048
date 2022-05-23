import org.junit.Test;

/** Test slideDown() in Game2048.Model */
public class TestSlideDown extends TestUtil {
    @Test
    public void downNoMerge() {
        int[][] before = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 2},
        };
        updateModel(before);
        model.slideDown();
        checkModel(after);
    }

    @Test
    public void testDownBasicMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 0},
        };
        updateModel(before);
        model.slideDown();
        checkModel(after);
    }

    @Test
    public void testUpTripleMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
        };
        updateModel(before);
        model.slideDown();
        checkModel(after);
    }


    @Test
    public void testUpTrickyMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
        };
        updateModel(before);
        model.slideDown();
        checkModel(after);
    }
}
