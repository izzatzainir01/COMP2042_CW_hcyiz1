package brickdestroy.gui.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.MenuInfoView;

public class InfoController extends AbstractController {

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
        // Call the super constructor
        super(frame);

        // Add the description view
        addView(description = new MenuInfoView("Description", getContent("description.txt"), "Controls"));
        initDescriptionButtonsListeners();
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
