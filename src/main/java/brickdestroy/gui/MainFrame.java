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
package brickdestroy.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import brickdestroy.gui.controller.GameController;
import brickdestroy.gui.controller.InfoController;
import brickdestroy.gui.controller.MenuController;
import brickdestroy.utility.MyImage;
import brickdestroy.utility.MyTimer;

/**
 * A class handles the game's window frame using {@code JFrame}. In a way, it
 * can be considered as the Main or Super Controller as it switches between the
 * different Controllers of the game. However, since it doesn't take any input
 * from the user directly, I do not consider it a Controller.
 * <p>
 * It is responsible for defining the {@code JFrame's} properties and switching
 * between the different Controllers.
 */
public class MainFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 540;

    private JFrame frame;
    private MyImage frameIcon;

    private JPanel controller;
    private MenuController menu;
    private InfoController info;
    private GameController game;

    /**
     * A class handles the game's window frame using {@code JFrame}. In a way, it
     * can be considered as the Main or Super Controller as it switches between the
     * different Controllers of the game. However, since it doesn't take any input
     * from the user directly, I do not consider it a Controller.
     * <p>
     * It is responsible for defining the {@code JFrame's} properties and switching
     * between the different Controllers.
     */
    public MainFrame() {

        // Define frame's icon
        frameIcon = new MyImage("monke.png");

        // Define frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Brick Destroy");
        frame.setIconImage(frameIcon.getImage());

        // Initialise MyTimer
        new MyTimer();

        // Add Menu Controller upon first launch
        addMenuController();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Adds the {@code MenuController} to the frame. This method automatically
     * removes the previous controller after adding the new one.
     */
    public void addMenuController() {
        menu = new MenuController(this);
        addController(menu.getPanel());
    }

    /**
     * Adds the {@code InfoController} to the frame. This method automatically
     * removes theprevious controller after adding the new one.
     */
    public void addInfoController() {
        info = new InfoController(this);
        addController(info.getPanel());
    }

    /**
     * Adds the {@code GameController} to the frame and initialise the game. This
     * method automatically removes the previous controller after adding the new
     * one.
     */
    public void addGameController() {
        game = new GameController(this);
        frame.addWindowFocusListener(game);
        addController(game.getPanel());
    }

    /**
     * Closes the game.
     */
    public void exit() {
        System.out.println("Exiting game...");
        System.out.println("Goodbye, " + System.getProperty("user.name") + "!");
        System.exit(0);
    }

    /**
     * Adds a controller to the frame. This method replaces the previous controller
     * with a new one, then it removes the old one.
     * 
     * @param controller The new controller
     */
    private void addController(JPanel controller) {

        // Set a temporary reference to the original controller
        JPanel temp = null;
        if (this.controller != null) {
            temp = this.controller;
        }

        // Set the controller to the new one
        this.controller = controller;
        frame.add(controller);
        frame.revalidate();
        frame.repaint();

        // Remove the old controller
        if (temp != null) {
            frame.remove(temp);
            frame.revalidate();
            frame.repaint();
            temp = null;
        }
    }

}
