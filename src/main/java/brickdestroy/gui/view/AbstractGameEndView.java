package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class AbstractGameEndView extends MyAbstractView {

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
     * @param button1Text The text on the Secondary button
     */
    public AbstractGameEndView(String title, String button1Text, String button1Command, String button2Command) {
        // Call the super constructor
        super(button1Text, "Exit", button1Command, button2Command);

        // Define the title
        this.title = title;
    }

    /**
     * Draw the background image and texts.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int stringW;

        // Define text colour
        g2d.setPaint(Color.BLACK);

        // Draw round completed message
        g2d.setFont(setFontSize((int) (width * 0.078)));
        stringW = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, (int) (width * 0.5) - (stringW / 2), (int) (height * 0.25));

        // Draw your score message
        g2d.setFont(setFontSize((int) (width * 0.052)));
        stringW = g2d.getFontMetrics().stringWidth("Score:");
        g2d.drawString("Score:", (int) (width * 0.5) - (stringW / 2), (int) (height * 0.4));

        // Draw score
        g2d.setFont(setFontSize((int) (width * 0.104)));
        stringW = g2d.getFontMetrics().stringWidth(scoreString);
        g2d.drawString(scoreString, (int) (width * 0.5) - (stringW / 2), (int) (height * 0.55));
    }

    /**
     * Set the score of this view.
     * 
     * @param score The new score
     */
    public void setScore(int score) {
        this.score = score;
        scoreString = String.format("%d", this.score);
        revalidate();
        repaint();
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
