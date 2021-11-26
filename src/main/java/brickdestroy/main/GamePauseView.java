package brickdestroy.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public class GamePauseView extends JLabel {

    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

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
    public GamePauseView() {

        // Define the background
        background = new MyImage("cement_wall.jpg");

        // Define button sizes
        buttonW1 = (int) (width * 0.5);
        buttonW2 = (int) (buttonW1 * 0.49);
        buttonH = (int) (height * 0.15);

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
     * Paint the Pause View's background image.
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw the background image
        g2d.drawImage(background.getImage(), 0, 0, null);
    }

    /**
     * Set an {@code Action} for the Continue button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setContinueAction(ActionListener l) {
        continueButton.addActionListener(l);
    }

    /**
     * Set an {@code Action} for the Restart button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setRestartAction(ActionListener l) {
        restart.addActionListener(l);
    }

    /**
     * Set an {@code Action} for the Exit Menu button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setExitMenuAction(ActionListener l) {
        exitMenu.addActionListener(l);
    }

    /**
     * Set an {@code Action} for the Exit Desktop button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setExitDesktopAction(ActionListener l) {
        exitDesktop.addActionListener(l);
    }
}
