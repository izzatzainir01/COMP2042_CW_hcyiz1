package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import brickdestroy.gui.MainFrame;
import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public class ScorePromptView extends JLabel {

    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private MyImage background;
    private String prompt = "Enter username:";

    private JTextField field;
    private int fieldW;
    private int fieldH;

    private MyButton play;
    private MyButton exit;

    private String username = "";

    public ScorePromptView() {

        // Define background image
        background = new MyImage("cement_wall.jpg");

        // Define prompt
        prompt = "Enter username:";

        // Define the field's width and height
        fieldW = (int) (width * 0.45);
        fieldH = (int) (height * 0.1);

        // Define text field
        field = new JTextField();
        field.setSize(fieldW, fieldH);
        field.setLocation((int) (width / 2 - fieldW / 2), (int) (height * 0.45 - fieldH / 2));
        field.setFont(new Font("Impact", Font.PLAIN, 25));
        field.setForeground(Color.BLACK);

        // Define button size
        int buttonW = (int) (width * 0.2);
        int buttonH = (int) (height * 0.15);

        // Define buttons
        play = new MyButton("Play", buttonW, buttonH);
        exit = new MyButton("Exit", buttonW, buttonH);

        // Set buttons' locations
        play.setLocation((int) (width * 0.35 - buttonW / 2), (int) (height * 0.7));
        exit.setLocation((int) (width * 0.65 - buttonW / 2), (int) (height * 0.7));

        // Initialise the view's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Add the components
        this.add(field);
        this.add(play);
        this.add(exit);
    }

    /**
     * Paint the background image and the prompt string.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw background image
        g2d.drawImage(background.getImage(), 0, 0, width, height, null);

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
    public void setPlayAction(ActionListener l) {
        play.addActionListener(l);

        // Also set the username
        play.addActionListener(e -> {
            username = field.getText();
        });
    }

    /**
     * Set an {@code Action} for the Exit button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setExitAction(ActionListener l) {
        exit.addActionListener(l);
    }

    /**
     * Get the username that was input in the text field.
     * 
     * @return A {@code String} of the username
     */
    public String getUsername() {
        return username;
    }
}
