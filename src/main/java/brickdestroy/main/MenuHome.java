package brickdestroy.main;

import java.awt.*;

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

    public MenuHome() {


        // Defining background and title images
        menuBG = new MyImage("menu_bg.jpg");
        title = new MyImage("title_version.png");

        // Defining button size
        buttonW = (int) (width * 0.2);
        buttonH = (int) (height * 0.15);

        // Defining start, info and exit buttons and their locations
        start = new MyButton("Start", buttonW, buttonH);
        start.setLocation((int) (width * 0.2 - buttonW/2), (int) (height * 0.7 - buttonH/2));

        info = new MyButton("Info", buttonW, buttonH);
        info.setLocation((int) (width * 0.5 - buttonW/2), (int) (height * 0.7 - buttonH/2));

        exit = new MyButton("Exit", buttonW, buttonH);
        exit.setLocation((int) (width * 0.8 - buttonW/2), (int) (height * 0.7 - buttonH/2));

        // Initialise the Label's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Adding the buttons
        this.add(start);
        this.add(info);
        this.add(exit);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw the background image
        g2d.drawImage(menuBG.getImage(), 0, 0, width, height, null);

        // Rescale, set location then draw the title image
        title.rescale(width * 0.85);
        title.setLocation(
                new Point((int) (width / 2 - title.getWidth() / 2), (int) (height * 0.4 - title.getHeight())));
        g2d.drawImage(title.getImage(), (int) title.getX(), (int) title.getY(), title.getWidth(), title.getHeight(),
                null);
    }

    public MyButton getStartButton() {
        return start;
    }

    public MyButton getInfoButton() {
        return info;
    }

    public MyButton getExitButton() {
        return exit;
    }

}
