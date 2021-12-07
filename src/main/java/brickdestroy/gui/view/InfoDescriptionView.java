package brickdestroy.gui.view;

import brickdestroy.gui.model.InfoModel;

/**
 * A child class of {@link InfoView} that represents the Controls
 * part of the Info section. It is responsible for defining the title, the
 * Second button action command and text, and the Info model.
 */
public class InfoDescriptionView extends InfoView {

    public static final String CONTROLS = "INFO_CONTROLS";

    /**
     * A child class of {@link InfoView} that represents the Controls
     * part of the Info section. It is responsible for defining the title, the
     * Second button action command and text, and the Info model.
     */
    public InfoDescriptionView(InfoModel info) {
        super(info, CONTROLS, "Description", "Controls");
    }

}
