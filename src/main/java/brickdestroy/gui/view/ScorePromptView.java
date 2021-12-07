package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;

public class ScorePromptView extends AbstractAllView {

    public static final String PLAY = "PROMPT_PLAY";
    public static final String EXIT = "PROMPT_EXIT";

    private final String prompt = "Enter username:";

    private JTextField field;
    private int fieldW;
    private int fieldH;

    public ScorePromptView() {
        // Call the super constructor
        super("Play", "Exit", PLAY, EXIT);

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
     * Get the username that was input in the text field.
     * 
     * @return A {@code String} of the username
     */
    public String getUsername() {
        return field.getText();
    }
}
