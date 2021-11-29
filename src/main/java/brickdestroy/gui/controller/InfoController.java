package brickdestroy.gui.controller;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.InfoControlsView;
import brickdestroy.gui.view.InfoDescriptionView;

public class InfoController extends JPanel {

    private MainFrame frame;
    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private InfoDescriptionView description;
    private InfoControlsView controls;

    public InfoController(MainFrame frame) {

        // Define frame
        this.frame = frame;

        // Initialise the panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        // Add the description view
        addView(description = new InfoDescriptionView());
        initDescriptionButtonsListeners();
    }

    /**
     * Add a {@code Component} to this controller and automatically call
     * {@code revalidate()} and {@code repaint()}.
     * 
     * @param comp The component to be added
     */
    private void addView(Component comp) {
        this.add(comp);
        revalidate();
        repaint();
    }

    /**
     * Remove a {@code Component} from this controller and automatically call
     * {@code revalidate()} and {@code repaint()}.
     * 
     * @param comp The component to be removed
     */
    private void removeView(Component comp) {
        this.remove(comp);
        revalidate();
        repaint();
    }

    /**
     * Add {@code ActionListeners} on the InfoDescriptionView's buttons.
     */
    private void initDescriptionButtonsListeners() {

        description.setBackAction(e -> frame.addController(new MenuController(frame)));

        description.setSwitcherAction(e -> {
            addView(controls = new InfoControlsView());
            initControlsButtonsListener();
            removeView(description);
        });
    }

    /**
     * Add {@code ActionListeners} on the InfoControlsView's buttons.
     */
    private void initControlsButtonsListener() {

        controls.setBackAction(e -> frame.addController(new MenuController(frame)));

        controls.setSwitcherAction(e -> {
            addView(description = new InfoDescriptionView());
            initDescriptionButtonsListeners();
            removeView(controls);
        });
    }

}
