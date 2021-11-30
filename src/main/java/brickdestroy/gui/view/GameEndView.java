package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import brickdestroy.gui.MainFrame;
import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public class GameEndView extends JLabel {

    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private MyImage background;

    private MyButton secondary;
    private MyButton exit;

    private String title = "";
    private String scoreString = "";
    private int score = 0;

    /**
     * The {@code GameEndView} class is a View class that represents the View for
     * when the game is stopped due to completing a round/game, or losing the game.
     * <p>
     * It is responsible for defining the common components that make up the
     * different variations of this View and adding them to itself. It contains a
     * 'Secondary' button that users can define its text and action from the
     * controller.
     * 
     * @param title      The title of the view
     * @param score      The score that is displayed on the view
     * @param buttonText The text on the Secondary button
     */
    public GameEndView(String title, int score, String buttonText) {

        // Define the title
        this.title = title;

        // Define background image
        background = new MyImage("cement_wall.jpg");

        // Define buttons
        secondary = new MyButton(buttonText, (int) (width * 0.2), (int) (height * 0.15));
        exit = new MyButton("Exit", (int) (width * 0.2), (int) (height * 0.15));

        // Set button locations
        secondary.setLocation((int) (width * 0.35 - secondary.getWidth() / 2),
                (int) (height * 0.7));
        exit.setLocation((int) (width * 0.65 - exit.getWidth() / 2), (int) (height * 0.7));

        // Define the score
        this.score = score;
        scoreString = String.format("%d", this.score);

        // Initialise the label's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Add the buttons
        this.add(secondary);
        this.add(exit);
    }

    /**
     * Paint the background image and texts.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int stringW;

        // Draw background image
        g2d.drawImage(background.getImage(), 0, 0, width, height, null);

        // Define text colour
        g2d.setPaint(Color.BLACK);

        // Draw round completed message
        g2d.setFont(setFontSize((int) (width * 0.078)));
        stringW = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, (int) (width * 0.5) - (stringW / 2), (int) (height * 0.25));

        // Draw your score message
        g2d.setFont(setFontSize((int) (width * 0.052)));
        stringW = g2d.getFontMetrics().stringWidth("Your score:");
        g2d.drawString("Your score:", (int) (width * 0.5) - (stringW / 2), (int) (height * 0.4));

        // Draw score
        g2d.setFont(setFontSize((int) (width * 0.104)));
        stringW = g2d.getFontMetrics().stringWidth(scoreString);
        g2d.drawString(scoreString, (int) (width * 0.5) - (stringW / 2), (int) (height * 0.55));
    }

    /**
     * Set an {@code Action} for the Next Level button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setSecondaryAction(ActionListener l) {
        secondary.addActionListener(l);
    }

    /**
     * Set an {@code Action} for the Exit button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setExitAction(ActionListener l) {
        exit.addActionListener(l);
    }

    /**
     * A private method to easily change the font's size, since all fonts in this
     * view are the same but not all the texts have the same size.
     * 
     * @param size The size of the font
     * @return A {@code Font} object
     */
    private Font setFontSize(int size) {
        return new Font("Impact", Font.PLAIN, size);
    }

}
