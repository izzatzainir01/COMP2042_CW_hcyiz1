package brickdestroy.gui.controller;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import brickdestroy.gui.MainFrame;

public abstract class AbstractController extends JPanel {

    protected MainFrame frame;
    protected int width = MainFrame.getWidth();
    protected int height = MainFrame.getHeight();

    /**
     * The {@code AbstractController} class is an abstract class that represents a
     * Controller for this game. It provides a template that all Controllers in this
     * game can use in order to avoid reusing the same code.
     * <p>
     * It is responsible for predefining some properties of the {@code JPanel} that
     * it extends and a method to add and remove a View from itself.
     * 
     * @param frame The {@code MainFrame}
     */
    protected AbstractController(MainFrame frame) {

        // Define the frame
        this.frame = frame;

        // Define the controller's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
    }

    /**
     * Add a {@code JLabel}, or a View, to this controller. This method
     * automatically calls the {@code revalidate()} and {@code repaint()} methods
     * upon adding a View.
     * 
     * @param view The new view to be added to this controller
     */
    protected void addView(JLabel view) {
        add(view);
        revalidate();
        repaint();

    }

    /**
     * Remove a {@code JLabel}, or a View, from this controller. This method
     * automatically calls the {@code revalidate()} and {@code repaint()} methods
     * upon removing a View.
     * 
     * @param view The new view to be removed from this controller
     */
    protected void removeView(JLabel view) {
        remove(view);
        revalidate();
        repaint();
    }

}
