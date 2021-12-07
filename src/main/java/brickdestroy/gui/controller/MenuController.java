package brickdestroy.gui.controller;

import java.awt.event.ActionEvent;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.ScoreModel;
import brickdestroy.gui.view.MenuHomeView;
import brickdestroy.gui.view.ScoreListView;

/**
 * A child class of {@link AbstractController} that handles the Main Menu
 * section of the game. It is responsible for switching between the different
 * sections of the game, namely the Game section, the Info section and the High
 * Scores section.
 * 
 * @see ScoreListView
 * @see InfoController
 * @see GameController
 */
public class MenuController extends AbstractController {

    private MenuHomeView home;
    private ScoreListView scores;

    /**
     * A child class of {@link AbstractController} that handles the Main Menu
     * section of the game. It is responsible for switching between the different
     * sections of the game, namely the Game section, the Info section and the High
     * Scores section.
     * 
     * @param frame The {@code MainFrame}
     * 
     * @see ScoreListView
     * @see InfoController
     * @see GameController
     */
    public MenuController(MainFrame frame) {
        // Call the super constructor
        super(frame);

        // Add the home view upon first launch
        addView(home = new MenuHomeView());
    }

    /**
     * Defines the {@code MenuHomeViews'} and {@code ScoreListViews'} buttons'
     * actions.
     * <p>
     * {@inheritDoc}
     */
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
                addView(scores = new ScoreListView(new ScoreModel()));
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
