package brickdestroy.gui.controller;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.MenuHomeView;

public class MenuController extends AbstractController {

    private MenuHomeView home;

    /**
     * The {@code MenuController} class is the Controller for the game's Main Menu,
     * which includes the {@code MenuHome} view. It is responsible for getting the
     * user input to switch to the {@code InfoController}.
     * <p>
     * The {@code GameFrame} parameter is necessary in order for the MenuController
     * to call the GameFrame to change Controllers.
     * 
     * @param frame - The {@code MainFrame}.
     */
    public MenuController(MainFrame frame) {
        // Call the super constructor
        super(frame);

        // Define the home view
        home = new MenuHomeView();

        // Add the home view upon first launch
        addView(home);
        initHomeButtonsListeners();
    }

    /**
     * Add {@code ActionListeners} on the MenuHomeViews's buttons.
     */
    private void initHomeButtonsListeners() {
        home.setStartAction(e -> frame.addGameController());
        home.setInfoAction(e -> frame.addInfoController());
        home.setExitAction(e -> frame.exit());
    }

}
