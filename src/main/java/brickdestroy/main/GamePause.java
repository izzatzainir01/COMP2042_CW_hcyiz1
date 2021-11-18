package brickdestroy.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public class GamePause extends JLabel {

    private int width;
    private int height;

    private MyImage background;

    private MyButton continueButton;
    private MyButton restart;
    private MyButton exitMenu;
    private MyButton exitDesktop;

    private int buttonW1;
    private int buttonW2;
    private int buttonH;

    /**
     * The {@code GamePause} class is the view for the pause screen during the
     * gameplay. It extends {@code JLabel} as I needed to access the
     * {@code paintComponent()} method to draw the images.
     * <p>
     * It is responsible for defining the components that make up the pause screen
     * and adding them.
     */
    public GamePause() {

        this.width = GameFrame.WIDTH;
        this.height = GameFrame.HEIGHT;

        // Define the background
        background = new MyImage("cement_wall.jpg");

        // Define button sizes
        buttonW1 = (int) (GameFrame.WIDTH * 0.5);
        buttonW2 = (int) (buttonW1 * 0.49);
        buttonH = (int) (GameFrame.HEIGHT * 0.15);

        // Define buttons and their locations
        restart = new MyButton("Restart", buttonW1, buttonH);
        continueButton = new MyButton("Continue", buttonW1, buttonH);
        exitMenu = new MyButton("Menu", buttonW2, buttonH);
        exitDesktop = new MyButton("Desktop", buttonW2, buttonH);

        // Define the restart location first because it's the center of the pause screen
        // so it sort of acts like an anchor.
        restart.setLocation((int) (width / 2 - buttonW1 / 2), (int) (height / 2 - buttonH / 2));
        continueButton.setLocation((int) (width / 2 - buttonW1 / 2), (int) (restart.getY() - buttonH * 1.1));
        exitMenu.setLocation((int) (restart.getX()), (int) (restart.getY() + buttonH * 1.1));
        exitDesktop.setLocation((int) (restart.getX() + buttonW1 - buttonW2), (int) (restart.getY() + buttonH * 1.1));

        // Initialise the Label's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Adding the buttons
        this.add(continueButton);
        this.add(restart);
        this.add(exitMenu);
        this.add(exitDesktop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw the background image
        g2d.drawImage(background.getImage(), 0, 0, null);
    }

    /**
     * Get the Continue button. This is for the GameController to detect an
     * {@code ActionEvent} from outside this class.
     * 
     * @return A {@code MyButton} of the Continue button.
     */
    public MyButton getContinueButton() {
        return continueButton;
    }

    /**
     * Get the Restart button. This is for the GameController to detect an
     * {@code ActionEvent} from outside this class.
     * 
     * @return A {@code MyButton} of the Restart button.
     */
    public MyButton getRestartButton() {
        return restart;
    }

    /**
     * Get the ExitMenu button. This is for the GameController to detect an
     * {@code ActionEvent} from outside this class.
     * 
     * @return A {@code MyButton} of the Exit button.
     */
    public MyButton getExitMenuButton() {
        return exitMenu;
    }

    /**
     * Get the ExitDesktop button. This is for the GameController to detect an
     * {@code ActionEvent} from outside this class.
     * 
     * @return A {@code MyButton} of the ExitDesktop button.
     */
    public MyButton getExitDesktopButton() {
        return exitDesktop;
    }
}
