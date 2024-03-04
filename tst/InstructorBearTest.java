import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.*;


public class InstructorBearTest {

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void infectingTest() {
        Bear b = new Bear(true);

        CritterInfo info1 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.OTHER, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME);

        Assertions.assertEquals(Critter.Action.INFECT, b.getMove(info1));
        Assertions.assertEquals(Critter.Action.INFECT, b.getMove(info2));
        Assertions.assertEquals(Critter.Action.INFECT, b.getMove(info3));
        Assertions.assertEquals(Critter.Action.INFECT, b.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void hoppingTest() {
        Bear b = new Bear(true);

        CritterInfo info1 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.EMPTY, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME);

        Assertions.assertEquals(Critter.Action.HOP, b.getMove(info1));
        Assertions.assertEquals(Critter.Action.HOP, b.getMove(info2));
        Assertions.assertEquals(Critter.Action.HOP, b.getMove(info3));
        Assertions.assertEquals(Critter.Action.HOP, b.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void turningTest() {
        Bear b = new Bear(true);

        CritterInfo info1 = new TestInfo(Critter.Neighbor.WALL, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        CritterInfo info2 = new TestInfo(Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL, Critter.Neighbor.WALL);
        CritterInfo info3 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER, Critter.Neighbor.OTHER);
        CritterInfo info4 = new TestInfo(Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME, Critter.Neighbor.SAME);

        Assertions.assertEquals(Critter.Action.LEFT, b.getMove(info1));
        Assertions.assertEquals(Critter.Action.LEFT, b.getMove(info2));
        Assertions.assertEquals(Critter.Action.LEFT, b.getMove(info3));
        Assertions.assertEquals(Critter.Action.LEFT, b.getMove(info4));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void getColorTest() {
        Bear regBear = new Bear(false);
        Bear polarBear = new Bear(true);

        Assertions.assertEquals(Color.BLACK, regBear.getColor());
        Assertions.assertEquals(Color.BLACK, regBear.getColor());
        Assertions.assertEquals(Color.WHITE, polarBear.getColor());
        Assertions.assertEquals(Color.WHITE, polarBear.getColor());
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void toStringTest() {
        Bear b = new Bear(true);
        CritterInfo info = new TestInfo(Critter.Neighbor.WALL, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY, Critter.Neighbor.EMPTY);
        boolean shouldBeSlash = true;

        for (int i = 0; i < 37; i++) {
            Assertions.assertEquals((shouldBeSlash) ? "/" : "\\", b.toString());
            Assertions.assertEquals((shouldBeSlash) ? "/" : "\\", b.toString());
            shouldBeSlash = !shouldBeSlash;
            b.getMove(info);
        }
    }
}
