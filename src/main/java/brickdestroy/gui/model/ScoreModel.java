package brickdestroy.gui.model;

import brickdestroy.utility.MyCSV;

public class ScoreModel {

    private MyCSV csv;
    private String username = "";

    /**
     * The {@code ScoreModel} class is the Model for handling Scores in the game.
     * <p>
     * It is responsible for temporarily storing the username and total score for
     * the current game session and storing them into the database. It is also
     * responsible for properly formatting the data before storing them into the
     * database.
     * 
     * @param username The username of the current game session
     */
    public ScoreModel() {
        // Define the highscore file
        csv = new MyCSV("highscore.csv");
    }

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
     * 
     * @param username
     */
    public void setUsername(String username) {
        // Assign the parsed username
        this.username = parseString(username);
    }

    /**
     * Add the score to the database
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
