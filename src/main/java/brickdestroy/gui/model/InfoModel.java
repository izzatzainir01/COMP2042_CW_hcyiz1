package brickdestroy.gui.model;

import java.io.IOException;
import java.io.InputStream;

public class InfoModel {

    private InputStream file;

    private String fileName;
    private String title;
    private String content;
    private String switcherText;

    /**
     * The {@code InfoModel} class is the Model for the Info section of the game.
     * <p>
     * It is responsible for retrieving data from the specified file and
     * storing other information that are relevant to the Info section.
     * 
     * @param fileName The name of the file that the data is stored in
     */
    public InfoModel(String fileName) {
        // Define file name
        this.fileName = fileName;

        // Load the file
        file = getClass().getClassLoader().getResourceAsStream(this.fileName);
        content = getData();
    }

    /**
     * Set the title of the info.
     * 
     * @param title The title of the info
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the text of the switcher button.
     * 
     * @param text The text of the switcher button
     */
    public void setSwitcherText(String text) {
        this.switcherText = text;
    }

    /**
     * Get the title of the info.
     * 
     * @return A {@code String} of the info's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the description content of the info.
     * 
     * @return A {@code String} of the info's description content
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the text of the switcher button.
     * 
     * @return A {@code String} of the switcher button's text
     */
    public String getSwitcherText() {
        return switcherText;
    }

    /**
     * Retrieve description data from the specified file.
     * 
     * @param fileName The name of the file where the data is stored in
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
