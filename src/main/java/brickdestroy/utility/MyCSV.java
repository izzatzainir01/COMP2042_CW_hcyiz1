package brickdestroy.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MyCSV {

    private String fileName;

    private String[] all;
    private int size = 0;

    /**
     * The {@code MyCSV} class is a tool I made to handle data in .csv files. I
     * decided to write my own CSV API instead of using an external library because
     * it's a fun challenge. Also cause why not lmao.
     * <p>
     * It is responsible for reading and writing data to and from the specified .csv
     * file. If the file does not exist, it creates a new file with the same name
     * and copies an existing resource into it in the project's root directory. In
     * the case of running the game from a .jar file, it creates the file in the
     * same directory as the .jar file.
     * 
     * @param fileName The name of the .csv file
     */
    public MyCSV(String fileName) {

        // Define file name
        this.fileName = fileName;

        // Define file and check if it exists
        File file = new File(fileName);
        try {
            // Copy content from resource if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
                writeContents(file);
            }
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
            String[] rows = Files.readString(Paths.get(fileName)).split("\n");
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
        // Replace commas and spaces with underscore
        first = parseString(first);

        // Format the row
        String row = String.format("%s,%d\n", first, second);

        try {
            Files.writeString(Paths.get(fileName), row, StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }

        all = getAllRowsAsString();
    }

    /**
     * Append a row at the end of the file.
     * 
     * @param row The row of information
     */
    public void appendRow(String row) {
        // Parse the row first
        String first = row.split(",")[0];
        String second = row.split(",")[1];

        first = parseString(first);
        second = parseString(second).replace("_", "");

        appendRow(first, Integer.parseInt(second));
    }

    /**
     * Get the amount of rows in the csv file.
     * 
     * @return An {@code int} of the amount of rows
     */
    public int getSize() {
        return size;
    }

    private String parseString(String text) {
        text = text.replace(",", "_"); // Replace commas with an underscore
        text = text.replaceAll("\\s+", "_"); // Replace multiple spaces with an underscore
        text = text.replaceAll("_{2,}", "_"); // Replace multiple underscores with an underscore

        return text;
    }

    private void writeContents(File file) {
        BufferedWriter writer;
        String content = "";
        try {
            writer = new BufferedWriter(new FileWriter(file));
            content = getData();
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getData() {
        InputStream input;
        String content = "";
        int data;
        try {
            // Copy data from resources
            input = getClass().getClassLoader().getResourceAsStream("data/" + fileName);
            while ((data = input.read()) != -1) {
                content += (char) data;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
