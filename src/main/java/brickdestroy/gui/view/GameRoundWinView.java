package brickdestroy.gui.view;

public class GameRoundWinView extends AbstractGameEndView {

    public static final String NEXT = "GAME_NEXT";

    public GameRoundWinView() {
        super("Level Completed!", "Next", NEXT);
    }
    
}
