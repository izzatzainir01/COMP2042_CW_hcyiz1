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
import brickdestroy.gui.model.ScoreModel;
import brickdestroy.gui.view.MenuHomeView;
import brickdestroy.gui.view.ScoreListView;

/**
 * A child class of {@link Controller} that handles the Main Menu
 * section of the game. It is responsible for switching between the different
 * sections of the game, namely the Game section, the Info section and the High
 * Scores section.
 * 
 * @see ScoreListView
 * @see InfoController
 * @see GameController
 */
public class MenuController extends Controller {

    private MenuHomeView home;
    private ScoreListView scores;

    /**
     * A child class of {@link Controller} that handles the Main Menu
     * section of the game. It is responsible for switching between the different
     * sections of the game, namely the Game section, the Info section and the High
     * Scores section.
     * 
     * @param frame The {@code MainFrame}
     * 
     * @see ScoreListView
     * @see InfoController
     * @see GameController
     */
    public MenuController(MainFrame frame) {
        // Call the super constructor
        super(frame);

        // Add the home view upon first launch
        addView(home = new MenuHomeView());
    }

    /**
     * Defines the {@code MenuHomeViews'} and {@code ScoreListViews'} buttons'
     * actions.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            // HomeMenuView's buttons
            case MenuHomeView.START:
                frame.addGameController();
                break;

            case MenuHomeView.INFO:
                frame.addInfoController();
                break;

            case MenuHomeView.SCORES:
                addView(scores = new ScoreListView(new ScoreModel()));
                removeView(home);
                break;

            case MenuHomeView.EXIT:
                frame.exit();
                break;

            // ScoreListView's buttons
            case ScoreListView.BACK:
                addView(home = new MenuHomeView());
                removeView(scores);
                break;

            default:
                break;
        }

    }

}
