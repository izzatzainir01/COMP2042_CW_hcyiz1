package brickdestroy.debug;

import javax.swing.*;

import brickdestroy.elements.*;
import brickdestroy.main.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugConsole extends JDialog implements WindowListener {

    private static final String TITLE = "Debug Console";

    private GameFrame owner;
    private DebugPanel debugPanel;
    private GameController gameBoard;
    private Game game;

    public DebugConsole(GameFrame owner, Game game, GameController gameBoard) {

        this.game = game;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(game);
        this.add(debugPanel, BorderLayout.CENTER);

        this.pack();
    }

    private void initialize() {
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    private void setLocation() {
        int x = ((GameFrame.WIDTH - this.getWidth()) / 2) + owner.getX();
        int y = ((GameFrame.HEIGHT - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x, y);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = game.getBall();
        debugPanel.setValues(b.getSpeedX(), b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
