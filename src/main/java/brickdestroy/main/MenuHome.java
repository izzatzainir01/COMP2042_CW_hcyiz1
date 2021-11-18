package brickdestroy.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public class MenuHome extends JLabel {

    private int width = GameFrame.WIDTH;
    private int height = GameFrame.HEIGHT;

    private MyImage menuBG;
    private MyImage title;

    private MyButton start;
    private MyButton info;
    private MyButton exit;

    private int buttonW;
    private int buttonH;

    /**
     * The {@code MenuHome} class the view for the game's home menu. It extends
     * {@code JLabel} as I needed to access the {@code paintComponent()} method to
     * draw the images.
     * <p>
     * It is responsible for defining the components that make up the home menu and
     * adding them.
     */
    public MenuHome() {

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
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
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
     * Get the Start button. This is for the MenuController to detect an
     * {@code ActionEvent} from outside this class.
     * 
     * @return A {@code MyButton} of the Start button.
     */
    public MyButton getStartButton() {
        return start;
    }

    /**
     * Get the Info button. This is for the MenuController to detect an
     * {@code ActionEvent} from outside this class.
     * 
     * @return A {@code MyButton} of the Info button.
     */
    public MyButton getInfoButton() {
        return info;
    }

    /**
     * Get the Exit button. This is for the MenuController to detect an
     * {@code ActionEvent} from outside this class.
     * 
     * @return A {@code MyButton} of the Exit button.
     */
    public MyButton getExitButton() {
        return exit;
    }

}
