package brickdestroy.gui.view;

public class InfoDescriptionView extends MenuInfoView {

    private static final String title = "Description";
    private static final String filePath = "target/classes/description.txt";
    private static final String switcherText = "Controls";

    /**
     * The {@code InfoDescriptionView} is a child class of the {@code MenuInfoView}.
     * It is only responsible for defining the title, content file path and switcher
     * button text.
     */
    public InfoDescriptionView() {
        super(title, filePath, switcherText);
    }

}
