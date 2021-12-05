package brickdestroy.gui.model;

import brickdestroy.utility.MyCSV;

public class ScoreModel {

    private MyCSV highscore;
    private String username;

    /**
     * The {@code ScoreModel} class is the Model for handling Scores in the game.
     * <p>
     * It is responsible for temporarily storing the username and total score for
     * the current game session and storing them into the database.
     * 
     * @param username The username of the current game session
     */
    public ScoreModel(String username) {
        // Define the highscore file
        highscore = new MyCSV("highscore.csv");

        // Default the name to Guest_x if no username is given
        if (username.equals("")) {
            int guestNum = 0;
            String name = "";
            for (int i = 0; i < highscore.getSize(); i++) {
                name = highscore.getRow(i)[0];
                if (name.equals("Guest_" + guestNum)) {
                    guestNum++;
                }
            }
            username = "Guest_" + guestNum;
        }

        // Assign the username
        this.username = username;
    }

    /**
     * Add the score to the database
     * 
     * @param score The score
     */
    public void addScore(int score) {
        highscore.appendRow(username, score);
    }

}
