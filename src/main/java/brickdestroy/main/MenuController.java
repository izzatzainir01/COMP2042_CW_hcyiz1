package brickdestroy.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import brickdestroy.utility.MyButton;

/**
 * The MenuController is the controller for the Menu, which includes the Home
 * and Info view. It is responsible for getting user input to switch between the
 * two views as well as calling the GameFrame to start the game.
 */
public class MenuController extends JPanel {

    GameFrame frame;
    private int width;
    private int height;

    private MenuHome home;

    public MenuController(GameFrame frame) {
        // Define frame
        this.frame = frame;

        // Define width and height
        this.width = GameFrame.WIDTH;
        this.height = GameFrame.HEIGHT;

        // Initialise the panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.requestFocusInWindow(true);

        // Add the home view upon first launch
        addHome();
    }

    public void addHome() {
        home = new MenuHome();
        initHomeButtonsListeners();
        this.add(home);
    }

    public void removeHome() {
        this.remove(home);
        revalidate();
        repaint();
    }

    // Initialise the Home View buttons' actions
    private void initHomeButtonsListeners() {
        MyButton startButton = home.getStartButton();
        MyButton info = home.getInfoButton();
        MyButton exit = home.getExitButton();

        // Start button calls the GameFrame to add the Game Controller and remove the
        // Menu Controller.
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == startButton) {
                    frame.addGameController();
                    frame.removeMenuController();
                    System.out.println("Start");
                }
            }
        });

        // Info button calls the GameFrame to add the Info View and remove the
        // Home View.
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == info) {
                    System.out.println("Info");
                }
            }
        });

        // Exit button calls the GameFrame to close the game.
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exit) {
                    frame.exit();
                }
            }
        });
    }

}
