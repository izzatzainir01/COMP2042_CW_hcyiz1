package brickdestroy.main;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import brickdestroy.debug.DebugConsole;
import brickdestroy.utility.MyButton;

public class GameController extends JPanel implements KeyListener {

    private GameFrame frame;
    private int width = GameFrame.WIDTH;
    private int height = GameFrame.HEIGHT;

    private Game game;
    private GamePause pause;

    private DebugConsole debugConsole;

    private Timer gameTimer;

    private boolean isPaused = false;

    /**
     * The {@code GameController} class is the Controller for the game's actual
     * gameplay, which includes the {@code Game} and {@code GamePause} views. It
     * is responsible for getting the user input to switch between the two views as
     * well as controlling the game.
     * <p>
     * The {@code GameFrame} parameter is necessary in order for the GameController
     * to call the GameFrame to change Controllers.
     * 
     * @param frame - The {@code GameFrame}.
     */
    public GameController(GameFrame frame) {
        // Define the frame
        this.frame = frame;

        // Define the Game and Debug console
        game = new Game();
        debugConsole = new DebugConsole(frame, game, this);

        // Initialise the Panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);

        // Game timer
        gameTimer = new Timer(10, e -> {
            if (game.isGameStopped()) {
                gameTimer.stop();
            }

            game.tick();
            revalidate();
            repaint();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        game.render(g2d);
    }

    /**
     * Add the {@code GamePause} view.
     */
    private void addPause() {
        pause = new GamePause();
        initPauseButtonsListener();
        this.add(pause);
        revalidate();
        repaint();
    }

    /**
     * Add the {@code GamePause} view.
     */
    private void removePause() {
        this.remove(pause);
        revalidate();
        repaint();
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
                    repaint();
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
                addPause();
                gameTimer.stop();
                repaint();
            } else {
                game.setGameStopped(false);
                removePause();
                gameTimer.start();
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

    /**
     * Add {@code ActionListeners} on the GamePause's buttons.
     */
    private void initPauseButtonsListener() {
        MyButton continueButton = pause.getContinueButton();
        MyButton restart = pause.getRestartButton();
        MyButton exitMenu = pause.getExitMenuButton();
        MyButton exitDesktop = pause.getExitDesktopButton();

        // Continue button resumes the game
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == continueButton) {
                    isPaused = false;
                    game.setGameStopped(false);
                    gameTimer.start();
                    removePause();
                }
            }
        });
        // Restart button restarts the game
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == restart) {
                    game.ballReset();
                    game.wallReset();
                    game.setGameStopped(true);
                    isPaused = false;
                    removePause();
                }
            }
        });
        // ExitMenu button calls the GameFrame to add the MenuController and remove the
        // GameController
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitMenu) {
                    frame.addMenuController();
                    frame.removeGameController();
                }
            }
        });
        // ExitDesktop button calls the GameFrame to exit
        exitDesktop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitDesktop) {
                    frame.exit();
                }
            }
        });
    }

    // Unused
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

}
