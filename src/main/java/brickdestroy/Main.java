package brickdestroy;

import javax.swing.SwingUtilities;

import brickdestroy.gui.MainFrame;

/**
 * The Main class of the game, which contains the {@code main} method. It simply
 * instantiates the {@link MainFrame}.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }

}
