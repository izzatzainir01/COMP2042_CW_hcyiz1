package brickdestroy.gui.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.MenuInfoView;

public class InfoController extends JPanel {

    private MainFrame frame;
    private int width = MainFrame.getWidth();
    private int height = MainFrame.getHeight();

    private MenuInfoView description;
    private MenuInfoView controls;

    /**
     * The {@code InfoController} class is the controller for the menu's information
     * views, which include variations of the {@code MenuInfoView} class.
     * <p>
     * It is responsible for defining the different variations of the info views and
     * getting user input to switch between the different Info views.
     * 
     * @param frame The {@code MainFrame}
     */
    public InfoController(MainFrame frame) {

        // Define frame
        this.frame = frame;

        // Initialise the panel's properties
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        // Add the description view
        addView(description = new MenuInfoView("Description", getContent("description.txt"), "Controls"));
        initDescriptionButtonsListeners();
    }

    /**
     * Add a {@code Component} to this controller and automatically call
     * {@code revalidate()} and {@code repaint()}.
     * 
     * @param comp The component to be added
     */
    private void addView(Component comp) {
        this.add(comp);
        revalidate();
        repaint();
    }

    /**
     * Remove a {@code Component} from this controller and automatically call
     * {@code revalidate()} and {@code repaint()}.
     * 
     * @param comp The component to be removed
     */
    private void removeView(Component comp) {
        this.remove(comp);
        revalidate();
        repaint();
    }

    /**
     * Add {@code ActionListeners} on the InfoDescriptionView's buttons.
     */
    private void initDescriptionButtonsListeners() {

        description.setBackAction(e -> frame.addController(new MenuController(frame)));

        description.setSwitcherAction(e -> {
            addView(controls = new MenuInfoView("Controls", getContent("controls.txt"), "Description"));
            initControlsButtonsListener();
            removeView(description);
        });
    }

    /**
     * Add {@code ActionListeners} on the InfoControlsView's buttons.
     */
    private void initControlsButtonsListener() {

        controls.setBackAction(e -> frame.addController(new MenuController(frame)));

        controls.setSwitcherAction(e -> {
            addView(description = new MenuInfoView("Description", getContent("description.txt"), "Controls"));
            initDescriptionButtonsListeners();
            removeView(controls);
        });
    }

    /**
     * Retrieve data from a text file.
     * 
     * @param fileName The file that the data will be retrieved from
     * @return A {@code String} of the data within the file
     */
    private String getContent(String fileName) {
        InputStream file;
        String content = "";

        try {
            file = getClass().getClassLoader().getResourceAsStream(fileName);
            int data;
            while ((data = file.read()) != -1) {
                content += (char) data;
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
