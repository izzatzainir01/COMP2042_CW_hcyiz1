package brickdestroy.gui.view;

/**
 * A child class of {@link GameEndView} that represents the View for
 * when the user clears a round or level. It is responsible for defining the
 * title, the First button action command and text.
 */
public class GameRoundWinView extends GameEndView {

    public static final String NEXT = "GAME_NEXT";

    /**
     * A child class of {@link GameEndView} that represents the View for
     * when the user clears a round or level. It is responsible for defining the
     * title, the First button action command and text.
     */
    public GameRoundWinView() {
        super("Level Completed!", "Next", NEXT);
    }

}
