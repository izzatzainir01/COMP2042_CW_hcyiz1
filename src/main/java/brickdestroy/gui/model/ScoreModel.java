package brickdestroy.gui.model;

import brickdestroy.utility.MyCSV;

public class ScoreModel {

    private MyCSV highscore;
    private String username;

    /**
     * The {@code ScoreModel} class is the Model for handling Scores in the game.
     * 
     * @param username
     */
    public ScoreModel(String username) {
        // Define the highscore file
        highscore = new MyCSV("highscore.csv");
        this.username = username;
    }

    public void addScore(int score) {
        highscore.appendRow(username, score);
    }

}
