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
