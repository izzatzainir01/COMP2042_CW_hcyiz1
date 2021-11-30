package brickdestroy.gui.controller;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import brickdestroy.gui.MainFrame;

public abstract class AbstractController extends JPanel {

    protected MainFrame frame;
    protected int width = MainFrame.getWidth();
    protected int height = MainFrame.getHeight();

    protected JLabel view;

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
     * Add a {@code JLabel}, or a View, to this controller. This method adds the new
     * view before removing the old one from the controller. It automatically calls
     * the {@code revalidte()} and {@code repaint()} methods upon adding and
     * removing a View.
     * 
     * @param view The new view to be added to this controller
     */
    protected void addView(JLabel view) {

        // Set a temporary reference to the original view
        JLabel temp = null;
        if (this.view != null) {
            temp = this.view;
        }

        // Set the view to the new one
        this.view = view;
        this.add(this.view);
        this.revalidate();
        this.repaint();

        // Remove the old view
        if (temp != null) {
            this.remove(temp);
            this.revalidate();
            this.repaint();
            temp = null;
        }

    }

}
