package brickdestroy.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MyCSV {

    private BufferedReader fileR;
    private String path = "";
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
        this.path = String.format("data/%s", fileName);

        // Load the file reader and get the size of the file
        this.fileR = getFileReader();
        this.size = getSize();
    }

    /**
     * Get an array of all rows in the form of {@code Strings}.
     * 
     * @return An array of {@code Strings}
     */
    public String[] getAllRowsAsString() {
        // Initialise an array of Strings
        String[] all = new String[size];

        for (int i = 0; i < size; i++)
            all[i] = getRowAsString(i);

        return all;
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
        String[][] all = new String[size][2];

        for (int i = 0; i < size; i++)
            all[i] = getRow(i);

        return all;
    }

    /**
     * Get the row in at the specified index in the form of a {@code String}.
     * 
     * @param index The row index
     * @return A {@code String} of the row
     */
    public String getRowAsString(int index) {
        // Initialise a String that represents a row
        String row = "";

        try {
            for (int i = 0; (i < index + 1) && i < size; i++)
                row = fileR.readLine();

            fileR.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Re-initialise the file
        fileR = getFileReader();
        return row;
    }

    /**
     * Get an array of the elements in the row at the specified index.
     * 
     * @param index The row index
     * @return An array of {@code Strings} of the elements in the row
     */
    public String[] getRow(int index) {
        return getRowAsString(index).split(",");
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
            URI resource = getClass().getClassLoader().getResource(path).toURI();
            Files.writeString(Paths.get(resource), row, StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileR = getFileReader();
        size = getSize();
    }

    private BufferedReader getFileReader() {
        return new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path)));
    }

    private int getSize() {
        int temp = 0;
        try {
            while (fileR.readLine() != null) {
                temp++;
            }
            fileR.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Re-initialise the file
        fileR = getFileReader();
        return temp;
    }

}
