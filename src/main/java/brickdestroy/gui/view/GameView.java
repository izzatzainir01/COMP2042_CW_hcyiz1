package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import brickdestroy.elements.Game;
import brickdestroy.gui.MainFrame;

public class GameView extends JLabel {

    private Game game;
    private int width = MainFrame.WIDTH;
    private int height = MainFrame.HEIGHT;

    private String counts = "";

    /**
     * The {@code GameView} class is the view for the game's elements. It extends
     * {@code JLabel} as I need to access the {@code paintComponent()} method.
     * <p>
     * It is responsible for defining its size and painting the elements of the
     * {@code Game}.
     * 
     * @param game
     */
    public GameView(Game game) {
        this.game = game;

        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Paint the game's elements simply by calling the {@code render()} method.
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
