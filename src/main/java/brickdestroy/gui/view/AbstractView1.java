package brickdestroy.gui.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import brickdestroy.gui.MainFrame;
import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public abstract class AbstractView1 extends JLabel {

    protected final int width = MainFrame.WIDTH;
    protected final int height = MainFrame.HEIGHT;

    private MyImage background;

    protected MyButton button1;
    protected MyButton button2;

    /**
     * The {@code AbstractView1} is an abstract class that represents some of the
     * Views in the game, many of which follow a similar template. This class
     * predefines that template in order to avoid rewriting the same code when
     * creating new Views.
     * <p>
     * It is responsible for predefining the background image and the first and
     * second button of the View.
     * 
     * @param button1Text The text of the first button
     * @param button2Text The text of the second button
     */
    protected AbstractView1(String button1Text, String button2Text) {
        // Define background image
        background = new MyImage("cement_wall.jpg");

        // Define buttons' size
        int buttonW = (int) (width * 0.2);
        int buttonH = (int) (height * 0.15);

        // Define the buttons
        button1 = new MyButton(button1Text, buttonW, buttonH);
        button2 = new MyButton(button2Text, buttonW, buttonH);

        // Set the buttons' locations
        button1.setLocation((int) (width * 0.35 - buttonW / 2), (int) (height * 0.7));
        button2.setLocation((int) (width * 0.65 - buttonW / 2), (int) (height * 0.7));

        // Initialise the view's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Add the buttons to the view
        this.add(button1);
        this.add(button2);
    }

    /**
     * Draw the background image of the view
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw the background
        g2d.drawImage(background.getImage(), 0, 0, width, height, null);
    }

    /**
     * Set an action for the first button of the view, which is the button on the
     * left.
     * 
     * @param l An {@code ActionListener}
     */
    public void setButton1Action(ActionListener l) {
        button1.addActionListener(l);
    }

    /**
     * Set an action for the second button of the view, which is the button on the
     * right.
     * 
     * @param l An {@code ActionListener}
     */
    public void setButton2Action(ActionListener l) {
        button2.addActionListener(l);
    }

}
