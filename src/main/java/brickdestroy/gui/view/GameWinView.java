package brickdestroy.gui.view;

public class GameWinView extends AbstractGameEndView {

    public static final String RESTART = "WIN_RESTART";
    public static final String EXIT = "WIN_EXIT";

    public GameWinView() {
        super("You Won!", "Restart", RESTART, EXIT);
    }

}
