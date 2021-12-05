package brickdestroy.gui.controller;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.MenuHomeView;
import brickdestroy.gui.view.ScoreListView;

public class MenuController extends AbstractController {

    private MenuHomeView home;
    private ScoreListView scores;

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

        // Add the home view upon first launch
        addView(home = new MenuHomeView());
        initHomeButtonsListeners();
    }

    /**
     * Add {@code ActionListeners} on the MenuHomeViews's buttons.
     */
    private void initHomeButtonsListeners() {
        home.setStartAction(e -> frame.addGameController());
        home.setScoresAction(e -> {
            addView(scores = new ScoreListView());
            initScoresButtons();
            removeView(home);
        });
        home.setInfoAction(e -> frame.addInfoController());
        home.setExitAction(e -> frame.exit());
    }

    /**
     * Add {@code ActionListeners} to the ScoreListViews' buttons.
     */
    private void initScoresButtons() {
        scores.setButton1Action(e -> {
            addView(home = new MenuHomeView());
            initHomeButtonsListeners();
            removeView(scores);
        });
    }

}
