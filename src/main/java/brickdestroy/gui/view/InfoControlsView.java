package brickdestroy.gui.view;

import brickdestroy.gui.model.InfoModel;

/**
 * A child class of {@link AbstractInfoView} that represents the Description
 * part of the Info section. It is responsible for defining the title, the
 * Second button action command and text, and the Info model.
 */
public class InfoControlsView extends AbstractInfoView {

    public static final String DESCRIPTION = "INFO_DESCRIPTION";

    /**
     * A child class of {@link AbstractInfoView} that represents the Description
     * part of the Info section. It is responsible for defining the title, the
     * Second button action command and text, and the Info model.
     */
    public InfoControlsView(InfoModel info) {
        super(info, DESCRIPTION, "Controls", "Description");

        button2.setSize((int) (width * 0.27), (int) (height * 0.15));
        button2.setLocation((int) (width * 0.9 - button2.getWidth()), (int) (height * 0.75));
    }

}
