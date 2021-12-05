package brickdestroy.gui.view;

public class GameLoseView extends AbstractGameEndView {

    public static final String RESTART = "LOSE_RESTART";
    public static final String EXIT = "LOSE_EXIT";

    public GameLoseView() {
        super("You Lose!", "Restart", RESTART, EXIT);
    }
    
}
