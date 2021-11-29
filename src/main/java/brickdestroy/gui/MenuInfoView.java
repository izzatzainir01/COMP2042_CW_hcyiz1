package brickdestroy.gui;

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

    public MenuInfoView(String title, String content, String switcherText) {

        // Define background image
        background = new MyImage("cement_wall.jpg");

        // Define description label and setting its properties
        description = new JTextArea();
        description.setSize((int) (width * 0.8), (int) (height * 0.5));
        description.setLocation(width / 2 - description.getWidth() / 2, (int) (height * 0.2));
        description.setOpaque(false);
        description.setEditable(false);
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setFont(setFontSize(25));
        description.setText(content);

        // Define title
        this.title = title;

        // Define back button
        back = new MyButton("Back", (int) (width * 0.2), (int) (height * 0.15));
        back.setLocation((int) (width * 0.1), (int) (height * 0.75));

        // Define controls button
        switcher = new MyButton(switcherText, (int) (width * 0.2), (int) (height * 0.15));
        switcher.setLocation((int) (width * 0.9 - switcher.getWidth()), (int) (height * 0.75));

        // Initialise the class's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));

        // Add the description label and controls button
        this.add(description);
        this.add(back);
    }

    /**
     * Pain the background image and the title
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw background image
        g2d.drawImage(background.getImage(), 0, 0, width, height, null);

        // Draw Description title text
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
    public static String getContent(String fileName) {
        FileReader file;
        String content = "";
        String path = String.format("target/classes/%s", fileName);

        try {
            file = new FileReader(path);
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
