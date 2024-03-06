import java.awt.*;

public class Bear extends Critter {
    private int count = 0; // Automatically initialized to 0
    private final boolean polar; // Marked as final because it's set once and should not change

    public Bear(boolean polar) {
        this.polar = polar;
    }

    @Override
    public Color getColor() {
        // Returns WHITE for polar bears, BLACK otherwise
        return this.polar ? Color.WHITE : Color.BLACK;
    }

    @Override
    public String toString() {
        // Alternates between "/" and "\" each move
        return ++count % 2 == 1 ? "/" : "\\";
    }

    @Override
    public Action getMove(CritterInfo info) {
        // Decides the next action based on the front neighbor
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }
}
