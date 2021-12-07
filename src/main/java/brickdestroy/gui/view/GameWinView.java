package brickdestroy.gui.view;

/**
 * A child class of {@link GameEndView} that represents the View for
 * when the user clears all rounds or levels. It is responsible for defining the
 * title, the First button action command and text.
 */
public class GameWinView extends GameEndView {

    public static final String RESTART = "WIN_RESTART";

    /**
     * A child class of {@link GameEndView} that represents the View for
     * when the user clears all rounds or levels. It is responsible for defining the
     * title, the First button action command and text.
     */
    public GameWinView() {
        super("You Won!", "Restart", RESTART);
    }

}
