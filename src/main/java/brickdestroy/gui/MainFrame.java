package brickdestroy.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import brickdestroy.gui.controller.GameController;
import brickdestroy.gui.controller.MenuController;
import brickdestroy.utility.MyImage;

public class MainFrame {

    private static int WIDTH;
    private static int HEIGHT;

    private JFrame frame;
    private MyImage frameIcon;

    private MenuController menu;
    private GameController game;

    /**
     * The {@code GameFrame} class handles the game's window frame using JFrame. It
     * is responsible for defining its size, location and some other properties.
     * <p>
     * To some extent, it is also the Main Controller as it is responsible for
     * switching between the two Controllers, {@code MenuController} and
     * {@code GameController}. However, since it does not take any input from the
     * user, I do not consider it a Controller.
     */
    public MainFrame() {

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
        frame.setTitle("Brick Destroy");
        frame.setIconImage(frameIcon.getImage());

        // Add Menu Controller upon first launch
        this.addMenuController();
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Add the {@code MenuController}.
     */
    public void addMenuController() {
        menu = new MenuController(this);
        frame.add(menu);
    }

    /**
     * Remove the {@code MenuController}.
     */
    public void removeMenuController() {
        frame.remove(menu);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Add the {@code GameController}.
     */
    public void addGameController() {
        game = new GameController(this);
        frame.add(game);
    }

    /**
     * Remove the {@code GameController}.
     */
    public void removeGameController() {
        frame.remove(game);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Close/exit the game.
     */
    public void exit() {
        System.out.println("Exiting game...");
        System.out.println("Goodbye, " + System.getProperty("user.name") + "!");
        System.exit(0);
    }

    /**
     * Get the frame's width.
     * 
     * @return An {@code int} of the frame's width
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * Get the frame's height.
     * 
     * @return An {@code int} of the frame's height
     */
    public static int getHeight() {
        return HEIGHT;
    }

    /**
     * Get the frame's X coordinate.
     * 
     * @return An {@code int} of the frame's X coordinate.
     */
    public int getX() {
        return frame.getX();
    }

    /**
     * Get the frame's Y coordinate.
     * 
     * @return An {@code int} of the frame's Y coordinate.
     */
    public int getY() {
        return frame.getY();
    }
}
