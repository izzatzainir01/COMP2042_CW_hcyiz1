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
package brickdestroy;

import javax.swing.SwingUtilities;

import brickdestroy.gui.MainFrame;

/**
 * The Main class of the game, which contains the {@code main} method. It simply
 * instantiates the {@link MainFrame}.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }

}
