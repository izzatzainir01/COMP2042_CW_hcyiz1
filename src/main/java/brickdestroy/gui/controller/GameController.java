package brickdestroy.gui.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import brickdestroy.elements.Game;
import brickdestroy.gui.DebugConsole;
import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.ScoreModel;
import brickdestroy.gui.view.GamePauseView;
import brickdestroy.gui.view.GameEndView;
import brickdestroy.gui.view.GameView;
import brickdestroy.gui.view.ScorePromptView;
import brickdestroy.utility.MyTimer;

public class GameController extends AbstractController implements KeyListener, WindowFocusListener {

    private Game game;
    private GameView gameView;
    private GamePauseView pause;

    private ScorePromptView prompt;
    private ScoreModel scores;

    private GameEndView roundComplete;
    private GameEndView gameOver;
    private GameEndView gameComplete;
    private int score = 0;

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
        initPromptButtons();
    }

    private void initialise() {
        // Define the Game and its view
        game = new Game();
        gameView = new GameView(game);

        // Define the debug console
        debugConsole = new DebugConsole(game, this);

        score = 0;

        // Set the timer action
        MyTimer.addTimerAction(e -> {
            gameView.revalidate();
            gameView.repaint();
            game.tick();

            // When the player clears all rounds
            if (game.isGameCompleted()) {
                // Set the cumulative score and add to database
                score = game.getTotalScore();
                scores.addScore(score);

                // Add the score to the view
                gameComplete = new GameEndView("Game Completed!", "Restart");
                gameComplete.setScore(score);

                // Add the Game Complete view
                addView(gameComplete);
                initGameCompletedButtons();
                removeView(gameView);
                MyTimer.stopTimer();
            }

            // When a round is successfully completedd
            else if (game.getBrickCount() == 0) {
                // Set the score of the current round
                score = game.getTotalScore();

                // Add the score to the view
                roundComplete = new GameEndView("Round Completed!", "Next");
                roundComplete.setScore(score);

                // Add the Round Completed view
                addView(roundComplete);
                initRoundCompletedButtons();
                removeView(gameView);
                MyTimer.stopTimer();
            }

            // When the player loses the game
            else if (game.getAttemptCount() == 0) {
                // Set the cumulative score and add to the database.
                score = game.getTotalScore();
                scores.addScore(score);

                // Add the score to the view
                gameOver = new GameEndView("Game Over!", "Restart");
                gameOver.setScore(score);

                // Add the Game Over view
                addView(gameOver);
                initGameOverButtons();
                removeView(gameView);
                MyTimer.stopTimer();
            }
        });

        // Add the GameView and start the timer
        addView(gameView);
        MyTimer.startTimer();
    }

    /**
     * Add {@code ActionListeners} on the Score Prompt's buttons.
     */
    private void initPromptButtons() {
        prompt.setButton1Action(e -> {
            scores = new ScoreModel(prompt.getUsername());
            initialise();
            removeView(prompt);
        });
        prompt.setButton2Action(e -> exitGame());
    }

    /**
     * Add {@code ActionListeners} on the GamePauseView's buttons.
     */
    private void initPauseButtonsListener() {

        // Continue button resumes the game
        pause.setContinueAction(e -> {
            MyTimer.startTimer();
            isPaused = false;
            addView(gameView);
            removeView(pause);
        });

        // Restart button restarts the current round
        pause.setRestartAction(e -> {
            MyTimer.startTimer();
            game.levelReset();
            game.setGameStopped(true);
            isPaused = false;
            score = 0;
            addView(gameView);
            removeView(pause);
        });

        // ExitMenu button exits the game to the Menu
        pause.setExitMenuAction(e -> exitGame());

        // ExitDesktop button calls the GameFrame to exit the game
        pause.setExitDesktopAction(e -> frame.exit());
    }

    /**
     * Add {@code ActionListeners} to the GameRoundCompletedView's buttons
     */
    private void initRoundCompletedButtons() {
        roundComplete.setButton1Action(e -> {
            MyTimer.startTimer();
            game.nextLevel();
            addView(gameView);
            removeView(roundComplete);
        });
        roundComplete.setButton2Action(e -> exitGame());

    }

    /**
     * Add {@code ActionListeners} to the GameCompletedView's buttons.
     */
    private void initGameCompletedButtons() {
        gameComplete.setButton1Action(e -> {
            initialise();
            removeView(gameComplete);
        });
        gameComplete.setButton2Action(e -> exitGame());

    }

    /**
     * Add {@code ActionListeners} to the GameOverView's buttons.
     */
    private void initGameOverButtons() {
        gameOver.setButton1Action(e -> {
            initialise();
            removeView(gameOver);
        });
        gameOver.setButton2Action(e -> exitGame());
    }

    /**
     * Exit the game.
     */
    private void exitGame() {
        MyTimer.stopTimer();
        panel.removeKeyListener(this);
        frame.addMenuController();
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
                initPauseButtonsListener();
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
        isPaused = !isPaused;
        if (isPaused) {
            MyTimer.stopTimer();
            game.setGameStopped(true);
            addView(pause = new GamePauseView());
            initPauseButtonsListener();
            removeView(gameView);
        }
    }

    // Unused
    @Override
    public void windowGainedFocus(WindowEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
}
