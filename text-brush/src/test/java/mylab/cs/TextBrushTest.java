package mylab.cs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextBrushTest {

    TextBrush sut = new TextBrush();

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final StringBuilder L_TEST_RESULT1 = new StringBuilder();
    private static final StringBuilder L_TEST_RESULT2 = new StringBuilder();
    private static final StringBuilder R_TEST_RESULT = new StringBuilder();
    private static final StringBuilder B_TEST_RESULT = new StringBuilder();

    @Before
    public void setup() {
        L_TEST_RESULT1.append("----------------------");
        L_TEST_RESULT1.append(LINE_SEPARATOR);
        L_TEST_RESULT1.append("|                    |");
        L_TEST_RESULT1.append(LINE_SEPARATOR);
        L_TEST_RESULT1.append("|xxxxxx              |");
        L_TEST_RESULT1.append(LINE_SEPARATOR);
        L_TEST_RESULT1.append("|                    |");
        L_TEST_RESULT1.append(LINE_SEPARATOR);
        L_TEST_RESULT1.append("|                    |");
        L_TEST_RESULT1.append(LINE_SEPARATOR);
        L_TEST_RESULT1.append("----------------------");
        L_TEST_RESULT1.append(LINE_SEPARATOR);

        L_TEST_RESULT2.append("----------------------");
        L_TEST_RESULT2.append(LINE_SEPARATOR);
        L_TEST_RESULT2.append("|                    |");
        L_TEST_RESULT2.append(LINE_SEPARATOR);
        L_TEST_RESULT2.append("|xxxxxx              |");
        L_TEST_RESULT2.append(LINE_SEPARATOR);
        L_TEST_RESULT2.append("|     x              |");
        L_TEST_RESULT2.append(LINE_SEPARATOR);
        L_TEST_RESULT2.append("|     x              |");
        L_TEST_RESULT2.append(LINE_SEPARATOR);
        L_TEST_RESULT2.append("----------------------");
        L_TEST_RESULT2.append(LINE_SEPARATOR);

        R_TEST_RESULT.append("----------------------");
        R_TEST_RESULT.append(LINE_SEPARATOR);
        R_TEST_RESULT.append("|             xxxxx  |");
        R_TEST_RESULT.append(LINE_SEPARATOR);
        R_TEST_RESULT.append("|xxxxxx       x   x  |");
        R_TEST_RESULT.append(LINE_SEPARATOR);
        R_TEST_RESULT.append("|     x       xxxxx  |");
        R_TEST_RESULT.append(LINE_SEPARATOR);
        R_TEST_RESULT.append("|     x              |");
        R_TEST_RESULT.append(LINE_SEPARATOR);
        R_TEST_RESULT.append("----------------------");
        R_TEST_RESULT.append(LINE_SEPARATOR);

        B_TEST_RESULT.append("----------------------");
        B_TEST_RESULT.append(LINE_SEPARATOR);
        B_TEST_RESULT.append("|oooooooooooooxxxxxoo|");
        B_TEST_RESULT.append(LINE_SEPARATOR);
        B_TEST_RESULT.append("|xxxxxxooooooox   xoo|");
        B_TEST_RESULT.append(LINE_SEPARATOR);
        B_TEST_RESULT.append("|     xoooooooxxxxxoo|");
        B_TEST_RESULT.append(LINE_SEPARATOR);
        B_TEST_RESULT.append("|     xoooooooooooooo|");
        B_TEST_RESULT.append(LINE_SEPARATOR);
        B_TEST_RESULT.append("----------------------");
        B_TEST_RESULT.append(LINE_SEPARATOR);

        sut.execute("C 20 4");
    }

    @Test
    public void testDraw() {
        StringBuilder result = sut.execute("L 1 2 6 2");
        Assert.assertEquals(result.toString(), L_TEST_RESULT1.toString());

        result = sut.execute("L 6 3 6 4");
        Assert.assertEquals(result.toString(), L_TEST_RESULT2.toString());

        result = sut.execute("R 14 1 18 3");
        Assert.assertEquals(result.toString(), R_TEST_RESULT.toString());

        result = sut.execute("B 10 3 o");
        Assert.assertEquals(result.toString(), B_TEST_RESULT.toString());
    }

}
