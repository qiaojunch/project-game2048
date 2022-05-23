package Game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * This is the view of the game. It shows the view of game board and buttons that controls
 *  the movement of tiles on the board.
 */
public class GameFrame extends JFrame {

    JPanel panelMain;
    GamePanel panelGame;
    JPanel panelButtons;
    JButton btnLeft, btnRight, btnUp, btnDown;

    final int BOARD_SIZE = 4;   // game board is 4x4 grid

    public GameFrame() {
        super("Game2048");  // call the super class with title of game

        // make the panel focusable so that it can react to keyboard inputs
        this.setFocusable(true);

        // initialize main panel
        panelMain = new JPanel();
        panelMain.setBackground(Color.cyan);
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        // add main panel to Game2048.GameFrame
        this.add(panelMain);

        // initialize game panel
        panelGame = new GamePanel(BOARD_SIZE, BOARD_SIZE);
        // add game panel to main panel
        panelMain.add(panelGame);

        // initialize button panel
        panelButtons = new JPanel();
        panelButtons.setBackground(Color.lightGray);
        panelButtons.setLayout(new GridBagLayout());
        // create a constraint instance for layout
        GridBagConstraints c = new GridBagConstraints();
        // add buttons to button panel with constraints
        btnLeft = new JButton("Left");
        btnLeft.setActionCommand("Slide left");
        c.gridx = 0; c.gridy = 1;
        panelButtons.add(btnLeft, c);
        
        btnRight = new JButton("Right");
        btnRight.setActionCommand("Slide right");
        c.gridx = 2; c.gridy = 1;
        panelButtons.add(btnRight, c);

        btnUp = new JButton("Up");
        btnUp.setActionCommand("Slide up");
        c.gridx = 1; c.gridy = 0;
        panelButtons.add(btnUp, c);

        btnDown = new JButton("Down");
        btnDown.setActionCommand("Slide down");
        c.gridx = 1; c.gridy = 2;
        panelButtons.add(btnDown, c);

        // add button panel to main panel
        panelMain.add(panelButtons);

    }

    /** Allows frame to handle two kinds of listeners */
    public void setListners(ActionListener click, KeyListener key) {
        // make frame handle event of clicking button
        btnLeft.addActionListener(click);
        btnRight.addActionListener(click);
        btnUp.addActionListener(click);
        btnDown.addActionListener(click);
        // make frame handle keyboard inputs
        this.addKeyListener(key);
    }

    /**
     * Set tile value as specified on the Game2048.GamePanel.
     * @param col column index of board (0-3)
     * @param row row index of board (0-3)
     * @param value Integer
     */
    public void setBoardValue(int col, int row, int value) {
        panelGame.setValue(col, row, value);
    }

    /** Update display of game board after setting values. */
    public void updateGameBoard() {
        panelGame.repaint();
    }

    /** Set buttons focusable to false so that frame can listen to keyboard input events. */
    public void setButtonsFocusableToFalse() {
        btnLeft.setFocusable(false);
        btnRight.setFocusable(false);
        btnUp.setFocusable(false);
        btnDown.setFocusable(false);
    }

}
