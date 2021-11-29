package brickdestroy.gui.controller;

import javax.swing.JPanel;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.InfoControlsView;
import brickdestroy.gui.view.InfoDescriptionView;
import brickdestroy.gui.view.MenuHomeView;

import java.awt.Component;
import java.awt.Dimension;

public class MenuController extends JPanel {

    private MainFrame frame;
    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private MenuHomeView home;
    private InfoDescriptionView description;
    private InfoControlsView controls;

    /**
     * The {@code MenuController} class is the Controller for the game's Main Menu,
     * which includes the {@code MenuHome} and {@code MenuInfo} views. It is
     * responsible for getting the user input to switch between the two views.
     * <p>
     * The {@code GameFrame} parameter is necessary in order for the MenuController
     * to call the GameFrame to change Controllers.
     * 
     * @param frame - The {@code GameFrame}.
     */
    public MenuController(MainFrame frame) {
        // Define frame
        this.frame = frame;

        // Initialise the panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        // Add the home view upon first launch
        addView(home = new MenuHomeView());
        initHomeButtonsListeners();
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
     * Add {@code ActionListeners} on the MenuHomeViews's buttons.
     */
    private void initHomeButtonsListeners() {

        home.setStartAction(e -> {
            frame.addGameController();
            frame.removeMenuController();
        });

        home.setInfoAction(e -> {
            addView(description = new InfoDescriptionView());
            initDescriptionButtonsListeenrs();
            removeView(home);
        });
        
        home.setExitAction(e -> frame.exit());
    }

    /**
     * Add {@code ActionListeners} on the InfoDescriptionView's buttons.
     */
    private void initDescriptionButtonsListeenrs() {

        description.setBackAction(e -> {
            addView(home = new MenuHomeView());
            initHomeButtonsListeners();
            removeView(description);
        });

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

        controls.setBackAction(e -> {
            addView(home = new MenuHomeView());
            initHomeButtonsListeners();
            removeView(controls);
        });

        controls.setSwitcherAction(e -> {
            addView(description = new InfoDescriptionView());
            initDescriptionButtonsListeenrs();
            removeView(controls);
        });
    }

}
