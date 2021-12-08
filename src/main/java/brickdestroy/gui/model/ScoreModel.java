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

import brickdestroy.utility.MyCSV;

/**
 * A Model class thath handles writing score information to a .csv file during
 * gameplay. It stores the user's username and score of the current gaming
 * session until the {@code GameController} calls for it to save the information
 * into a .csv file.
 * <p>
 * It is responsible for loading the .csv file where the scores are stored,
 * parsing the username to remove any commas, extra spaces and other control
 * characters, assigning the user with a Guest username, and storing the
 * information into the .csv file.
 * 
 * @see brickdestroy.gui.controller.GameController GameController
 * @see brickdestroy.gui.view.ScorePromptView ScorePromptView
 * @see brickdestroy.gui.view.ScoreListView ScoreListView
 */
public class ScoreModel {

    private MyCSV csv;
    private String username = "";

    /**
     * A Model class that handles writing score information to a .csv file during
     * gameplay. It stores the user's username and score of the current gaming
     * session until the {@code GameController} calls for it to save the information
     * into a .csv file.
     * <p>
     * It is responsible for loading the .csv file where the scores are stored,
     * parsing the username to remove any commas, extra spaces and other control
     * characters, assigning the user with a Guest username, and storing the
     * information into the .csv file.
     * 
     * @see brickdestroy.gui.controller.GameController GameController
     * @see brickdestroy.gui.view.ScorePromptView ScorePromptView
     * @see brickdestroy.gui.view.ScoreListView ScoreListView
     */
    public ScoreModel() {
        // Define the highscore file
        csv = new MyCSV("highscore.csv");
    }

    /**
     * Gets the data contained in the .csv file sorted by the score from highest to
     * lowest.
     * 
     * @return A 2D {@code String} array of the sorted data
     */
    public String[][] getSortedData() {
        // Get data and sort it
        String[][] all = csv.getAllRows();
        sort2DArray(all);

        // Determine size
        int size = (csv.getSize() < 20) ? csv.getSize() : 20;

        // Define the data, this adds a number count to the dataset as well
        String[][] data = new String[size][3];
        for (int i = 0; i < csv.getSize(); i++) {
            // Only display the first 20 entries
            if (csv.getSize() > 20 && i == 20)
                break;

            data[i][0] = Integer.toString(i + 1) + ".";
            data[i][1] = all[i][0];
            data[i][2] = all[i][1];
        }

        return data;
    }

    /**
     * Sets the username of the current gaming session.
     * 
     * @param username The username
     */
    public void setUsername(String username) {
        // Assign the parsed username
        this.username = parseString(username);
    }

    /**
     * Adds the score to the database.
     * 
     * @param score The score
     */
    public void addScore(int score) {
        // Default the name to Guest_x if no username is given
        if (username.equals("")) {
            int guestNum = 0;
            String name = "";
            for (int i = 0; i < csv.getSize(); i++) {
                name = csv.getRow(i)[0];
                if (name.equals("Guest_" + guestNum)) {
                    guestNum++;
                }
            }
            username = "Guest_" + guestNum;
        }

        String row = String.format("%s,%d", username, score);
        csv.appendRow(row);
    }

    private String parseString(String text) {
        text = text.replace(",", "_"); // Replace commas with an underscore
        text = text.replaceAll("\\s+", "_"); // Replace multiple spaces with an underscore
        text = text.replaceAll("_{2,}", "_"); // Replace multiple underscores with an underscore

        return text;
    }

    private void sort2DArray(String[][] arr) {
        // Bubble sort
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                // Check the second element of the array: the score
                int num1 = Integer.parseInt(arr[j][1]);
                int num2 = Integer.parseInt(arr[j + 1][1]);
                // Descending order
                if (num1 < num2) {
                    String[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
