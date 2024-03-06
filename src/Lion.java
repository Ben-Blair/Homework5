// Ben Blair
// Chat-GPT used

import java.awt.*;
import java.util.Random;

public class Lion extends Critter {
    private int moves;
    private Color color;
    private final Random random = new Random();
    private static final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE}; // Array of colors

    public Lion() {
        this.moves = 0;
        changeColor(); // Initialize the lion with a random color
    }

    private void changeColor() {
        // Selects a new color randomly from the COLORS array
        color = COLORS[random.nextInt(COLORS.length)];
    }

    @Override
    public Color getColor() {
        if (++moves % 3 == 0) { // Change color every 3 moves
            changeColor();
        }
        return color;
    }

    @Override
    public String toString() {
        return "L";
    }

    @Override
    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }
}
