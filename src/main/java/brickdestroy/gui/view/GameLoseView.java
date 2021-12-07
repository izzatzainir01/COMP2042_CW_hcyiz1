package brickdestroy.gui.view;

/**
 * A child class of {@link GameEndView} that represents the View for
 * when the user loses the game. It is responsible for defining the title, the
 * First button action command and text.
 */
public class GameLoseView extends GameEndView {

    public static final String RESTART = "LOSE_RESTART";

    /**
     * A child class of {@link GameEndView} that represents the View for
     * when the user loses the game. It is responsible for defining the title, the
     * First button action command and text.
     */
    public GameLoseView() {
        super("You Lose!", "Restart", RESTART);
    }

}
