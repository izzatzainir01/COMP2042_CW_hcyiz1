package brickdestroy.gui.view;

public class InfoControlsView extends MenuInfoView {

    private static final String title = "Controls";
    private static final String filePath = "target/classes/controls.txt";
    private static final String switcherText = "Description";

    /**
     * The {@code InfoControlsView} is a child class of the {@code MenuInfoView}.
     * It is only responsible for defining the title, content file path and switcher
     * button text.
     */
    public InfoControlsView() {
        super(title, filePath, switcherText);
    }
    
}
