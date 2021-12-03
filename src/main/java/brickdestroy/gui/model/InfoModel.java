package brickdestroy.gui.model;

import java.io.IOException;
import java.io.InputStream;

public class InfoModel {

    private InputStream file;

    private String fileName;
    private String title;
    private String description;

    public InfoModel(String fileName) {
        // Define file name
        this.fileName = fileName;

        // Load the file
        file = getClass().getClassLoader().getResourceAsStream(this.fileName);
        description = getContent();
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
    public String getDescription() {
        return description;
    }

    /**
     * Retrieve description data from the specified file.
     * 
     * @param fileName The name of the file where the data is stored in
     */
    private String getContent() {
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
