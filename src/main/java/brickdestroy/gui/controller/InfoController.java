package brickdestroy.gui.controller;

import java.awt.event.ActionEvent;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.InfoModel;
import brickdestroy.gui.view.AbstractInfoView;
import brickdestroy.gui.view.InfoControlsView;
import brickdestroy.gui.view.InfoDescriptionView;

public class InfoController extends AbstractController {

    private AbstractInfoView description;
    private InfoModel descriptionInfo;

    private AbstractInfoView controls;
    private InfoModel controlsInfo;

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

        // Define the description model and view
        descriptionInfo = new InfoModel("description.txt");
        descriptionInfo.setTitle("Description");
        descriptionInfo.setSwitcherText("Controls");
        description = new InfoDescriptionView(descriptionInfo);

        // Define the controls model and view
        controlsInfo = new InfoModel("controls.txt");
        controlsInfo.setTitle("Controls");
        controlsInfo.setSwitcherText("Description");
        controls = new InfoControlsView(controlsInfo);

        // Add the description view
        addView(description);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            // Back button
            case AbstractInfoView.BACK:
                frame.addMenuController();
                break;

            // InfoDescriptionView secondary button
            case InfoDescriptionView.CONTROLS:
                addView(controls);
                removeView(description);
                break;

            // InfoConstrolsView secondary button
            case InfoControlsView.DESCRIPTION:
                addView(description);
                removeView(controls);
                break;

            default:
                break;
        }

    }

}
