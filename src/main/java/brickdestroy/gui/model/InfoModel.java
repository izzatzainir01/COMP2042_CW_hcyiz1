package brickdestroy.gui.model;

import java.io.IOException;
import java.io.InputStream;

/**
 * A Model class that handles information in the Info section of the game. It
 * is responsible for fetching information from the specified resource and
 * storing them along with other information that will be displayed by the
 * {@code InfoViews}.
 * 
 * @see brickdestroy.gui.view.InfoControlsView InfoControlsView
 * @see brickdestroy.gui.view.InfoDescriptionView InfoDescriptionView
 */
public class InfoModel {

    private InputStream file;

    private String title;
    private String content;
    private String switcherText;

    /**
     * A Model class that handles information in the Info section of the game. It
     * is responsible for fetching information from the specified resource and
     * storing them along with other information that will be displayed by the
     * {@code InfoViews}.
     * 
     * @param fileName The name of the resource file
     * 
     * @see brickdestroy.gui.view.InfoControlsView InfoControlsView
     * @see brickdestroy.gui.view.InfoDescriptionView InfoDescriptionView
     */
    public InfoModel(String fileName) {
        // Define file path
        String filePath = String.format("data/%s", fileName);

        // Load the file
        file = getClass().getClassLoader().getResourceAsStream(filePath);
        content = getData();
    }

    /**
     * Gets the content data of the info.
     * 
     * @return A {@code String} of the info's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the title of the info.
     * 
     * @param title The title of the info
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the title of the info.
     * 
     * @return A {@code String} of the info's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the text of the switcher button.
     * 
     * @param text The text of the switcher button
     */
    public void setSwitcherText(String text) {
        this.switcherText = text;
    }

    /**
     * Gets the text of the switcher button.
     * 
     * @return A {@code String} of the switcher button's text
     */
    public String getSwitcherText() {
        return switcherText;
    }

    /**
     * Retrieve content data from the specified file.
     */
    private String getData() {
        String content = "";
        int data;
        try {
            while ((data = file.read()) != -1) {
                content += (char) data;
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
