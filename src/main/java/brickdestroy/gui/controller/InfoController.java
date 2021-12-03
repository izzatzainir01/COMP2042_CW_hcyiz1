package brickdestroy.gui.controller;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.InfoModel;
import brickdestroy.gui.view.MenuInfoView;

public class InfoController extends AbstractController {

    private MenuInfoView description;
    private InfoModel descriptionInfo;

    private MenuInfoView controls;
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
        description = new MenuInfoView(descriptionInfo);

        // Define the controls model and view
        controlsInfo = new InfoModel("controls.txt");
        controlsInfo.setTitle("Controls");
        controlsInfo.setSwitcherText("Description");
        controls = new MenuInfoView(controlsInfo);

        // Add the description view
        addView(description);
        initDescriptionButtonsListeners();
    }

    /**
     * Add {@code ActionListeners} on the InfoDescriptionView's buttons.
     */
    private void initDescriptionButtonsListeners() {

        description.setBackAction(e -> frame.addMenuController());

        description.setSwitcherAction(e -> {
            addView(controls);
            initControlsButtonsListener();
            removeView(description);
        });
    }

    /**
     * Add {@code ActionListeners} on the InfoControlsView's buttons.
     */
    private void initControlsButtonsListener() {

        controls.setBackAction(e -> frame.addMenuController());

        controls.setSwitcherAction(e -> {
            addView(description);
            initDescriptionButtonsListeners();
            removeView(controls);
        });
    }

}
