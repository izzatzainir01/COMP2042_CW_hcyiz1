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
 * A child class of {@link InfoView} that represents the Description
 * part of the Info section. It is responsible for defining the title, the
 * Second button action command and text, and the Info model.
 */
public class InfoControlsView extends InfoView {

    public static final String DESCRIPTION = "INFO_DESCRIPTION";

    /**
     * A child class of {@link InfoView} that represents the Description
     * part of the Info section. It is responsible for defining the title, the
     * Second button action command and text, and the Info model.
     */
    public InfoControlsView(InfoModel info) {
        super(info, DESCRIPTION, "Controls", "Description");

        button2.setSize((int) (width * 0.27), (int) (height * 0.15));
        button2.setLocation((int) (width * 0.9 - button2.getWidth()), (int) (height * 0.75));
    }

}
