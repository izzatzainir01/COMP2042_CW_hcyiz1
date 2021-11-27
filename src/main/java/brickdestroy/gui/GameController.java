package brickdestroy.gui;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import brickdestroy.elements.Game;

public class GameController extends JPanel implements KeyListener {

    private MainFrame frame;
    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private Game game;
    private GameView gameView;
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

        // Define the frame
        this.frame = frame;

        // Define the Game and Debug console
        game = new Game();
        debugConsole = new DebugConsole(game, this);

        // Initialise the Panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);

        // Add the GameView
        addGameView();

        // Game timer
        gameTimer = new Timer(10, e -> {
            if (game.isGameStopped()) {
                gameTimer.stop();
            }

            game.tick();
            gameView.revalidate();
            gameView.repaint();
        });
    }

    public void repaintGameView() {
        gameView.repaint();
    }

    /**
     * Add the {@code GameView} view.
     */
    private void addGameView() {
        gameView = new GameView(game);
        this.add(gameView);
    }

    /**
     * Remove the {@code GameView} view.
     */
    private void removeGameView() {
        this.remove(gameView);
        revalidate();
        repaint();
    }

    /**
     * Add the {@code GamePause} view. The {@code GameView} is removed automatically
     * through this method.
     */
    private void addPauseView() {
        pause = new GamePauseView();
        initPauseButtonsListener();
        this.add(pause);
        removeGameView();
    }

    /**
     * Add the {@code GamePause} view. The {@code GameView} is added automatically
     * through this method.
     */
    private void removePauseView() {
        addGameView();
        this.remove(pause);
        revalidate();
        repaint();
    }

    /**
     * Add {@code ActionListeners} on the GamePause's buttons.
     */
    private void initPauseButtonsListener() {

        // Continue button resumes the game
        pause.setContinueAction(e -> {
            isPaused = false;
            game.setGameStopped(false);
            gameTimer.start();
            removePauseView();
        });

        // Restart button restarts the game
        pause.setRestartAction(e -> {
            game.ballReset();
            game.wallReset();
            game.setGameStopped(true);
            isPaused = false;
            removePauseView();
        });

        // ExitMenu button calls the GameFrame to add the MenuController and remove the
        // GameController
        pause.setExitMenuAction(e -> {
            frame.addMenuController();
            frame.removeGameController();
        });

        // ExitDesktop button calls the GameFrame to exit the game
        pause.setExitDesktopAction(e -> frame.exit());
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
                if (gameTimer.isRunning()) {
                    game.setGameStopped(true);
                    gameTimer.stop();
                    gameView.repaint();
                } else {
                    game.setGameStopped(false);
                    gameTimer.start();
                }
        }
        // Escape pressed
        if (keyCode == KeyEvent.VK_ESCAPE) {
            isPaused = !isPaused;
            if (isPaused) {
                game.setGameStopped(true);
                addPauseView();
                gameTimer.stop();
                revalidate();
                repaint();
            } else {
                game.setGameStopped(false);
                removePauseView();
                gameTimer.start();
                revalidate();
                repaint();
            }
        }
        // F1 pressed
        if (keyCode == KeyEvent.VK_F1) {
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
