package brickdestroy.gui.view;

import brickdestroy.gui.model.InfoModel;

public class InfoDescriptionView extends AbstractInfoView {

    public static final String CONTROLS = "INFO_CONTROLS";

    public InfoDescriptionView(InfoModel info) {
        super(info, BACK, CONTROLS, "Description", "Controls");
    }

}
