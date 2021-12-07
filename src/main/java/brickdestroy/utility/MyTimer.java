package brickdestroy.utility;

import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * A utility class that represents a global/static timer. The intention behind
 * this class is to make sure that the game doesn't create multiple instances of
 * a {@code Timer} when the player starts a new game multiple times.
 * 
 * @see Timer
 */
public class MyTimer {

    private static Timer timer;
    private static ActionListener listener;

    /**
     * A utility class that represents a global/static timer. The intention behind
     * this class is to make sure that the game doesn't create multiple instances of
     * a {@code Timer} when the player starts a new game multiple times.
     * 
     * @see Timer
     */
    public MyTimer() {

        // Only instantiate a Timer if it hasn't already been instantiated before.
        if (timer == null)
            timer = new Timer(10, null);
    }

    /**
     * Adds an {@code ActionListener} to the timer. This method automatically
     * removes the existing listener before adding the new one.
     * 
     * @param l An {@code ActionListener}
     */
    public static void addTimerAction(ActionListener l) {
        // Remove the old listener
        if (listener != null) {
            timer.removeActionListener(listener);
        }

        // Add the new listener
        listener = l;
        timer.addActionListener(listener);
    }

    /**
     * Starts the timer.
     */
    public static void startTimer() {
        timer.start();
    }

    /**
     * Stops the timer.
     */
    public static void stopTimer() {
        timer.stop();
    }

}
