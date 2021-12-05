package brickdestroy.gui.view;

import brickdestroy.gui.model.InfoModel;

public class InfoControlsView extends AbstractInfoView {

    public static final String DESCRIPTION = "INFO_DESCRIPTION";

    public InfoControlsView(InfoModel info) {
        super(info, BACK, DESCRIPTION);
    }
    
}
