import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.*;

public class InstructorLionTest {

    /*
     * The movement for the Lion species is defined as follows
     *
     * If an enemy is in front, attack
     * else if a wall is in front or to the right, turn left
     * else if a fellow Lion is in front, turn right
     * otherwise, hop forward.
     */
    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void infectingTest() {
        Lion l = new Lion();

        CritterInfo info1 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME);

        Assertions.assertEquals(Critter.Action.INFECT, l.getMove(info1));
        Assertions.assertEquals(Critter.Action.INFECT, l.getMove(info2));
        Assertions.assertEquals(Critter.Action.INFECT, l.getMove(info3));
        Assertions.assertEquals(Critter.Action.INFECT, l.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void turnLeftTest() {
        Lion l = new Lion();

        CritterInfo info1 = new TestInfo(Critter.Neighbor.WALL, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.WALL);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.WALL);

        Assertions.assertEquals(Critter.Action.LEFT, l.getMove(info1));
        Assertions.assertEquals(Critter.Action.LEFT, l.getMove(info2));
        Assertions.assertEquals(Critter.Action.LEFT, l.getMove(info3));
        Assertions.assertEquals(Critter.Action.LEFT, l.getMove(info4));

    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void turnRightTest() {
        Lion l = new Lion();

        CritterInfo info1 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.SAME);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.EMPTY);

        Assertions.assertEquals(Critter.Action.RIGHT, l.getMove(info1));
        Assertions.assertEquals(Critter.Action.RIGHT, l.getMove(info2));
        Assertions.assertEquals(Critter.Action.RIGHT, l.getMove(info3));
        Assertions.assertEquals(Critter.Action.RIGHT, l.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void hoppingTest() {
        Lion l = new Lion();

        CritterInfo info1 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.SAME);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.EMPTY);

        Assertions.assertEquals(Critter.Action.HOP, l.getMove(info1));
        Assertions.assertEquals(Critter.Action.HOP, l.getMove(info2));
        Assertions.assertEquals(Critter.Action.HOP, l.getMove(info3));
        Assertions.assertEquals(Critter.Action.HOP, l.getMove(info4));

    }


    /*
     * The Lion should always be L
     */
    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void toStringTest() {
        Lion l = new Lion();
        Assertions.assertEquals("L", l.toString());
        Assertions.assertEquals("L", l.toString());
    }


    /*
     * The Lion randomly chooses red, green, or blue, and reselects every 3 moves.
     */
    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void getColorTest() {
        Lion l = new Lion();
        CritterInfo info = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);

        boolean wasRed = false, wasGreen = false, wasBlue = false;
        // With 500 cycles, the odds of false failure (P = 9E-89) are less than randomly choosing a single proton
        // from all the protons in the known universe (1E82 protons). This is probably "good enough".
        for (int colorCycles = 0; colorCycles < 500; colorCycles++) {
            Color startColor = l.getColor();
            if (startColor.equals(Color.RED)) {
                wasRed = true;
            } else if (startColor.equals(Color.GREEN)) {
                wasGreen = true;
            } else if (startColor.equals(Color.BLUE)) {
                wasBlue = true;
            } else {
                Assertions.fail("Color must be red, green, or blue.");
            }
            for (int i = 0; i < 3; i++) {
                Assertions.assertEquals(startColor, l.getColor());
                Assertions.assertEquals(startColor, l.getColor());
                l.getMove(info);
            }
        }
        Assertions.assertTrue(wasRed && wasGreen && wasBlue, "Red, green, and blue must appear in the testing frame.");
    }
}
