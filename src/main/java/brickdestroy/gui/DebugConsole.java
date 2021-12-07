package brickdestroy.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import brickdestroy.elements.Game;

/**
 * A class the represents a dialog to modify some of the properties of the
 * {@code Game}. This is mainly for developer's use in order to easily debug the
 * {@code Game} in the middle of gameplay.
 * <p>
 * It is responsible for defining its components, adding them to itself and
 * taking user input to modify the {@code Game}.
 * 
 * @see Game
 */
public class DebugConsole extends JDialog {

    private Game game;

    private JPanel panel;

    private JButton skipLevel;
    private JButton resetBalls;
    private int buttonW;
    private int buttonH;

    private JSlider speedSlider;

    /**
     * A class the represents a dialog to modify some of the properties of the
     * {@code Game}. This is mainly for developer's use in order to easily debug the
     * {@code Game} in the middle of gameplay.
     * <p>
     * It is responsible for defining its components, adding them to itself and
     * taking user input to modify the {@code Game}.
     * 
     * @param game The {@code Game}
     * 
     * @see Game
     */
    public DebugConsole(Game game) {

        // Define the Game and its Controller
        this.game = game;

        // Initialise the debug console
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Debug Console");
        this.setModal(true);

        // Define, initialise and add the panel
        panel = new JPanel();
        initialisePanel();

        this.add(panel, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Initialise the panel that will be added to the dialog box.
     */
    private void initialisePanel() {

        // Define button size
        buttonW = (int) (MainFrame.WIDTH * 0.26);
        buttonH = (int) (MainFrame.HEIGHT * 0.096);

        // Create the Skip Level button
        skipLevel = makeButton("Skip Level", e -> {
            game.nextLevel();
        });

        // Create the Reset Balls button
        resetBalls = makeButton("Reset Balls", e -> {
            game.resetBallCount();
        });

        // Create the slider
        speedSlider = makeSlider((int) game.getBallSpeed(), e -> {
            game.setBallSpeed(speedSlider.getValue());
        });

        // Adding the components to the panel.
        this.setLayout(new BorderLayout());
        this.add(skipLevel, BorderLayout.WEST);
        this.add(resetBalls, BorderLayout.EAST);
        this.add(speedSlider, BorderLayout.SOUTH);
    }

    /**
     * Create a {@code JButton} with some predefined properties.
     * 
     * @param title The text of the button
     * @param e     An {@code ActionListener}
     * @return A {@code JButton} object
     */
    private JButton makeButton(String title, ActionListener e) {
        JButton button = new JButton(title);

        button.setPreferredSize(new Dimension(buttonW, buttonH));
        button.addActionListener(e);

        return button;
    }

    /**
     * Create a {@code JSlider} with some predefined properties.
     * 
     * @param ballSpeed The initial value of the slider
     * @param e         An {@code ActionListener}
     * @return A {@code JSlider} object
     */
    private JSlider makeSlider(int ballSpeed, ChangeListener e) {
        int min = (ballSpeed - 5 < 0) ? 0 : ballSpeed - 5;
        int max = ballSpeed + 5;

        JSlider slider = new JSlider(min, max, ballSpeed);

        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.addChangeListener(e);

        return slider;
    }
}
