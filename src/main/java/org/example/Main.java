package org.example;

import java.io.*;


//Main class to read commands from a file, process them, and interact with a Robot instance.
//Commands include PLACE, MOVE, LEFT, RIGHT, REPORT and Output verification.

public class Main {
    private static final Robot robot = new Robot();  // A single robot instance
    public static void main(String[] args) {
        // Open and read the file line by line
        try (BufferedReader br = new BufferedReader(new FileReader("src/text.file"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process each line and print the result, if any
                String result = processCommand(line);
                if (!result.isEmpty()) {
                    System.out.println(result);  // Print the result after every command
                }
            }
        } catch (IOException e) {
            // Handle cases where the file is missing or unreadable
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }


    //Processes a single command from the file.
    //Splits the command into ex PLACE, MOVE and arguments.
    //Uses a switch statement to determine the action to perform.

    public static String processCommand(String line) {
        // Split into command and arguments
        String[] tokens = line.split(",", 2);
        String outputMessage = "";

        switch (tokens[0]) {
            case "PLACE":
                // Handle the PLACE command with arguments
                if (tokens.length == 2) {
                    robot.resetMap();
                    String[] params = tokens[1].split(",");
                    if (params.length == 3) {
                        try {
                            int newX = Integer.parseInt(params[0].trim());
                            int newY = Integer.parseInt(params[1].trim());
                            String direction = params[2].trim();
                            robot.place(newX, newY, direction);
                        } catch (NumberFormatException e) {
                            outputMessage = "Invalid PLACE command format: " + line;
                        }
                    } else {
                        outputMessage = "Invalid PLACE command format: " + line;
                    }
                }
                break;
            case "MOVE":
                robot.move();
                break;
            case "LEFT":
                robot.left();
                break;
            case "RIGHT":
                robot.right();
                break;
            case "REPORT":
                outputMessage = robot.report();
                break;
            default:
                if (line.startsWith("Output:") && robot.placeTrue) {
                    String expectedOutput = line.trim();
                    String actualReport = robot.report();
                    if (expectedOutput.equals("Output: " + actualReport)) {
                        outputMessage = "Report successful";
                    } else {
                        outputMessage = "Report failed";
                    }
                    robot.printMap();
                } else {
                    outputMessage = "Unknown command: " + line;
                }
                break;
        }
        return outputMessage;
    }
}