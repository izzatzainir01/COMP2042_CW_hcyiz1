/*
 *  Brick Destroy - A simple arcade video game
 *  Copyright (C) 2021  Izzat Zainir
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import brickdestroy.gui.view.GameEndView;
import brickdestroy.gui.view.GameLoseView;
import brickdestroy.gui.view.GameView;
import brickdestroy.gui.view.GameWinView;
import brickdestroy.gui.view.ScorePromptView;
import brickdestroy.utility.MyTimer;

/**
 * A child class of {@link Controller} that handles the Game section of
 * the game. It is responsible for handling user inputs to control the
 * {@code Game} and switching between the different Views relating to the
 * gameplay.
 * <p>
 * In addition to {@code ActionListener}, the {@code GameController} implements
 * {@code KeyListener} and {@code WindowFocusListener} to listen for the user's
 * keyboard inputs to control the game and stop the game upon losing focus of
 * the game window respectively.
 * <p>
 * The {@code GameController} also handles saving the user's username and score
 * into a .csv file via the {@code ScorePromptView} and {@code ScoreModel}. It
 * will prompt the user to enter a username before the game starts. It will then
 * save the score every time the user exits the game via the
 * {@code GameEndViews}. The score will not be saved upon exiting via the
 * {@code GamePauseView}.
 * 
 * @see Game
 * @see GameView
 * @see GamePauseView
 * @see GameEndView
 * @see ScorePromptView
 * @see ScoreModel
 */
public class GameController extends Controller implements KeyListener, WindowFocusListener {

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
     * A child class of {@link Controller} that handles the Game section of
     * the game. It is responsible for handling user inputs to control the
     * {@code Game} and switching between the different Views relating to the
     * gameplay.
     * <p>
     * In addition to {@code ActionListener}, the {@code GameController} implements
     * {@code KeyListener} and {@code WindowFocusListener} to listen for the user's
     * keyboard inputs to control the game and stop the game upon losing focus of
     * the game window respectively.
     * <p>
     * The {@code GameController} also handles saving the user's username and score
     * into a .csv file via the {@code ScorePromptView} and {@code ScoreModel}. It
     * will prompt the user to enter a username before the game starts. It will then
     * save the scores every time the user exits the game via the
     * {@code GameEndViews}. The score will not be saved upon exiting via the
     * {@code GamePauseView}.
     * 
     * @param frame The {@code MainFrame}
     * 
     * @see Game
     * @see GameView
     * @see GamePauseView
     * @see GameEndView
     * @see ScorePromptView
     * @see ScoreModel
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

    /**
     * Initialises the game.
     */
    private void initialise() {
        // Define the Game and its view
        game = new Game();
        gameView = new GameView(game);

        // Define the debug console
        debugConsole = new DebugConsole(game);

        // Set the timer action
        MyTimer.addTimerAction(e -> {
            gameView.revalidate();
            gameView.repaint();
            game.tick();

            // When the player clears all rounds
            if (game.isGameCompleted()) {
                scores.addScore(game.getTotalScore());
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
                scores.addScore(game.getTotalScore());
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
     * Exist the game.
     */
    private void exitGame() {
        MyTimer.stopTimer();
        panel.removeKeyListener(this);
        frame.addMenuController();
    }

    /**
     * Defines the {@code GameEndViews'} buttons' actions.
     * <p>
     * {@inheritDoc}
     */
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
                isPaused = false;
                initialise();
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

            // GameWinView's buttons
            case GameWinView.RESTART:
                initialise();
                removeView(gameComplete);
                break;

            case GameLoseView.RESTART:
                initialise();
                removeView(gameOver);
                break;

            // For all GameEndView's exit button
            case GameEndView.EXIT:
                exitGame();
                break;

            default:
                break;
        }

    }

    /**
     * Handles controlling {@code Player} movements, starting/stopping the game, pausing the
     * game, and enabling the {@link DebugConsole}.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (this.game != null) {
            switch (keyEvent.getKeyCode()) {

                // A key pressed
                case KeyEvent.VK_A:
                    game.movePlayerLeft(true);
                    break;

                // D key pressed
                case KeyEvent.VK_D:
                    game.movePlayerRight(true);
                    break;

                // Space pressed
                case KeyEvent.VK_SPACE:
                    if (!isPaused)
                        if (!game.isGameStopped()) {
                            game.setGameStopped(true);
                            gameView.repaint();
                        } else {
                            game.setGameStopped(false);
                        }
                    break;

                // Escape pressed
                case KeyEvent.VK_ESCAPE:
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
                    break;

                // F1 pressed
                case KeyEvent.VK_F1:
                    game.setGameStopped(true);
                    debugConsole.setVisible(true);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Calls for the {@code Player} to stop moving left or right, depending on the
     * key released.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (this.game != null) {
            switch (keyEvent.getKeyCode()) {
                // A key released
                case KeyEvent.VK_A:
                    game.movePlayerLeft(false);
                    break;

                // D key released
                case KeyEvent.VK_D:
                    game.movePlayerRight(false);
                    break;
            }
        }
    }

    /**
     * Stops the {@code Game} upon losing focus of the game window.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void windowLostFocus(WindowEvent e) {
        if (game != null)
            game.setGameStopped(true);
    }

    /**
     * <b>Unused in this game.</b>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void windowGainedFocus(WindowEvent e) {
    }

    /**
     * <b>Unused in this game.</b>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
}
