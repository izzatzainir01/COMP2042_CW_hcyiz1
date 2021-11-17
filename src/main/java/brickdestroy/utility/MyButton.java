package brickdestroy.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import brickdestroy.main.GameFrame;

import javax.swing.JButton;

/**
 * My own button that predefines some properties to make my life easier.
 */
public class MyButton extends JButton implements MouseListener {

    private MyImage buttonBG;

    private Border border;
    private int borderW;
    private Color borderC;

    private Font font;
    private int fontSize;

    public MyButton(String text, int width, int height) {

        // Define button background and rescale it
        this.buttonBG = new MyImage("bd_button.png");
        buttonBG.resize(width, height);

        // Define button border width relative to the frame's width and initial colour
        this.borderW = (int) (GameFrame.WIDTH * (0.007));
        this.borderC = Color.darkGray;
        this.border = BorderFactory.createLineBorder(borderC, borderW);

        // Define font size relative to the frame's width
        this.fontSize = (int) (GameFrame.WIDTH * 0.049);
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

    public void setFontSize(int size) {
        this.setFont(new Font("Impact", Font.PLAIN, size));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Change border colour to white upon mouse entry
        border = BorderFactory.createLineBorder(Color.white, borderW);
        this.setBorder(border);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Change border colour to darkGray upon mouse exit
        border = BorderFactory.createLineBorder(borderC, borderW);
        this.setBorder(border);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
