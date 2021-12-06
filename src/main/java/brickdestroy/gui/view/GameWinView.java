package brickdestroy.gui.view;

public class GameWinView extends AbstractGameEndView {

    public static final String RESTART = "WIN_RESTART";

    public GameWinView() {
        super("You Won!", "Restart", RESTART);
    }

}
