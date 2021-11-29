package brickdestroy.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public class GameRoundCompleteView extends JLabel {

    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private MyImage background;

    private MyButton nextLevel;
    private MyButton exit;

    private final String roundComplete = "Round Completed!";
    private final String yourScore = "Your Score:";
    private String scoreString = "";
    private int score = 0;

    /**
     * The {@code GameRoundCompleteView} class is the view for when the player
     * successfully completes a round. It extends {@code JLabel} as I need to access
     * the {@code paintComponent()} method.
     * 
     * It is responsible for defining its components and drawing them.
     * 
     * @param score The score that is displayed
     */
    public GameRoundCompleteView(int score) {

        // Define background image
        background = new MyImage("cement_wall.jpg");

        // Define buttons
        nextLevel = new MyButton("Next", (int) (width * 0.2), (int) (height * 0.15));
        exit = new MyButton("Exit", (int) (width * 0.2), (int) (height * 0.15));

        // Set button locations
        nextLevel.setLocation((int) (width * 0.65 - nextLevel.getWidth() / 2),
                (int) (height * 0.7));
        exit.setLocation((int) (width * 0.35 - exit.getWidth() / 2), (int) (height * 0.7));

        // Define the score
        this.score = score;
        scoreString = String.format("%d", this.score);

        // Initialise the label's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Add the buttons
        this.add(nextLevel);
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
        g2d.setFont(setFontSize(60));
        stringW = g2d.getFontMetrics().stringWidth(roundComplete);
        g2d.drawString(roundComplete, (int) (width * 0.5) - (stringW / 2), (int) (height * 0.25));

        // Draw your score message
        g2d.setFont(setFontSize(30));
        stringW = g2d.getFontMetrics().stringWidth(yourScore);
        g2d.drawString(yourScore, (int) (width * 0.5) - (stringW / 2), (int) (height * 0.4));

        // Draw score
        g2d.setFont(setFontSize(80));
        stringW = g2d.getFontMetrics().stringWidth(scoreString);
        g2d.drawString(scoreString, (int) (width * 0.5) - (stringW / 2), (int) (height * 0.55));
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
