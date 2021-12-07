package brickdestroy.gui.view;

import brickdestroy.gui.model.InfoModel;

public class InfoControlsView extends AbstractInfoView {

    public static final String DESCRIPTION = "INFO_DESCRIPTION";

    public InfoControlsView(InfoModel info) {
        super(info, BACK, DESCRIPTION, "Controls", "Description");

        button2.setSize((int) (width * 0.27), (int) (height * 0.15));
        button2.setLocation((int) (width * 0.9 - button2.getWidth()), (int) (height * 0.75));
    }

}
