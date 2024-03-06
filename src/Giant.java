// Ben Blair
// Chat-GPT used

import java.awt.*;

public class Giant extends Critter {
    private int count;
    private final String[] giantNames = {"fee", "fie", "foe", "fum"};
    private int nameIndex; // Automatically initialized to 0

    public Giant() {
        this.count = 0;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }

    @Override
    public String toString() {
        // Update the nameIndex every 24 moves, since each name is used for 6 moves
        if (count % 6 == 0) {
            // Calculate which name to use based on the count
            nameIndex = (count / 6) % giantNames.length;
        }
        count++;
        return giantNames[nameIndex];
    }

    @Override
    public Action getMove(CritterInfo info) {
        // Always infect if an enemy is in front
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        }
        // Hop if possible
        else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        }
        // Otherwise, turn right
        else {
            return Action.RIGHT;
        }
    }
}
