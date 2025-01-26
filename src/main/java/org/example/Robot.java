package org.example;

public class Robot {
    public static final int MAP_SIZE = 5; // fixed map size (5x5)
    private static final int[][] map = new int[MAP_SIZE][MAP_SIZE];
    public Integer x = null;
    public Integer y = null;
    public Integer facing = null; // Direction robot is facing
    public boolean placeTrue = false; // To track if the robot has been successfully placed

    private static final int[][] directions = {
            {1, 0},  // NORTH
            {0, 1},  // EAST
            {-1, 0}, // SOUTH
            {0, -1}  // WEST
    };

    private static final String[] directionNames = {"NORTH", "EAST", "SOUTH", "WEST"};

    public Robot() {
        resetMap();
    }
    // Initialize map with empty positions (0s)
    public void resetMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = 0;
            }
        }
    }

    // Place the robot on the map with x, y coordinates and facing direction
    public void place(int newX, int newY, String direction) {
        if (newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE) {
            // Check for valid direction
            for (int i = 0; i < directionNames.length; i++) {
                if (directionNames[i].equals(direction)) {
                    x = newX;
                    y = newY;
                    facing = i;
                    map[MAP_SIZE - 1 - y][x] = 1; // Mark the robot's position
                    placeTrue = true;
                    return;
                }
            }
        }
        // If any conditions are invalid, set robot as not placed
        placeTrue = false;
    }

    // Check if the robot is placed on the map used for test
    public boolean isPlaced() {
        return placeTrue;
    }

    // Move the robot based on its facing direction
    public void move() {
        if (placeTrue) {
            int newX = x + directions[facing][1];
            int newY = y + directions[facing][0];

            if (newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE) {
                x = newX;
                y = newY;
                map[MAP_SIZE - 1 - y][x] = 1;
            }
        }
    }

    // Turn the robot to the left (90 degrees counterclockwise)
    public void left() {
        if (placeTrue) {
            facing = (facing + 3) % 4; // Turn left (counterclockwise)
        }
    }

    // Turn the robot to the right (90 degrees clockwise)
    public void right() {
        if (placeTrue) {
            facing = (facing + 1) % 4; // Turn right (clockwise)
        }
    }

    // Get the current report in the format: "x,y,direction"
    public String report() {
        if (placeTrue) {
            return x + "," + y + "," + directionNames[facing];
        } else {
            return "";
        }
    }

    // Print the current map
    public void printMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}