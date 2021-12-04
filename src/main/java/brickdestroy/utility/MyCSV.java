package brickdestroy.utility;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MyCSV {

    private String[] all;

    private URI resource;
    private int size = 0;

    /**
     * The {@code MyCSV} class is a class I made to handle data in .csv files. I
     * decided to write my own CSV API instead of using an external library because
     * it's a fun challenge. Also cause why not lmao.
     * <p>
     * It is responsible for reading and writing data to and from the specified .csv
     * file.
     * 
     * @param fileName The name of the .csv file
     */
    public MyCSV(String fileName) {
        // Define file path
        String path = String.format("data/%s", fileName);

        // Define the resource file URI
        try {
            this.resource = getClass().getClassLoader().getResource(path).toURI();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialise the data
        all = getAllRowsAsString();
    }

    /**
     * Get an array of all rows in the form of {@code Strings}.
     * 
     * @return An array of {@code Strings}
     */
    public String[] getAllRowsAsString() {
        // Split the string by newlines
        try {
            String[] rows = Files.readString(Paths.get(resource)).split("\n");
            this.size = rows.length;

            // Get rid of carriage returns
            for (int i = 0; i < size; i++) {
                rows[i] = rows[i].replaceAll("\\r", "").replaceAll("\\n", "");
            }

            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get a 2D array of all rows in the form of {@code Strings}. The first
     * dimension of the array represents a row, while the second dimension
     * represents elements in that row.
     * 
     * @return A 2D array of {@code Strings}
     */
    public String[][] getAllRows() {
        // Initialise a 2D array of Strings
        String[][] array2D = new String[size][];

        for (int i = 0; i < size; i++)
            array2D[i] = all[i].split(",");

        return array2D;
    }

    /**
     * Get the row at the specified index in the form of a {@code String}.
     * 
     * @param index The row index
     * @return A {@code String} of the row
     */
    public String getRowAsString(int index) {
        return all[index];
    }

    /**
     * Get an array of the elements in the row at the specified index.
     * 
     * @param index The row index
     * @return An array of {@code Strings} of the elements in the row
     */
    public String[] getRow(int index) {
        return getAllRows()[index];
    }

    /**
     * Append a row at the end of the file.
     * 
     * @param first  The first element of the row
     * @param second The second element of the row
     */
    public void appendRow(String first, int second) {
        // Format the row
        String row = String.format("%s,%d\n", first, second);

        try {
            Files.writeString(Paths.get(resource), row, StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }

        all = getAllRowsAsString();
    }

    /**
     * Get the amount of rows in the csv file.
     * 
     * @return An {@code int} of the amount of rows
     */
    public int getSize() {
        return size;
    }

}