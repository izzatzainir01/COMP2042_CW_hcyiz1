/*
 *  Brick Destroy - A simple arcade video game
 *  Copyright (C) 2021  Izzat Zainir
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;

/**
 * A child class of {@link AllView} that represents a View that prompts
 * the user for their username before the {@code Game} starts. If the user did
 * not enter anything for their username, the {@code ScoreModel} will assign the
 * current user with a "Guest_x" username, where x is the guest ID.
 * <p>
 * It is responsible for defining the components that make up this View and
 * adding them to itself.
 * 
 * @see brickdestroy.gui.model.ScoreModel ScoreModel
 */
public class ScorePromptView extends AllView {

    public static final String PLAY = "PROMPT_PLAY";
    public static final String EXIT = "PROMPT_EXIT";

    private final String prompt = "Enter username:";

    private JTextField field;
    private int fieldW;
    private int fieldH;

    /**
     * A child class of {@link AllView} that represents a View that prompts
     * the user for their username before the {@code Game} starts. If the user did
     * not enter anything for their username, the {@code ScoreModel} will assign the
     * current user with a "Guest_x" username, where x is the guest ID.
     * <p>
     * It is responsible for defining the components that make up this View and
     * adding them to itself.
     * 
     * @see brickdestroy.gui.model.ScoreModel ScoreModel
     */
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
     * Draws the prompt string.
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
     * Gets the username that was entered in the text field.
     * 
     * @return A {@code String} of the username
     */
    public String getUsername() {
        return field.getText();
    }
}
