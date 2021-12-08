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

import brickdestroy.gui.model.InfoModel;

/**
 * A child class of {@link InfoView} that represents the Controls
 * part of the Info section. It is responsible for defining the title, the
 * Second button action command and text, and the Info model.
 */
public class InfoDescriptionView extends InfoView {

    public static final String CONTROLS = "INFO_CONTROLS";

    /**
     * A child class of {@link InfoView} that represents the Controls
     * part of the Info section. It is responsible for defining the title, the
     * Second button action command and text, and the Info model.
     */
    public InfoDescriptionView(InfoModel info) {
        super(info, CONTROLS, "Description", "Controls");
    }

}
