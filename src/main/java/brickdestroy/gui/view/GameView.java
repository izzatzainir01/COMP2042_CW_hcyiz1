package brickdestroy.gui.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import brickdestroy.elements.Game;
import brickdestroy.gui.MainFrame;

public class GameView extends JLabel {

    private Game game;
    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

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

        game.render(g2d);
    }
}
