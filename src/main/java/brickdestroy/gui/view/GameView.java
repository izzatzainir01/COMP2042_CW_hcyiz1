package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import brickdestroy.elements.Game;
import brickdestroy.gui.MainFrame;

/**
 * A View class that provides a visual representation of the {@link Game}.
 * It is the only View class that does not extend {@code AbstractAllView} as
 * there is no background image nor buttons in it. It draws all the game
 * elements as well as some other information about the game onto itself.
 * <p>
 * The {@code GameView} class is responsible for drawing the Bricks, Player,
 * Ball, brick count, attempt count and started/stopped status of the
 * {@code Game}.
 * 
 * @see brickdestroy.elements.Brick Brick
 * @see brickdestroy.elements.Player Player
 * @see brickdestroy.elements.Ball Ball
 */
public class GameView extends JLabel {

    private Game game;
    private int width = MainFrame.WIDTH;
    private int height = MainFrame.HEIGHT;

    private String counts = "";

    /**
     * A View class that provides a visual representation of the {@link Game}.
     * It is the only View class that does not extend {@code AbstractAllView} as
     * there is no background image nor buttons in it. It draws all the game
     * elements as well as some other information about the game onto itself.
     * <p>
     * The {@code GameView} class is responsible for drawing the Bricks, Player,
     * Ball, brick count, attempt count and started/stopped status of the
     * {@code Game}.
     * 
     * @param game The {@code Game}
     * 
     * @see brickdestroy.elements.Brick Brick
     * @see brickdestroy.elements.Player Player
     * @see brickdestroy.elements.Ball Ball
     */
    public GameView(Game game) {
        this.game = game;

        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Draws the game's elements, brick count and attempt count.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Render the game's bricks, ball and player
        game.render(g2d);

        // Set message font and colour
        g2d.setFont(new Font("Impact", Font.PLAIN, (int) (width * 0.03)));
        g2d.setColor(Color.BLUE);

        // Get font width and height
        int fontWidth = g2d.getFontMetrics().stringWidth(counts);
        int fontHeight = g2d.getFontMetrics().getHeight();

        // Draw the counts text
        counts = String.format("Bricks: %d Balls: %d", game.getBrickCount(), game.getAttemptCount());
        g2d.drawString(counts, width / 2 - fontWidth / 2, height / 2);

        // Draw the SPACE to start message if the game is stopped
        if (game.isGameStopped()) {
            g2d.setFont(new Font("Impact", Font.PLAIN, (int) (width * 0.02)));
            fontWidth = g2d.getFontMetrics().stringWidth("Press SPACE to start");
            g2d.drawString("Press SPACE to start", width / 2 - fontWidth / 2, height / 2 + fontHeight);
        }
    }
}
