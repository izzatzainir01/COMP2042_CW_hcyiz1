package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ScorePromptView extends AbstractView1 {

    private final String prompt = "Enter username:";

    private JTextField field;
    private int fieldW;
    private int fieldH;

    public ScorePromptView() {
        // Call the super constructor
        super("Play", "Exit");

        // Define the field's width and height
        fieldW = (int) (width * 0.45);
        fieldH = (int) (height * 0.1);

        // Define text field
        field = new JTextField();
        field.setSize(fieldW, fieldH);
        field.setLocation((int) (width / 2 - fieldW / 2), (int) (height * 0.45 - fieldH / 2));
        field.setFont(new Font("Impact", Font.PLAIN, 25));
        field.setForeground(Color.BLACK);

        // Add the text field
        this.add(field);
    }

    /**
     * Draw the prompt string.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw prompt string
        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Impact", Font.PLAIN, 55));
        int fontW = g2d.getFontMetrics().stringWidth(prompt);
        int fontH = g2d.getFontMetrics().getHeight();
        g2d.drawString(prompt, width / 2 - fontW / 2, (int) (field.getY() - fontH / 2));
    }

    /**
     * Set an {@code Action} for the Play button.
     * 
     * @param l An {@code ActionListener}
     */
    @Override
    public void setButton1Action(ActionListener l) {
        button1.addActionListener(l);
    }

    /**
     * Set an {@code Action} for the Exit button.
     * 
     * @param l An {@code ActionListener}
     */
    @Override
    public void setButton2Action(ActionListener l) {
        button2.addActionListener(l);
    }

    /**
     * Get the username that was input in the text field.
     * 
     * @return A {@code String} of the username
     */
    public String getUsername() {
        return field.getText();
    }
}
