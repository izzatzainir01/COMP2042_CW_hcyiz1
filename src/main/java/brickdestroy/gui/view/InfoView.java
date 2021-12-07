package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.InfoModel;

/**
 * An abstract child class of {@link AllView} that represents the Views
 * of the Info section of the game. This class predefines a template that
 * extends its parent's template that all Info Views follow. Since all Info
 * Views have a Back button, this class also predefines the text and the action
 * command of the Second button.
 * <p>
 * The {@code InfoView} class's content consists of a title and a
 * {@code JTextArea} that displays information relating to that View.
 */
public abstract class InfoView extends AllView {

    public static final String BACK = "INFO_BACK";

    protected int width = MainFrame.WIDTH;
    protected int height = MainFrame.HEIGHT;

    private String title = "";
    private JTextArea content;
    private JScrollPane scrollPane;

    /**
     * An abstract child class of {@link AllView} that represents the Views
     * of the Info section of the game. This class predefines a template that
     * extends its parent's template that all Info Views follow. Since all Info
     * Views have a Back button, this class also predefines the text and the action
     * command of the Second button.
     * <p>
     * The {@code InfoView} class's content consists of a title and a
     * {@code JTextArea} that displays information relating to that View.
     * 
     * @param info           The Info model
     * @param button2Command The Second button command
     * @param title          The title of this View
     * @param button2Text    The Second button text
     */
    protected InfoView(InfoModel info, String button2Command, String title, String button2Text) {
        super("Back", button2Text, BACK, button2Command);

        // Define content size
        int contentW = (int) (width * 0.8);
        int contentH = (int) (height * 0.5);

        // Define description text area and set its properties
        content = new JTextArea();
        content.setSize(contentW, contentH);
        content.setLocation(width / 2 - contentW / 2, (int) (height * 0.22));
        content.setOpaque(false);
        content.setEditable(false);
        content.setWrapStyleWord(true);
        content.setLineWrap(true);
        content.setFont(setFontSize((int) (width * 0.033)));
        content.setText(info.getContent());
        content.setForeground(Color.BLACK);
        content.setCaretPosition(0);

        // Define the scroll pane
        scrollPane = new JScrollPane(content);
        // Set size and location
        scrollPane.setSize(contentW, contentH);
        scrollPane.setPreferredSize(new Dimension(contentW, contentH));
        scrollPane.setLocation(width / 2 - contentW / 2, (int) (height * 0.22));
        // Remove background and borders
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Define title
        this.title = title;

        // Set buttons locations
        button1.setLocation((int) (width * 0.1), (int) (height * 0.75));
        button2.setLocation((int) (width * 0.9 - button2.getWidth()), (int) (height * 0.75));

        // Add the description label
        this.add(scrollPane);
    }

    /**
     * Draw the background image and the title
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw title text
        g2d.setColor(Color.BLACK);
        g2d.setFont(setFontSize((int) (width * 0.052)));
        g2d.drawString(title, (int) (width * 0.1), (int) (height * 0.15));
    }

    /**
     * Set the font size without having to create a new font everytime.
     * 
     * @param size The new font size
     * @return A {@code Font} object
     */
    private Font setFontSize(int size) {
        return new Font("Impact", Font.PLAIN, size);
    }

}
