package brickdestroy.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
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
        buttonBG.rescale(width);

        // Define button border width and initial colour
        this.borderW = (int) ((double) width * 4 / 153);
        this.borderC = Color.darkGray;
        this.border = BorderFactory.createLineBorder(borderC, borderW);

        // Define font
        this.fontSize = (int) (width * 30 / 153); // 35/135 is the ratio of font size to button width that i settled with
        this.font = new Font("Impact", Font.PLAIN, fontSize);
        
        // Initialise the button
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(border);
        this.setIcon(buttonBG.getImageIcon());
        
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setForeground(Color.black);

        this.addMouseListener(this);
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
