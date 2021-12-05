package brickdestroy.gui.controller;

import java.awt.event.ActionEvent;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            // HomeMenuView's buttons
            case MenuHomeView.START:
                frame.addGameController();
                break;

            case MenuHomeView.INFO:
                frame.addInfoController();
                break;

            case MenuHomeView.SCORES:
                addView(scores = new ScoreListView());
                removeView(home);
                break;

            case MenuHomeView.EXIT:
                frame.exit();
                break;

            // ScoreListView's buttons
            case ScoreListView.BACK:
                addView(home = new MenuHomeView());
                removeView(scores);
                break;

            default:
                break;
        }

    }

}
