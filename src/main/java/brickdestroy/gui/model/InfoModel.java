/*
 *  Brick Destroy - A simple arcade video game
 *  Copyright (C) 2021  Izzat Zainir
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package brickdestroy.gui.model;

import java.io.IOException;
import java.io.InputStream;

/**
 * A Model class that handles information in the Info section of the game. It
 * is responsible for fetching information from the specified resource file.
 * 
 * @param fileName The name of the resource file
 * 
 * @see brickdestroy.gui.view.InfoControlsView InfoControlsView
 * @see brickdestroy.gui.view.InfoDescriptionView InfoDescriptionView
 */
public class InfoModel {

    private InputStream file;
    private String content;

    /**
     * A Model class that handles information in the Info section of the game. It
     * is responsible for fetching information from the specified resource file.
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
