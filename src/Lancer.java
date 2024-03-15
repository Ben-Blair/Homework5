// Created by Ben Blair on March 8, 2024
// OpenAI Chat-GPT was used

import java.awt.*;

public class Lancer extends Critter {
    private int count = 0; // Tracks the number of moves and helps in changing the name.
    private boolean changeName = false; // Toggles the name change.
    private Direction preferredDirection = Direction.NORTH; // Keeps track of the Lancer's preferred movement direction.
    private static int colorIndex = 0; // For cycling through rainbow colors.
    private boolean isInfecting = false; // Indicates if currently infecting
    private int infectingTicks = 0; // Counter for infecting ticks

    public Lancer() {
        super();
    }

    @Override
    public String toString() {
        // Name changes based on infecting status or toggled change
        if (isInfecting) {
            return "I"; // Temporary name for infecting state
        }
        // Change name on every 10th move
        if (count % 10 == 0) {
            changeName = !changeName;
        }
        count++;
        return changeName ? "0_0" : "";
    }

    @Override
    public Color getColor() {
        // Cycle through rainbow colors
        Color[] rainbowColors = {
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
                Color.BLUE, new Color(75, 0, 130), // Indigo
                new Color(238, 130, 238) // Violet
        };
        Color currentColor = rainbowColors[colorIndex % rainbowColors.length];
        colorIndex++;
        return currentColor;
    }

    @Override
    public Action getMove(CritterInfo info) {
        // Handle the infectingTicks decrement and state reset
        if (isInfecting) {
            infectingTicks--;
            if (infectingTicks <= 0) {
                isInfecting = false;
            }
        }

        // Decide on action based on neighbor's state
        if (info.getFront() == Neighbor.OTHER) {
            isInfecting = true;
            infectingTicks = 2; // Set for a very short duration
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return adjustDirectionBasedOnSurroundings(info);
        }
    }

    private Action adjustDirectionBasedOnSurroundings(CritterInfo info) {
        // Turn towards enemy or adjust direction based on surroundings
        if (info.getRight() == Neighbor.OTHER) {
            preferredDirection = turnDirection(preferredDirection, false);
            return Action.RIGHT;
        } else if (info.getLeft() == Neighbor.OTHER) {
            preferredDirection = turnDirection(preferredDirection, true);
            return Action.LEFT;
        } else if (info.getBack() == Neighbor.OTHER) {
            preferredDirection = turnDirection(preferredDirection, true);
        }

        // If blocked, choose an action to potentially unblock
        if (info.getRight() == Neighbor.WALL || info.getRight() == Neighbor.SAME) {
            return Action.LEFT;
        } else if (info.getLeft() == Neighbor.WALL || info.getLeft() == Neighbor.SAME) {
            return Action.RIGHT;
        }

        // Default action changed to turning towards the opposite direction
        if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            Direction oppositeDirection = getOppositeDirection(preferredDirection);
            preferredDirection = oppositeDirection;
            return turnDirection(preferredDirection, true) == oppositeDirection ? Action.LEFT : Action.RIGHT;
        }
    }

    private Direction turnDirection(Direction current, boolean isLeftTurn) {
        // Determine new direction based on current direction and turn direction
        switch (current) {
            case NORTH: return isLeftTurn ? Direction.WEST : Direction.EAST;
            case SOUTH: return isLeftTurn ? Direction.EAST : Direction.WEST;
            case EAST: return isLeftTurn ? Direction.NORTH : Direction.SOUTH;
            case WEST: return isLeftTurn ? Direction.SOUTH : Direction.NORTH;
            default: return current; // Fallback, should not happen
        }
    }

    private Direction getOppositeDirection(Direction current) {
        // Return the opposite direction of the current one
        switch (current) {
            case NORTH: return Direction.SOUTH;
            case SOUTH: return Direction.NORTH;
            case EAST: return Direction.WEST;
            case WEST: return Direction.EAST;
            default: return current; // Fallback, should not happen
        }
    }
}

