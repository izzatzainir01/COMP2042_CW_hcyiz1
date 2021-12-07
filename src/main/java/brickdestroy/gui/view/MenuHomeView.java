package brickdestroy.gui.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import brickdestroy.gui.MainFrame;
import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

/**
 * A child class of {@link AllView} that represents the game's Home
 * Menu. It is the first View that users will see upon launching the game.
 * Although it extends from {@code AllView}, it doesn't actually use any
 * of its buttons or even the background image. It only uses the parent's
 * {@code setActionListener} method.
 * <p>
 * It is responsible for defining all the components that make up this View and
 * adding them to itself.
 */
public class MenuHomeView extends AllView {

    public static final String START = "MENU_START";
    public static final String INFO = "MENU_INFO";
    public static final String SCORES = "MENU_SCORES";
    public static final String EXIT = "MENU_EXIT";

    private int width = MainFrame.WIDTH;
    private int height = MainFrame.HEIGHT;

    private MyImage menuBG;
    private MyImage title;

    private MyButton start;
    private MyButton info;
    private MyButton scores;
    private MyButton exit;

    private int buttonW;
    private int buttonH;

    /**
     * A child class of {@link AllView} that represents the game's Home
     * Menu. It is the first View that users will see upon launching the game.
     * Although it extends from {@code AllView}, it doesn't actually use any
     * of its buttons or even the background image. It only uses the parent's
     * {@code setActionListener} method.
     * <p>
     * It is responsible for defining all the components that make up this View and
     * adding them to itself.
     */
    public MenuHomeView() {
        super(null, null, null, null);
        button1.setVisible(false);
        button2.setVisible(false);

        // Defining background and title images
        menuBG = new MyImage("menu_bg.jpg");
        title = new MyImage("title_version.png");

        // Defining button size
        buttonW = (int) (width * 0.2);
        buttonH = (int) (height * 0.15);

        // Defining start, info and exit buttons
        start = new MyButton("Start", buttonW, buttonH);
        info = new MyButton("Info", buttonW, buttonH);
        scores = new MyButton("Scores", buttonW, buttonH);
        exit = new MyButton("Exit", buttonW, buttonH);

        // Setting the buttons' locations
        start.setLocation((int) (width * 0.3 - buttonW / 2), (int) (height * 0.59 - buttonH / 2));
        info.setLocation((int) (width * 0.7 - buttonW / 2), (int) (height * 0.59 - buttonH / 2));
        scores.setLocation((int) (width * 0.3 - buttonW / 2), (int) (height * 0.81 - buttonH / 2));
        exit.setLocation((int) (width * 0.7 - buttonW / 2), (int) (height * 0.81 - buttonH / 2));

        // Set the buttons' action commands
        start.setActionCommand(START);
        info.setActionCommand(INFO);
        scores.setActionCommand(SCORES);
        exit.setActionCommand(EXIT);

        // Adding the buttons
        this.add(start);
        this.add(info);
        this.add(scores);
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
     * {@inheritDoc}
     */
    @Override
    public void setActionListener(ActionListener l) {
        start.addActionListener(l);
        info.addActionListener(l);
        scores.addActionListener(l);
        exit.addActionListener(l);
    }

}
