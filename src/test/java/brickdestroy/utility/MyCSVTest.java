package brickdestroy.utility;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

public class MyCSVTest {

    private MyCSV highscore;

    private String[] def = {
            "John,10",
            "Doe,22",
            "Mary,9",
            "Jane,1000",
            "Bro,42",
            "Code,69"
    };

    private String[][] def2D = {
            { "John", "10" },
            { "Doe", "22" },
            { "Mary", "9" },
            { "Jane", "1000" },
            { "Bro", "42" },
            { "Code", "69" }
    };

    /*
     * REMINDER TO SELF TO CLEAN AND RECOMPILE EVERY TIME I WANNA RUN TESTS
     */

    @Before
    public void setup() {
        highscore = new MyCSV("test_dummy.csv");
    }

    @Test
    public void shouldGetStringsOfAllRows() {
        String[] rows = highscore.getAllRowsAsString();

        int i = 0;
        for (String row : def) {
            Assert.assertEquals(true, row instanceof String);
            Assert.assertEquals(row, rows[i]);
            i++;
        }
    }

    @Test
    public void shouldGet2DArrayOfAllRows() {
        String[][] array = highscore.getAllRows();

        int i = 0;
        for (String[] row : def2D) {
            Assert.assertEquals(true, array[i].getClass().isArray());
            Assert.assertEquals(row[0], array[i][0]);
            Assert.assertEquals(row[0], array[i][0]);
            i++;
        }
    }

    @Test
    public void shouldGetRowAsStringByIndex0() {
        String row = highscore.getRowAsString(0);

        Assert.assertEquals(true, highscore.getRowAsString(0) instanceof String);
        Assert.assertEquals(def[0], row);

    }

    @Test
    public void shouldGetRowAsStringByIndex3() {
        String row = highscore.getRowAsString(3);

        Assert.assertEquals(true, highscore.getRowAsString(3) instanceof String);
        Assert.assertEquals(def[3], row);

    }

    @Test
    public void shouldGetRowAsArrayByIndex0() {
        String[] elements = highscore.getRow(0);

        Assert.assertEquals(true, highscore.getRow(0).getClass().isArray());
        Assert.assertEquals(def2D[0][0], elements[0]);
        Assert.assertEquals(def2D[0][1], elements[1]);
    }

    @Test
    public void shouldGetRowAsArrayByIndex3() {
        String[] elements = highscore.getRow(3);

        Assert.assertEquals(true, highscore.getRow(3).getClass().isArray());
        Assert.assertEquals(def2D[3][0], elements[0]);
        Assert.assertEquals(def2D[3][1], elements[1]);
    }

    @Test
    public void shouldAppendRow() {
        highscore.appendRow("Izzat", 99);
        Assert.assertEquals("Izzat,99", highscore.getRowAsString(highscore.getSize() - 1));
    }
}
