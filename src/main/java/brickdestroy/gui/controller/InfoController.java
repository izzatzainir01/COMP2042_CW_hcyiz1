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
package brickdestroy.gui.controller;

import java.awt.event.ActionEvent;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.model.InfoModel;
import brickdestroy.gui.view.InfoView;
import brickdestroy.gui.view.InfoControlsView;
import brickdestroy.gui.view.InfoDescriptionView;

/**
 * A child class of {@link Controller} that handles the Info section of
 * the game, accessible through the Main Menu. It is responsible for fetching
 * information from the resources, displaying it in its respective View and
 * switching between the Views that take up the Info section.
 * <p>
 * Currently, the Info section consists of the {@code InfoDescriptionView} and
 * the {@code InfoControlsView}.
 * 
 * @see InfoDescriptionView
 * @see InfoControlsView
 */
public class InfoController extends Controller {

    private InfoView description;
    private InfoModel descriptionInfo;

    private InfoView controls;
    private InfoModel controlsInfo;

    /**
     * A child class of {@link Controller} that handles the Info section of
     * the game, accessible through the Main Menu. It is responsible for fetching
     * information from the resources, displaying it in its respective View and
     * switching between the Views that take up the Info section.
     * <p>
     * Currently, the Info section consists of the {@code InfoDescriptionView} and
     * the {@code InfoControlsView}.
     * 
     * @param frame The {@code MainFrame}
     * 
     * @see InfoDescriptionView
     * @see InfoControlsView
     */
    public InfoController(MainFrame frame) {
        // Call the super constructor
        super(frame);

        // Define the description info model
        descriptionInfo = new InfoModel("description.txt");

        // Define the controls info model
        controlsInfo = new InfoModel("controls.txt");

        // Add the description view
        addView(description = new InfoDescriptionView(descriptionInfo));
    }

    /**
     * Defines the {@code InfoViews'} buttons' actions.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            // Back button
            case InfoView.BACK:
                frame.addMenuController();
                break;

            // InfoDescriptionView secondary button
            case InfoDescriptionView.CONTROLS:
                addView(controls = new InfoControlsView(controlsInfo));
                removeView(description);
                break;

            // InfoConstrolsView secondary button
            case InfoControlsView.DESCRIPTION:
                addView(description = new InfoDescriptionView(descriptionInfo));
                removeView(controls);
                break;

            default:
                break;
        }

    }

}
