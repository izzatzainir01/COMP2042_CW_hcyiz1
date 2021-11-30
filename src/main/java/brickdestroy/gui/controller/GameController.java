package brickdestroy.gui.controller;

import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import brickdestroy.elements.Game;
import brickdestroy.gui.DebugConsole;
import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.GamePauseView;
import brickdestroy.gui.view.GameRoundCompleteView;
import brickdestroy.gui.view.GameView;

public class GameController extends AbstractController implements KeyListener {

    private Game game;
    private GameView gameView;
    private GameRoundCompleteView roundComplete;
    private GamePauseView pause;

    private DebugConsole debugConsole;

    private Timer gameTimer;

    private boolean isPaused = false;

    /**
     * The {@code GameController} class is the Controller for the game's actual
     * gameplay, which includes the {@code Game} and {@code GamePause} views. It is
     * responsible for getting the user input to switch between the two views as
     * well as controlling the game.
     * <p>
     * The {@code GameFrame} parameter is necessary in order for the GameController
     * to call the GameFrame to change Controllers.
     * 
     * @param frame - The {@code GameFrame}.
     */
    public GameController(MainFrame frame) {
        // Call the super constructor and define some extra properties
        super(frame);
        panel.setFocusable(true);
        panel.addKeyListener(this);

        // Define the Game and Debug console
        game = new Game();
        debugConsole = new DebugConsole(game, this);

        // Define the game timer
        gameTimer = new Timer(10, e -> {
            game.tick();

            // When a round is successfully completedd
            if (game.getBrickCount() == 0) {
                addView(roundComplete = new GameRoundCompleteView(game.getScore()));
                initRoundCompleteButtons();
                removeView(gameView);
                gameTimer.stop();
            }

            gameView.revalidate();
            gameView.repaint();
        });

        // Add the GameView
        addView(gameView = new GameView(game));

        // Start the game timer
        gameTimer.start();
    }

    /**
     * Add {@code ActionListeners} on the GamePauseView's buttons.
     */
    private void initPauseButtonsListener() {

        // Continue button resumes the game
        pause.setContinueAction(e -> {
            isPaused = false;
            addView(gameView);
            removeView(pause);
        });

        // Restart button restarts the game
        pause.setRestartAction(e -> {
            game.levelReset();
            game.setGameStopped(true);
            isPaused = false;
            addView(gameView);
            removeView(pause);
        });

        // ExitMenu button calls the GameFrame to add the MenuController and remove the
        // GameController
        pause.setExitMenuAction(e -> {
            gameTimer.stop();
            panel.removeKeyListener(this);
            new MenuController(frame).addToFrame();
        });

        // ExitDesktop button calls the GameFrame to exit the game
        pause.setExitDesktopAction(e -> frame.exit());
    }

    /**
     * Add {@code ActionListeners} to the GameRoundCompletedView's buttons
     */
    private void initRoundCompleteButtons() {

        roundComplete.setExitAction(e -> {
            gameTimer.stop();
            panel.removeKeyListener(this);
            new MenuController(frame).addToFrame();
        });

        roundComplete.setNextLevelAction(e -> {
            game.nextLevel();
            addView(gameView);
            removeView(roundComplete);
            gameTimer.start();
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        // A key pressed
        if (keyCode == KeyEvent.VK_A)
            game.movePlayerLeft(true);
        // D key pressed
        if (keyCode == KeyEvent.VK_D)
            game.movePlayerRight(true);
        // Space pressed
        if (keyCode == KeyEvent.VK_SPACE) {
            if (!isPaused)
                if (!game.isGameStopped()) {
                    game.setGameStopped(true);
                    gameView.repaint();
                } else {
                    game.setGameStopped(false);
                }
        }
        // Escape pressed
        if (keyCode == KeyEvent.VK_ESCAPE) {
            isPaused = !isPaused;
            if (isPaused) {
                game.setGameStopped(true);
                addView(pause = new GamePauseView());
                initPauseButtonsListener();
                removeView(gameView);
            } else {
                addView(gameView);
                removeView(pause);
            }
        }
        // F1 pressed
        if (keyCode == KeyEvent.VK_F1) {
            game.setGameStopped(true);
            debugConsole.setVisible(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        // A key released
        if (keyCode == KeyEvent.VK_A)
            game.movePlayerLeft(false);
        // D key released
        if (keyCode == KeyEvent.VK_D)
            game.movePlayerRight(false);
    }

    // Unused
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

}
