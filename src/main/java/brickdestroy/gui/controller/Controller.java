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

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import brickdestroy.gui.MainFrame;
import brickdestroy.gui.view.AllView;

/**
 * An abstract class that represents a Controller for this game. It provides a
 * template that all Controllers in this game use to ease the process of
 * creating a new Controller. The Controllers in this game use a {@code JPanel}
 * as an in-between for the {@code MainFrame} and the Views that it adds to
 * itself.
 * <p>
 * I am aware that adding Views to the {@code JPanel} makes it more like the
 * 'display' than the {@code MainFrame}, who is supposed to have that role.
 * However, I simply couldn't figure out how to add and remove Views from the
 * frame without it being very buggy. So I settled with thinking of the
 * {@code JPanel} as more of a screen while the {@code MainFrame} as more of a
 * phone frame that holds the screen.
 * <p>
 * The {@code Controller} is responsible for predefining some properties
 * of the {@code JPanel} that it uses and methods to add and remove a View from
 * the {@code JPanel}.
 * 
 * @see MainFrame
 * @see JPanel
 */
public abstract class Controller implements ActionListener {

    protected MainFrame frame;
    protected int width = MainFrame.WIDTH;
    protected int height = MainFrame.HEIGHT;

    protected JPanel panel;

    /**
     * An abstract class that represents a Controller for this game. It provides a
     * template that all Controllers in this game use to ease the process of
     * creating a new Controller. The Controllers in this game use a {@code JPanel}
     * as an in-between for the {@code MainFrame} and the Views that it adds to
     * itself.
     * <p>
     * I am aware that adding Views to the {@code JPanel} makes it more like the
     * 'display' than the {@code MainFrame}, who is supposed to have that role.
     * However, I simply couldn't figure out how to add and remove Views from the
     * frame without it being very buggy. So I settled with thinking of the
     * {@code JPanel} as more of a screen while the {@code MainFrame} as more of a
     * phone frame that holds the screen.
     * <p>
     * The {@code Controller} is responsible for predefining some properties
     * of the {@code JPanel} that it uses and methods to add and remove a View from
     * the {@code JPanel}.
     * 
     * @param frame The {@code MainFrame}
     * 
     * @see MainFrame
     * @see JPanel
     */
    protected Controller(MainFrame frame) {

        // Define the frame
        this.frame = frame;

        // Define the panel
        this.panel = new JPanel();

        // Define the panel's properties
        panel.setBounds(0, 0, width, height);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(null);
    }

    /**
     * Gets the {@code JPanel} of this controller. This is only meant for the
     * {@code MainFrame} to use but I couldn't figure out a more 'secure' way of
     * doing this.
     * 
     * @return The {@code JPanel} of this controller
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Adds a {@code JLabel} to this controller. This method automatically calls the
     * {@code revalidate} and {@code repaint} methods upon adding a View.
     * 
     * @param view The new view to be added to this controller
     */
    protected void addView(JLabel view) {
        panel.add(view);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Adds a {@code MyAbstractView} to this controller. This method automatically
     * calls the {@code revalidate} and {@code repaint} methods upon adding a
     * View. It also automatically adds this controller as an {@code ActionListener}
     * to the given View.
     * 
     * @param view The new view to be added to this controller
     */
    protected void addView(AllView view) {
        panel.add(view);
        panel.revalidate();
        panel.repaint();
        view.setActionListener(this);
    }

    /**
     * Removes a View from this controller. This method automatically calls the
     * {@code revalidate} and {@code repaint} methods upon removing the View.
     * 
     * @param view The view to be removed from this controller
     */
    protected void removeView(JLabel view) {
        panel.remove(view);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Removes a View from this controller. This method automatically calls the
     * {@code revalidate} and {@code repaint} methods upon removing the View. It
     * also removes this controller as an {@code ActionListener} from the given
     * View.
     * 
     * @param view The view to be removed from this controller
     */
    protected void removeView(AllView view) {
        panel.remove(view);
        panel.revalidate();
        panel.repaint();
        view.removeActionListener(this);
    }

}
