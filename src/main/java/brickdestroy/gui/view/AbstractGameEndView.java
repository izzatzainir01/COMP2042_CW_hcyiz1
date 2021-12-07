package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * An abstract child class of {@link AbstractAllView} that represents the View
 * for when the game is stopped due to completing a round or the game, or losing
 * the game. This class predefines a template that extends its parents' template
 * that all Game End Views follow. Since all Game End Views have an Exit button,
 * this class also predefines the text and the action command of the Second
 * button.
 * <p>
 * The {@code AbstractGameEndView} class's content consists of a title and a
 * display of the score.
 */
public abstract class AbstractGameEndView extends AbstractAllView {

    public static final String EXIT = "END_VIEW";

    private String title = "";
    private String scoreString = "";
    private int score = 0;

    /**
     * An abstract child class of {@link AbstractAllView} that represents the View
     * for when the game is stopped due to completing a round or the game, or losing
     * the game. This class predefines a template that extends its parents' template
     * that all Game End Views follow. Since all Game End Views have an Exit button,
     * this class also predefines the text and the action command of the Second
     * button.
     * <p>
     * The {@code AbstractGameEndView} class's content consists of a title and a
     * display of the score.
     * 
     * @param title          The title of this view
     * @param button1Text    The text of the First button
     * @param button1Command The action command of the First button
     */
    public AbstractGameEndView(String title, String button1Text, String button1Command) {
        // Call the super constructor
        super(button1Text, "Exit", button1Command, EXIT);

        // Define the title
        this.title = title;
    }

    /**
     * Draw the background image, the title and the score.
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
     * Sets the score of this view.
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
