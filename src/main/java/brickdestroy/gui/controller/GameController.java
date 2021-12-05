package brickdestroy.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import brickdestroy.elements.Game;
import brickdestroy.gui.DebugConsole;
import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.ScoreModel;
import brickdestroy.gui.view.GamePauseView;
import brickdestroy.gui.view.GameRoundWinView;
import brickdestroy.gui.view.GameLoseView;
import brickdestroy.gui.view.GameView;
import brickdestroy.gui.view.GameWinView;
import brickdestroy.gui.view.ScorePromptView;
import brickdestroy.utility.MyTimer;

public class GameController extends AbstractController implements KeyListener, WindowFocusListener {

    private Game game;
    private GameView gameView;
    private GamePauseView pause;

    private ScorePromptView prompt;
    private ScoreModel scores;

    private GameRoundWinView roundComplete;
    private GameLoseView gameOver;
    private GameWinView gameComplete;

    private DebugConsole debugConsole;

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

        // Define the score prompt view
        prompt = new ScorePromptView();

        // Add the prompt view upon first launching the game
        addView(prompt);
    }

    private void initialise() {
        // Define the Game and its view
        game = new Game();
        gameView = new GameView(game);

        // Define the debug console
        debugConsole = new DebugConsole(game, this);

        // Set the timer action
        MyTimer.addTimerAction(e -> {
            gameView.revalidate();
            gameView.repaint();
            game.tick();

            // When the player clears all rounds
            if (game.isGameCompleted()) {
                // Add the total score to the view
                gameComplete = new GameWinView();
                gameComplete.setScore(game.getTotalScore());

                // Add the Game Complete view
                addView(gameComplete);
                removeView(gameView);
                MyTimer.stopTimer();
            }

            // When a round is successfully completed
            else if (game.getBrickCount() == 0) {
                // Add the total score to the view
                roundComplete = new GameRoundWinView();
                roundComplete.setScore(game.getTotalScore());

                // Add the Round Completed view
                addView(roundComplete);
                removeView(gameView);
                MyTimer.stopTimer();
            }

            // When the player loses the game
            else if (game.getAttemptCount() == 0) {
                // Add the total score to the view
                gameOver = new GameLoseView();
                gameOver.setScore(game.getTotalScore());

                // Add the Game Over view
                addView(gameOver);
                removeView(gameView);
                MyTimer.stopTimer();
            }
        });

        // Add the GameView and start the timer
        addView(gameView);
        MyTimer.startTimer();
    }

    /**
     * Exit the game.
     */
    private void exitGame() {
        MyTimer.stopTimer();
        panel.removeKeyListener(this);
        frame.addMenuController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            // ScorePromptsView buttons
            case ScorePromptView.PLAY:
                scores = new ScoreModel();
                scores.setUsername(prompt.getUsername());
                initialise();
                removeView(prompt);
                break;

            case ScorePromptView.EXIT:
                exitGame();
                break;

            // GamePauseView's buttons
            case GamePauseView.CONTINUE:
                MyTimer.startTimer();
                isPaused = false;
                addView(gameView);
                removeView(pause);
                break;

            case GamePauseView.RESTART:
                MyTimer.startTimer();
                game.levelReset();
                game.setGameStopped(true);
                isPaused = false;
                addView(gameView);
                removeView(pause);
                break;

            case GamePauseView.MENU:
                exitGame();
                break;

            case GamePauseView.DESKTOP:
                frame.exit();
                break;

            // GameRoundWinView's buttons
            case GameRoundWinView.NEXT:
                MyTimer.startTimer();
                game.nextLevel();
                addView(gameView);
                removeView(roundComplete);
                break;

            case GameRoundWinView.EXIT:
                scores.addScore(game.getTotalScore());
                exitGame();
                break;

            // GameWinView's buttons
            case GameWinView.RESTART:
                initialise();
                removeView(gameComplete);
                break;

            case GameWinView.EXIT:
                scores.addScore(game.getTotalScore());
                exitGame();
                break;

            case GameLoseView.RESTART:
                initialise();
                removeView(gameOver);
                break;

            case GameLoseView.EXIT:
                exitGame();
                break;

            default:
                break;
        }

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
                MyTimer.stopTimer();
                game.setGameStopped(true);
                addView(pause = new GamePauseView());
                removeView(gameView);
            } else {
                MyTimer.startTimer();
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

    /**
     * Pause the game upon lost focus
     */
    @Override
    public void windowLostFocus(WindowEvent e) {
        if (game != null)
            game.setGameStopped(true);
    }

    // Unused
    @Override
    public void windowGainedFocus(WindowEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
}
