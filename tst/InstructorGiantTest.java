import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.*;

public class InstructorGiantTest {

    private static final String[] GIANT_SAYINGS = {"fee", "fie", "foe", "fum"};

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void infectingTest() {
        Giant g = new Giant();

        CritterInfo info1 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME);

        Assertions.assertEquals(Critter.Action.INFECT, g.getMove(info1));
        Assertions.assertEquals(Critter.Action.INFECT, g.getMove(info2));
        Assertions.assertEquals(Critter.Action.INFECT, g.getMove(info3));
        Assertions.assertEquals(Critter.Action.INFECT, g.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void hoppingTest() {
        Giant g = new Giant();

        CritterInfo info1 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME);

        Assertions.assertEquals(Critter.Action.HOP, g.getMove(info1));
        Assertions.assertEquals(Critter.Action.HOP, g.getMove(info2));
        Assertions.assertEquals(Critter.Action.HOP, g.getMove(info3));
        Assertions.assertEquals(Critter.Action.HOP, g.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void turningTest() {
        Giant g = new Giant();

        CritterInfo info1 = new TestInfo(Critter.Neighbor.WALL, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME);

        Assertions.assertEquals(Critter.Action.RIGHT, g.getMove(info1));
        Assertions.assertEquals(Critter.Action.RIGHT, g.getMove(info2));
        Assertions.assertEquals(Critter.Action.RIGHT, g.getMove(info3));
        Assertions.assertEquals(Critter.Action.RIGHT, g.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void toStringTest() {
        Giant g = new Giant();
        CritterInfo info = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        int stepCount = 0;
        for (int i = 0; i < 137; i++) {
            Assertions.assertEquals(GIANT_SAYINGS[(stepCount / 6) % 4], g.toString());
            Assertions.assertEquals(GIANT_SAYINGS[(stepCount / 6) % 4], g.toString());
            g.getMove(info);
            stepCount++;
        }
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void getColorTest() {
        Giant g = new Giant();
        Assertions.assertEquals(Color.GRAY, g.getColor());
        Assertions.assertEquals(Color.GRAY, g.getColor());
    }
}
