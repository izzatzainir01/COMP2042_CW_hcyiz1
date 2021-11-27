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

public class DebugConsole extends JDialog {

    private Game game;
    private GameController controller;

    private JPanel panel;

    private JButton skipLevel;
    private JButton resetBalls;
    private int buttonW;
    private int buttonH;

    private JSlider speedSlider;

    /**
     * The {@code DebugConsole} class is a GUI that allows the user to modify some
     * parts of the gameplay.
     * <p>
     * It is responsible for defining its components and taking user input to modify
     * the gameplay.
     * 
     * @param game       The {@code Game}
     * @param controller The {@code GameController}
     */
    public DebugConsole(Game game, GameController controller) {

        // Define the Game and its Controller
        this.game = game;
        this.controller = controller;

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
        buttonW = (int) (MainFrame.getWidth() * 0.26);
        buttonH = (int) (MainFrame.getHeight() * 0.096);

        // Create the Skip Level button
        skipLevel = makeButton("Skip Level", e -> {
            game.nextLevel();
            game.ballReset();
            controller.repaintGameView();
        });

        // Create the Reset Balls button
        resetBalls = makeButton("Reset Balls", e -> {
            game.resetBallCount();
            controller.repaintGameView();
        });

        // Create the slider
        speedSlider = makeSlider(0, 10, (int) game.getBallSpeed(), e -> {
            game.setBallSpeed(speedSlider.getValue());
            controller.repaintGameView();
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
     * @param min The minimum value of the slider
     * @param max The maximum value of the slider
     * @param def The initial value of the slider
     * @param e   An {@code ActionListener}
     * @return A {@code JSlider} object
     */
    private JSlider makeSlider(int min, int max, int def, ChangeListener e) {
        JSlider slider = new JSlider(min, max, def);

        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.addChangeListener(e);

        return slider;
    }
}
