// Created by Ben Blair on March 8, 2024
// OpenAI Chat-GPT was used

import java.awt.*;

public class Lancer extends Critter {
    private int count = 0; // Tracks the number of moves and helps in changing the name.
    private boolean changeName = false; // Toggles the name change.
    private Direction preferredDirection = Direction.NORTH; // Keeps track of the Lancer's preferred movement direction.
    private static int colorIndex = 0; // For cycling through rainbow colors.

    public Lancer() {
        super();
    }

    @Override
    public String toString() {
        if (count % 10 == 0) {
            changeName = !changeName;
        }
        count++;
        return changeName ? "0_0" : "-_-";
    }

    @Override
    public Color getColor() {
        Color[] rainbowColors = {
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
                Color.BLUE, new Color(75, 0, 130), // Indigo
                new Color(238, 130, 238) // Violet
        };
        Color currentColor = rainbowColors[colorIndex];
        colorIndex = (colorIndex + 1) % rainbowColors.length;
        return currentColor;
    }

    @Override
    public Action getMove(CritterInfo info) {
        // Implementing a simple strategy that tries to infect if possible, otherwise moves or turns based on surroundings
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return adjustDirectionBasedOnSurroundings(info);
        }
    }

    private Action adjustDirectionBasedOnSurroundings(CritterInfo info) {
        // Prioritize turning towards empty spaces or towards enemies
        if (info.getRight() == Neighbor.OTHER) {
            preferredDirection = turnDirection(preferredDirection, false); // Adjust to face the enemy
            return Action.RIGHT;
        } else if (info.getLeft() == Neighbor.OTHER) {
            preferredDirection = turnDirection(preferredDirection, true);
            return Action.LEFT;
        } else if (info.getBack() == Neighbor.OTHER) {
            // In a situation where the enemy is behind, turning around might not be immediate, but preference is updated
            preferredDirection = turnDirection(preferredDirection, true); // Arbitrary choice, could also choose false
        }

        // If surrounded by walls or critters of the same type, choose a turn direction to possibly find an escape
        if (info.getRight() == Neighbor.WALL || info.getRight() == Neighbor.SAME) {
            return Action.LEFT;
        } else if (info.getLeft() == Neighbor.WALL || info.getLeft() == Neighbor.SAME) {
            return Action.RIGHT;
        }

        // If no better option, hop if possible, otherwise turn left as a default action
        return info.getFront() == Neighbor.EMPTY ? Action.HOP : Action.LEFT;
    }

    private Direction turnDirection(Direction current, boolean isLeftTurn) {
        switch (current) {
            case NORTH: return isLeftTurn ? Direction.WEST : Direction.EAST;
            case SOUTH: return isLeftTurn ? Direction.EAST : Direction.WEST;
            case EAST: return isLeftTurn ? Direction.NORTH : Direction.SOUTH;
            case WEST: return isLeftTurn ? Direction.SOUTH : Direction.NORTH;
            default: return current; // Should never be reached
        }
    }
}
