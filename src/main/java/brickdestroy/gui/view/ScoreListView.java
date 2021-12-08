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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import brickdestroy.gui.model.ScoreModel;

/**
 * A child class of {@link AllView} that represents the High Score List
 * section of the game, accessible through the Home Menu. It displays a list of
 * usernames and scores that is sorted from the highest score to the lowest and
 * only displays 20 entries.
 * <p>
 * It is responsible for defining the components that make up this View and
 * adding them to itself.
 * 
 * @see ScoreModel
 */
public class ScoreListView extends AllView {

    public static final String BACK = "BACK";

    private final String title = "High Scores";

    private JTable table;
    private JScrollPane scrollPane;

    private final String[] columns = { "ID", "Username", "Score" };

    /**
     * A child class of {@link AllView} that represents the High Score List
     * section of the game, accessible through the Home Menu. It displays a list of
     * usernames and scores that is sorted from the highest score to the lowest and
     * only displays 20 entries.
     * <p>
     * It is responsible for defining the components that make up this View and
     * adding them to itself.
     * 
     * @param model The score model
     * 
     * @see ScoreModel
     */
    public ScoreListView(ScoreModel model) {
        // Call the super constructor
        super("Back", null, BACK, null);

        // Define table size
        int tableW = (int) (width * 0.8);
        int tableH = (int) (height * 0.55);

        // Define the table and its properties
        table = new JTable(model.getSortedData(), columns);
        // Size and locations
        table.setSize(tableW, tableH);
        table.setPreferredScrollableViewportSize(new Dimension(tableW, tableH));
        // No table header
        table.setTableHeader(null);
        // Width of columns and row height
        table.getColumnModel().getColumn(0).setPreferredWidth((int) (tableW * 0.1));
        table.getColumnModel().getColumn(1).setPreferredWidth((int) (tableW * 0.7));
        table.getColumnModel().getColumn(2).setPreferredWidth((int) (tableW * 0.2));
        table.setRowHeight(40);
        // Set uneditable
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setDefaultEditor(Object.class, null);
        // Remove background and borders
        table.setOpaque(false);
        table.setBorder(BorderFactory.createEmptyBorder());
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
        table.setShowGrid(false);
        // Set font and colour
        table.setFont(new Font("Impact", Font.PLAIN, 30));
        table.setForeground(Color.BLACK);

        // Define the scroll pane
        scrollPane = new JScrollPane(table);
        // Set size and location
        scrollPane.setSize(tableW, tableH);
        scrollPane.setPreferredSize(new Dimension(tableW, tableH));
        scrollPane.setLocation(width / 2 - table.getWidth() / 2, (int) (height * 0.22));
        // Remove background and borders
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Set button2 invisible
        button2.setVisible(false);
        // Set button1 to center of the frame
        int bottomOfTable = scrollPane.getY() + scrollPane.getHeight();
        button1.setLocation(width / 2 - button1.getWidth() / 2,
                (int) ((bottomOfTable + (height - bottomOfTable) / 2) - button1.getHeight() / 2));

        // Add the table
        this.add(scrollPane);
    }

    /**
     * Draws the title text
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw title text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Impact", Font.PLAIN, 50));
        g2d.drawString(title, (int) (width * 0.1), (int) (height * 0.15));
    }

}
