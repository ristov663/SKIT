import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TriangleTest {

    private int side1;
    private int side2;
    private int side3;
    private boolean expected;

    public TriangleTest(int side1, int side2, int side3, boolean expected) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{

                {3, 4, 5, true},  // Валиден триаголник
                {3, 3, 7, false}, // Предикатот 1 не е исполнет (side1 + side2 > side3)
                {7, 3, 3, false}, // Предикатот 2 не е исполнет (side1 + side3 > side2)
                {3, 7, 3, false}, // Предикатот 3 не е исполнет (side2 + side3 > side1)
        });
    }

    @Test
    public void testIsTriangle() {
        assertEquals(expected, isTriangle(side1, side2, side3));
    }

    public boolean isTriangle(int side1, int side2, int side3) {

        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }
}
