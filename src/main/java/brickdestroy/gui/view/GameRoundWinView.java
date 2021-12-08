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
package brickdestroy.gui.view;

/**
 * A child class of {@link GameEndView} that represents the View for
 * when the user clears a round or level. It is responsible for defining the
 * title, the First button action command and text.
 */
public class GameRoundWinView extends GameEndView {

    public static final String NEXT = "GAME_NEXT";

    /**
     * A child class of {@link GameEndView} that represents the View for
     * when the user clears a round or level. It is responsible for defining the
     * title, the First button action command and text.
     */
    public GameRoundWinView() {
        super("Level Completed!", "Next", NEXT);
    }

}
