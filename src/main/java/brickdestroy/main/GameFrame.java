package brickdestroy.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * GameFrame is the window frame of the game. It is responsible for defining its
 * size and switching between the two Controllers upon being called.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String TITLE = "Brick Destroy";
    public static int WIDTH;
    public static int HEIGHT;

    private ImageIcon frameIcon;

    private MenuController menu;
    private GameController game;

    private boolean gaming;

    public GameFrame() {
        super();

        // Define frame size based on the screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int) (screen.getWidth() * 0.6);
        HEIGHT = (int) (screen.getHeight() * 0.6);

        // Define frame's icon

        // Initialise frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(TITLE);
        this.setIconImage(frameIcon.getImage());

        // Add Menu Controller upon first launch
        addMenuController();
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addMenuController() {
        menu = new MenuController(this);
        this.add(menu);
    }

    public void removeMenuController() {
        this.remove(menu);
        revalidate();
        repaint();
    }

    public void addGameController() {
        game = new GameController(this);
        this.add(game);
    }

    public void removeGameController() {
        this.remove(game);
        revalidate();
        repaint();
    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
         * the first time the frame loses focus is because it has been disposed to
         * install the GameBoard, so went it regains the focus it's ready to play. of
         * course calling a method such as 'onLostFocus' is useful only if the GameBoard
         * as been displayed at least once
         */
        gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (gaming)
            game.onLostFocus();

    }
}
