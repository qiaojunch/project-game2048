package Game2048;

import javax.swing.*;

/** Launch game2048 and allow users to play. */
public class Launcher {

    public static void main(String[] args) {
        GameFrame view = new GameFrame();
        Model model = new Model();

        GameController controller = new GameController(view, model);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }
}
