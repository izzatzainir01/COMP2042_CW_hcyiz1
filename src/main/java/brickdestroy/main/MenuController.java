package brickdestroy.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * The MenuController is the controller for the Menu, which includes the Home
 * and the Info view. It is responsible for getting user input 
 */
public class MenuController extends JPanel implements ActionListener {

    GameFrame frame;
    private int width;
    private int height;

    private MenuHome home;

    public MenuController(GameFrame frame) {
        // Define frame
        this.frame = frame;

        // Define size
        this.width = GameFrame.WIDTH;
        this.height = GameFrame.HEIGHT;

        // Initialise the panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.requestFocusInWindow(true);

        addHome();
    }

    public void addHome() {
        home = new MenuHome(frame, new Dimension(width, height));
        this.add(home);
    }

    public void removeHome() {
        this.remove(home);
        revalidate();
        repaint();
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        home.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
