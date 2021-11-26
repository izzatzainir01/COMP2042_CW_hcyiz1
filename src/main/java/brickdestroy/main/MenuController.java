package brickdestroy.main;

import javax.swing.JPanel;

import java.awt.Dimension;

public class MenuController extends JPanel {

    private GameFrame frame;
    private int width = GameFrame.WIDTH;
    private int height = GameFrame.HEIGHT;

    private MenuHomeView home;

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
    public MenuController(GameFrame frame) {
        // Define frame
        this.frame = frame;

        // Initialise the panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        // Add the home view upon first launch
        addHome();
    }

    /**
     * Add the {@code MenuHome} view.
     */
    private void addHome() {
        home = new MenuHomeView();
        initHomeButtonsListeners();
        this.add(home);
    }

    /**
     * Remove the {@code MenuHome} view.
     */
    private void removeHome() {
        this.remove(home);
        revalidate();
        repaint();
    }

    /**
     * Add {@code ActionListeners} on the MenuHome's buttons.
     */
    private void initHomeButtonsListeners() {

        home.setStartAction(e -> {
            frame.addGameController();
            frame.removeMenuController();
        });

        home.setInfoAction(e -> System.out.println("Info"));
        home.setExitAction(e -> frame.exit());
    }

}
