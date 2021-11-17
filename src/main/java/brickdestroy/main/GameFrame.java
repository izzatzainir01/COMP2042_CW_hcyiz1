package brickdestroy.main;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import brickdestroy.utility.MyImage;

/**
 * GameFrame is the window frame of the game. It is responsible for defining its
 * size and switching between the two Controllers upon being called.
 */
public class GameFrame {

    private static final String TITLE = "Brick Destroy";
    public static int WIDTH;
    public static int HEIGHT;

    private JFrame frame;
    private MyImage frameIcon;

    private MenuController menu;
    private GameController game;

    public GameFrame() {

        // Define frame size based on the screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int) (screen.getWidth() * 0.5);
        HEIGHT = (int) (screen.getHeight() * 0.6);

        // Define frame's icon
        this.frameIcon = new MyImage("monke.png");

        // Define frame
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle(TITLE);
        frame.setIconImage(frameIcon.getImage());

        // Add Menu Controller upon first launch
        this.addMenuController();
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addMenuController() {
        menu = new MenuController(this);
        frame.add(menu);
    }

    public void removeMenuController() {
        frame.remove(menu);
        frame.revalidate();
        frame.repaint();
    }

    public void addGameController() {
        game = new GameController(this);
        frame.add(game);
    }

    public void removeGameController() {
        frame.remove(game);
        frame.revalidate();
        frame.repaint();
    }

    public void exit() {
        System.out.println("Exiting game...");
        System.out.println("Goodbye, " + System.getProperty("user.name") + "!");
        System.exit(0);
    }

    public int getX() {
        return frame.getX();
    }

    public int getY() {
        return frame.getY();
    }
}
