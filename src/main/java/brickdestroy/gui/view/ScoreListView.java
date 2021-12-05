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

import brickdestroy.utility.MyCSV;

public class ScoreListView extends MyAbstractView {

    public static final String BACK = "BACK";

    private final String title = "High Scores";

    private JTable table;
    private JScrollPane scrollPane;

    private final String[] columns = { "ID", "Username", "Score" };
    private MyCSV csv;

    /**
     * The {@code ScoreListView} class is the View for the High Scores List that can
     * be accessed through the Home Menu.
     * <p>
     * It responsible for defining its components and adding them to itself. It uses
     * a {@code JTable} and {@code JScrollPane} to display the list of high scores,
     * which is sorted in descending order by scores.
     */
    public ScoreListView() {
        // Call the super constructor
        super("Back", "null", BACK, null);

        // Define csv file
        csv = new MyCSV("highscore.csv");

        // Get data and sort it
        String[][] all = csv.getAllRows();
        sort2DArray(all);

        // Determine size
        int size = (csv.getSize() < 20) ? csv.getSize() : 20;

        // Define the data, this adds a number count to the dataset as well
        String[][] data = new String[size][3];
        for (int i = 0; i < csv.getSize(); i++) {
            // Only display the first 20 entries
            if (csv.getSize() > 20 && i == 20)
                break;

            data[i][0] = Integer.toString(i + 1) + ".";
            data[i][1] = all[i][0];
            data[i][2] = all[i][1];
        }

        // Define table size
        int tableW = (int) (width * 0.8);
        int tableH = (int) (height * 0.55);

        // Define the table and its properties
        table = new JTable(data, columns);
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
     * Paint the title
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

    private void sort2DArray(String[][] arr) {
        // Bubble sort
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                // Check the second element of the array: the score
                int num1 = Integer.parseInt(arr[j][1]);
                int num2 = Integer.parseInt(arr[j + 1][1]);
                // Descending order
                if (num1 < num2) {
                    String[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
