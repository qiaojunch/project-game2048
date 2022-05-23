package Game2048;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/** This class creates the view of tiles. */
public class TileSquare extends JComponent {
    static final int SCALE = 100;
    static final int BOARDER = SCALE / 20;
    static final int FONT_SIZE = (int)(SCALE*0.4);
    static final Font FONT = new Font("Consolas", Font.PLAIN, FONT_SIZE);

    /** Mapping from numbers on tiles to their text and background
     *  colors. */
    static final HashMap<Integer, Color[]> TILE_COLORS = new HashMap<>();

    /** List of tile values and corresponding background and foreground
     *  color values. */
    private static final int[][] TILE_COLOR_MAP = {
            { 0, 0xd3d3d3, 0xffffff },
            { 2, 0x776e65, 0xeee4da },
            { 4, 0x776e65, 0xede0c8 },
            { 8, 0xf9f6f2, 0xf2b179 },
            { 16, 0xf9f6f2, 0xf59563 },
            { 32, 0xf9f6f2, 0xf67c5f },
            { 64, 0xf9f6f2, 0xf65e3b },
            { 128, 0xf9f6f2, 0xedcf72 },
            { 256, 0xf9f6f2, 0xedcc61 },
            { 512, 0xf9f6f2, 0xedc850 },
            { 1024, 0xf9f6f2, 0xedc53f },
            { 2048, 0xf9f6f2, 0xedc22e },
    };

    static {
        /* { "LABEL", "TEXT COLOR (hex)", "BACKGROUND COLOR (hex)" } */
        for (int[] tileData : TILE_COLOR_MAP) {
            TILE_COLORS.put(tileData[0],
                    new Color[] { new Color(tileData[1]),
                            new Color(tileData[2]) });
        }
    };

    private int value;   // number to be displayed on tile

    /** Constructor that initializes a tile with given value. */
    public TileSquare(int val) {
        this.value = val;
        this.setFont(FONT);
        this.setPreferredSize(new Dimension(SCALE, SCALE));
        this.setBackground(Color.lightGray);
    }

    /**
     * Return the value of a tile.
     * @return Integer
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Set the tile with specific value.
     * @param val Integer
     */
    public void setValue(int val) {
        this.value = val;
    }

    /** Draw a custom tile square. */
    @Override
    public synchronized void paintComponent(Graphics g) {
        // Set tileSquare color
        g.setColor(TILE_COLORS.get(value)[1]);   // set tile background color
        g.fillRoundRect(
                 BOARDER, BOARDER,
                getWidth() - 2*BOARDER, getHeight() - 2*BOARDER,
                SCALE/3, SCALE/3);
        g.setColor(TILE_COLORS.get(value)[0]);   // set number color

        // Fix aliasing on tile
        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Center the text and draw the number on tile
        FontMetrics metrics = getFontMetrics(FONT);
        String txt = Integer.toString(value);
        g.drawString(txt,
                (getWidth() - metrics.stringWidth(txt)) / 2,
                getHeight() / 2 + metrics.getAscent() / 3);
    }

}
