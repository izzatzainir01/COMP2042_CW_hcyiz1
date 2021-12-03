package brickdestroy.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import brickdestroy.gui.controller.GameController;
import brickdestroy.gui.controller.InfoController;
import brickdestroy.gui.controller.MenuController;
import brickdestroy.utility.MyImage;
import brickdestroy.utility.MyTimer;

public class MainFrame {

    private static int WIDTH;
    private static int HEIGHT;

    private JFrame frame;
    private MyImage frameIcon;

    private JPanel controller;
    private MenuController menu;
    private InfoController info;
    private GameController game;

    /**
     * The {@code GameFrame} class handles the game's window frame using JFrame. It
     * is responsible for defining its size, location and some other properties.
     * <p>
     * To some extent, it can also be considered as the Main Controller as it is
     * responsible for switching between the two Controllers, {@code MenuController}
     * and {@code GameController}. However, since it does not take any input from
     * the user, I do not consider it a Controller.
     */
    public MainFrame() {

        // Define frame size based on the screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int) (screen.getWidth() * 0.5);
        HEIGHT = (int) (screen.getHeight() * 0.6);

        // Define frame's icon
        frameIcon = new MyImage("monke.png");

        // Define frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Brick Destroy");
        frame.setIconImage(frameIcon.getImage());

        // Initialise MyTimer
        new MyTimer();

        // Add Menu Controller upon first launch
        addMenuController();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Add the Menu Controller to the frame. This method automatically removes the
     * previous controller after adding the new one.
     */
    public void addMenuController() {
        menu = new MenuController(this);
        addController(menu.getPanel());
    }

    /**
     * Add the Info Controller to the frame. This method automatically removes the
     * previous controller after adding the new one.
     */
    public void addInfoController() {
        info = new InfoController(this);
        addController(info.getPanel());
    }

    /**
     * Add the Game Controller to the frame and initialise the game. This method
     * automatically removes the previous controller after adding the new one.
     */
    public void addGameController() {
        game = new GameController(this);
        addController(game.getPanel());
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
     * Add a controller to the frame. This method replaces the previous controller
     * with a new one, then it removes the old one.
     * 
     * @param controller The new controller
     */
    private void addController(JPanel controller) {

        // Set a temporary reference to the original controller
        JPanel temp = null;
        if (this.controller != null) {
            temp = this.controller;
        }

        // Set the controller to the new one
        this.controller = controller;
        frame.add(controller);
        frame.revalidate();
        frame.repaint();

        // Remove the old controller
        if (temp != null) {
            frame.remove(temp);
            frame.revalidate();
            frame.repaint();
            temp = null;
        }
    }

}
