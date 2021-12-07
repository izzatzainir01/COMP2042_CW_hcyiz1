package brickdestroy.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import brickdestroy.gui.MainFrame;

import javax.swing.JButton;

/**
 * A utility class that represents a custom button in the game using
 * {@code JButton}. It predefines many properties of the button in order to ease
 * the process of creating a button in the game.
 * 
 * @param text   The button text
 * @param width  The width
 * @param height The height
 * 
 * @see JButton
 */
public class MyButton extends JButton implements MouseListener {

    private MyImage buttonBG;

    private Border border;
    private int borderW;
    private Color borderC;

    private Font font;
    private int fontSize;

    /**
     * A utility class that represents a custom button in the game using
     * {@code JButton}. It predefines many properties of the button in order to ease
     * the process of creating a button in the game.
     * 
     * @param text   The button text
     * @param width  The width
     * @param height The height
     * 
     * @see JButton
     */
    public MyButton(String text, int width, int height) {

        // Define button background and resize it
        this.buttonBG = new MyImage("bd_button.png");
        buttonBG.resize(width, height);

        // Define button border width relative to the frame's width and initial colour
        this.borderW = (int) (MainFrame.WIDTH * (0.007));
        this.borderC = Color.darkGray;
        this.border = BorderFactory.createLineBorder(borderC, borderW);

        // Define font size relative to the frame's width
        this.fontSize = (int) (MainFrame.WIDTH * 0.049);
        this.font = new Font("Impact", Font.PLAIN, fontSize);

        // Initialise the button
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setBorder(border);
        this.setIcon(buttonBG.getImageIcon());

        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setForeground(Color.black);

        this.addMouseListener(this);
    }

    /**
     * Changes the button's border colour to white upon mouse entry.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        border = BorderFactory.createLineBorder(Color.white, borderW);
        this.setBorder(border);
    }

    /**
     * Changes button's border colour to dark gray upon mouse exit.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        border = BorderFactory.createLineBorder(borderC, borderW);
        this.setBorder(border);
    }

    /**
     * Unused
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Unused
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Unused
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
