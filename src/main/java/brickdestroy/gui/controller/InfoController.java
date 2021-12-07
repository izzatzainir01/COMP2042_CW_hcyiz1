package brickdestroy.gui.controller;

import java.awt.event.ActionEvent;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.InfoModel;
import brickdestroy.gui.view.InfoView;
import brickdestroy.gui.view.InfoControlsView;
import brickdestroy.gui.view.InfoDescriptionView;

/**
 * A child class of {@link Controller} that handles the Info section of
 * the game, accessible through the Main Menu. It is responsible for fetching
 * information from the resources, displaying it in its respective View and
 * switching between the Views that take up the Info section.
 * <p>
 * Currently, the Info section consists of the {@code InfoDescriptionView} and
 * the {@code InfoControlsView}.
 * 
 * @see InfoDescriptionView
 * @see InfoControlsView
 */
public class InfoController extends Controller {

    private InfoView description;
    private InfoModel descriptionInfo;

    private InfoView controls;
    private InfoModel controlsInfo;

    /**
     * A child class of {@link Controller} that handles the Info section of
     * the game, accessible through the Main Menu. It is responsible for fetching
     * information from the resources, displaying it in its respective View and
     * switching between the Views that take up the Info section.
     * <p>
     * Currently, the Info section consists of the {@code InfoDescriptionView} and
     * the {@code InfoControlsView}.
     * 
     * @param frame The {@code MainFrame}
     * 
     * @see InfoDescriptionView
     * @see InfoControlsView
     */
    public InfoController(MainFrame frame) {
        // Call the super constructor
        super(frame);

        // Define the description info model
        descriptionInfo = new InfoModel("description.txt");

        // Define the controls info model
        controlsInfo = new InfoModel("controls.txt");

        // Add the description view
        addView(description = new InfoDescriptionView(descriptionInfo));
    }

    /**
     * Defines the {@code InfoViews'} buttons' actions.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            // Back button
            case InfoView.BACK:
                frame.addMenuController();
                break;

            // InfoDescriptionView secondary button
            case InfoDescriptionView.CONTROLS:
                addView(controls = new InfoControlsView(controlsInfo));
                removeView(description);
                break;

            // InfoConstrolsView secondary button
            case InfoControlsView.DESCRIPTION:
                addView(description = new InfoDescriptionView(descriptionInfo));
                removeView(controls);
                break;

            default:
                break;
        }

    }

}
