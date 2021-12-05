package brickdestroy.gui.view;

public class GameRoundWinView extends AbstractGameEndView {

    public static final String NEXT = "GAME_NEXT";
    public static final String EXIT = "ROUND_NEXT";

    public GameRoundWinView() {
        super("Level Completed!", "Next", NEXT, EXIT);
    }
    
}
