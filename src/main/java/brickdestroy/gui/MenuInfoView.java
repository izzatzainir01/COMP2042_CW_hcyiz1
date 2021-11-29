package brickdestroy.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import brickdestroy.utility.MyButton;
import brickdestroy.utility.MyImage;

public abstract class MenuInfoView extends JLabel {

    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private MyImage background;

    private JTextArea description;
    private String title = "";

    private MyButton back;
    private MyButton switcher;

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
    public MenuInfoView(String title, String filePath, String switcherText) {

        // Define background image
        background = new MyImage("cement_wall.jpg");

        // Define description text area and set its properties
        description = new JTextArea();
        description.setSize((int) (width * 0.8), (int) (height * 0.5));
        description.setLocation(width / 2 - description.getWidth() / 2, (int) (height * 0.2));
        description.setOpaque(false);
        description.setEditable(false);
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setFont(setFontSize(25));
        description.setText(getContent(filePath));
        description.setForeground(Color.BLACK);

        // Define title
        this.title = title;

        // Define back and switcher buttons
        back = new MyButton("Back", (int) (width * 0.2), (int) (height * 0.15));
        switcher = new MyButton(switcherText, (int) (width * 0.25), (int) (height * 0.15));

        // Set buttons locations
        back.setLocation((int) (width * 0.1), (int) (height * 0.75));
        switcher.setLocation((int) (width * 0.9 - switcher.getWidth()), (int) (height * 0.75));

        // Initialise the class's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Add the description label and controls button
        this.add(description);
        this.add(back);
        this.add(switcher);
    }

    /**
     * Paint the background image and the title
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw background image
        g2d.drawImage(background.getImage(), 0, 0, width, height, null);

        
        // Draw title text
        g2d.setColor(Color.BLACK);
        g2d.setFont(setFontSize(40));
        g2d.drawString(title, (int) (width * 0.1), (int) (height * 0.15));
    }

    /**
     * Set an {@code ActionListener} for the Back button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setBackAction(ActionListener l) {
        back.addActionListener(l);
    }

    /**
     * Set an {@code ActionListener} for the Controls button.
     * 
     * @param l An {@code ActionListener}
     */
    public void setSwitcherAction(ActionListener l) {
        switcher.addActionListener(l);
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

    /**
     * This method retrieves text data from the description.txt file.
     * 
     * @return A {@code String} of the data in description.txt
     */
    private String getContent(String filePath) {
        FileReader file;
        String content = "";

        try {
            file = new FileReader(filePath);
            int data = file.read();
            while (data != -1) {
                content += (char) data;
                data = file.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
