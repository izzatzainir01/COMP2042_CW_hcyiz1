package brickdestroy.gui.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import brickdestroy.gui.MainFrame;
import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public class MenuHomeView extends JLabel {

    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private MyImage menuBG;
    private MyImage title;

    private MyButton start;
    private MyButton info;
    private MyButton exit;

    private int buttonW;
    private int buttonH;

    /**
     * The {@code MenuHome} class is the view for the game's home menu. It extends
     * {@code JLabel} as I need to access the {@code paintComponent()} method to
     * draw the images.
     * <p>
     * It is responsible for defining and adding the components that make up the
     * home menu.
     */
    public MenuHomeView() {

        // Defining background and title images
        menuBG = new MyImage("menu_bg.jpg");
        title = new MyImage("title_version.png");

        // Defining button size
        buttonW = (int) (width * 0.2);
        buttonH = (int) (height * 0.15);

        // Defining start, info and exit buttons and their locations
        start = new MyButton("Start", buttonW, buttonH);
        info = new MyButton("Info", buttonW, buttonH);
        exit = new MyButton("Exit", buttonW, buttonH);

        start.setLocation((int) (width * 0.2 - buttonW / 2), (int) (height * 0.7 - buttonH / 2));
        info.setLocation((int) (width * 0.5 - buttonW / 2), (int) (height * 0.7 - buttonH / 2));
        exit.setLocation((int) (width * 0.8 - buttonW / 2), (int) (height * 0.7 - buttonH / 2));

        // Initialise the Label's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Adding the buttons
        this.add(start);
        this.add(info);
        this.add(exit);
    }

    /**
     * Paint the Home View's background and title image
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw the background image
        g2d.drawImage(menuBG.getImage(), 0, 0, width, height, null);

        // Rescale, set location then draw the title image
        title.rescale(width * 0.85);
        title.setLocation((width / 2 - title.getWidth() / 2), (int) (height * 0.4 - title.getHeight()));
        g2d.drawImage(title.getImage(), (int) title.getX(), (int) title.getY(), title.getWidth(), title.getHeight(),
                null);
    }

    /**
     * Set an {@code Action} for the Start button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setStartAction(ActionListener l) {
        start.addActionListener(l);
    }

    /**
     * Set an {@code Action} for the Info button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setInfoAction(ActionListener l) {
        info.addActionListener(l);
    }

    /**
     * Set an {@code Action} for the Exit button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setExitAction(ActionListener l) {
        exit.addActionListener(l);
    }

}
