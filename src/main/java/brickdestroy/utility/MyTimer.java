package brickdestroy.utility;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MyTimer {

    private static Timer timer;
    private static ActionListener listener;

    /**
     * The {@code MyTimer} class is a global/static timer. The intention behind this
     * class is to make sure that the game doesn't create multiple instances of a
     * {@code Timer} when the player starts a new game multiple times.
     */
    public MyTimer() {
        // Only instantiate a Timer if it hasn't already been instantiated before.
        if (timer == null)
            timer = new Timer(10, null);
    }

    /**
     * Add an {@code ActionListener} to the timer. This method automatically removes
     * the existing listener before adding the new one.
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
     * Start the timer.
     */
    public static void startTimer() {
        timer.start();
    }

    /**
     * Stop the timer.
     */
    public static void stopTimer() {
        timer.stop();
    }

}
