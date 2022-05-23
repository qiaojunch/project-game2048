package Game2048;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is the controller of the game. It combines the view and the model.
 */
public class GameController implements ActionListener, KeyListener {
    private GameFrame view;
    private Model model;

    /**
     * Initialize the controller, and make it handle actions from view.
     * @param myview GameFrame that represents the view of game
     * @param mymodel Model that represents the logic of game
     */
    public GameController(GameFrame myview, Model mymodel) {
        this.view = myview;
        this.model = mymodel;

        // start a new game with two tiles on board
        this.model.addNewNumber();
        this.model.addNewNumber();

        // controller tells the view to handle listeners
        myview.setListners(this, this);
        // display the game board
        updateTileSquares();

    }

    /** Setup for user interactive */
    public void updateTileSquares() {
        for (int c = 0; c < model.BORAD_SIZE; c++) {
            for (int r = 0; r < model.BORAD_SIZE; r++) {
                view.setBoardValue(c, r, model.getCellValue(r, c));
            }
        }
        // update display
        view.updateGameBoard();

        // check if game over
        if (model.isGameOver()) {
            gameOver();
        }
    }

    /** Pop up GameOver message to user */
    public void gameOver() {
        String[] options = new String[]{"New Game", "Exit"};

        int result = JOptionPane.showOptionDialog(
                view,
                "Game over\nYour score is " + model.getScore(),
                "Game Over!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (result == JOptionPane.YES_OPTION) {
            newGame();   // start a new game
        } else {
            System.exit(0);  // exit the game
        }
    }

    /** Start a new game after game is over. */
    public void newGame() {
        model = new Model();
        // start a new game with two random tiles on board
        this.model.addNewNumber();
        this.model.addNewNumber();

        updateTileSquares();
        view.pack();
    }

    /** Perform the action of sliding tiles on game board via buttons.*/
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Slide up":
                model.slideUp();
                model.addNewNumber();
                updateTileSquares();
                break;
            case "Slide down":
                model.slideDown();
                model.addNewNumber();
                updateTileSquares();
                break;
            case "Slide left":
                model.slideLeft();
                model.addNewNumber();
                updateTileSquares();
                break;
            case "Slide right":
                model.slideRight();
                model.addNewNumber();
                updateTileSquares();
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    /** Make keyBoard shortcuts. Allow users to press arrow keys to slide tiles on game board. */
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                model.slideUp();
                model.addNewNumber();
                updateTileSquares();
                break;
            case KeyEvent.VK_DOWN:
                model.slideDown();
                model.addNewNumber();
                updateTileSquares();
                break;
            case KeyEvent.VK_LEFT:
                model.slideLeft();
                model.addNewNumber();
                updateTileSquares();
                break;
            case KeyEvent.VK_RIGHT:
                model.slideRight();
                model.addNewNumber();
                updateTileSquares();
                break;
        }
        // make frame listen to keyboard inputs
        view.setButtonsFocusableToFalse();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }
}
