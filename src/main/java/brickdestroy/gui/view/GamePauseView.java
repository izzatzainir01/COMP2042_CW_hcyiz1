package brickdestroy.gui.view;

import java.awt.event.ActionListener;

import brickdestroy.gui.MainFrame;
import brickdestroy.utility.MyButton;

/**
 * A child class of {@link AbstractAllView} that represents the Pause screen
 * during the gameplay. Although this class inherits from
 * {@code AbstractAllView}, it doesn't actually use any of its parent's buttons.
 * It uses its own buttons with distinct action commands and text from that of
 * the parent's.
 * <p>
 * The {@code GamePauseView} is responsible for defining its buttons and adding
 * them to itself.
 */
public class GamePauseView extends AbstractAllView {

    public static final String CONTINUE = "PAUSE_CONTINUE";
    public static final String RESTART = "PAUSE_RESTART";
    public static final String MENU = "PAUSE_MENU";
    public static final String DESKTOP = "PAUSE_DESKTOP";

    private int width = MainFrame.WIDTH;
    private int height = MainFrame.HEIGHT;

    private MyButton continueButton;
    private MyButton restart;
    private MyButton exitMenu;
    private MyButton exitDesktop;

    private int buttonW1;
    private int buttonW2;
    private int buttonH;

    /**
     * A child class of {@link AbstractAllView} that represents the Pause screen
     * during the gameplay. Although this class inherits from
     * {@code AbstractAllView}, it doesn't actually use any of its parent's buttons.
     * It uses its own buttons with distinct action commands and text from that of
     * the parent's.
     * <p>
     * The {@code GamePauseView} is responsible for defining its buttons and adding
     * them to itself.
     */
    public GamePauseView() {
        super(null, null, null, null);
        button1.setVisible(false);
        button2.setVisible(false);

        // Define button sizes
        buttonW1 = (int) (width * 0.5);
        buttonW2 = (int) (buttonW1 * 0.49);
        buttonH = (int) (height * 0.15);

        // Define buttons and their locations
        restart = new MyButton("Restart", buttonW1, buttonH);
        continueButton = new MyButton("Continue", buttonW1, buttonH);
        exitMenu = new MyButton("Menu", buttonW2, buttonH);
        exitDesktop = new MyButton("Desktop", buttonW2, buttonH);

        // Define the restart location first because it's the center of the pause screen
        // so it sort of acts like an anchor.
        restart.setLocation((int) (width / 2 - buttonW1 / 2), (int) (height / 2 - buttonH / 2));
        continueButton.setLocation((int) (width / 2 - buttonW1 / 2), (int) (restart.getY() - buttonH * 1.1));
        exitMenu.setLocation((int) (restart.getX()), (int) (restart.getY() + buttonH * 1.1));
        exitDesktop.setLocation((int) (restart.getX() + buttonW1 - buttonW2), (int) (restart.getY() + buttonH * 1.1));

        // Set the buttons' action commands
        continueButton.setActionCommand(CONTINUE);
        restart.setActionCommand(RESTART);
        exitMenu.setActionCommand(MENU);
        exitDesktop.setActionCommand(DESKTOP);

        // Adding the buttons
        this.add(continueButton);
        this.add(restart);
        this.add(exitMenu);
        this.add(exitDesktop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActionListener(ActionListener l) {
        continueButton.addActionListener(l);
        restart.addActionListener(l);
        exitMenu.addActionListener(l);
        exitDesktop.addActionListener(l);
    }
}
