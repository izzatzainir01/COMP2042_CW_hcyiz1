package brickdestroy.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextArea;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.InfoModel;

public abstract class AbstractInfoView extends MyAbstractView {

    public static final String BACK = "INFO_BACK";

    protected int width = MainFrame.WIDTH;
    protected int height = MainFrame.HEIGHT;

    private String title = "";
    private JTextArea content;

    /**
     * The {@code MenuInfoView} class is an abstract class that provides a template
     * for the Info view.
     * <p>
     * It is responsible for defining its components and taking in arguments in the
     * constructor from its child classes to create the content. The Info View
     * contains a 'Switcher' button that you can define to make it switch between
     * the different views. That being said, it is technically just a normal button
     * that you can set any action to from the controller.
     * 
     * @param title        The title of the view
     * @param contentFile  The file name that contains the data for the content
     * @param switcherText The text inside the switcher button
     */
    protected AbstractInfoView(InfoModel info, String button1Command, String button2Command) {
        super("Back", info.getSwitcherText(), BACK, button2Command);

        // Define description text area and set its properties
        content = new JTextArea();
        content.setSize((int) (width * 0.8), (int) (height * 0.5));
        content.setLocation(width / 2 - content.getWidth() / 2, (int) (height * 0.22));
        content.setOpaque(false);
        content.setEditable(false);
        content.setWrapStyleWord(true);
        content.setLineWrap(true);
        content.setFont(setFontSize((int) (width * 0.033)));
        content.setText(info.getContent());
        content.setForeground(Color.BLACK);

        // Define title
        this.title = info.getTitle();

        // Set buttons locations
        button1.setLocation((int) (width * 0.1), (int) (height * 0.75));
        button2.setLocation((int) (width * 0.9 - button2.getWidth()), (int) (height * 0.75));

        // Add the description label
        this.add(content);
    }

    /**
     * Paint the background image and the title
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
