import org.example.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestRobot {

    private Robot robot;

    @BeforeEach
    void setUp() {
        robot = new Robot();
    }

    // TEST: Turn RIGHT
    @Test
    void testTurnRight() {
        robot.place(2, 2, "NORTH");
        robot.right();
        assertEquals(1, robot.facing);  // EAST is 1
    }

    // TEST: Turn LEFT
    @Test
    void testTurnLeft() {
        robot.place(2, 2, "WEST");
        robot.left();
        assertEquals(2, robot.facing);  // WEST is 3
    }

    // TEST: Valid PLACE command
    @Test
    void testPlaceValidCommand() {
        robot.place(0, 0, "NORTH");
        assertTrue(robot.isPlaced());
        assertEquals(0, robot.x);
        assertEquals(0, robot.y);
    }

    // TEST: Invalid PLACE command (out of bounds)
    @Test
    void testPlaceInvalidCommandOutOfBounds() {
        robot.place(-1, 2, "WEST");
        assertFalse(robot.isPlaced());

        robot.place(5, 2, "EAST");
        assertFalse(robot.isPlaced());

        robot.place(2, -1, "SOUTH");
        assertFalse(robot.isPlaced());

    }

    // TEST: Valid MOVE command
    @Test
    void testMoveValid() {
        robot.place(0, 0, "NORTH");
        robot.move();
        robot.right();
        robot.move();
        assertEquals(1, robot.x);
        assertEquals(1, robot.y);  // Should move north
    }

    // TEST: MOVE command that moves out of bounds
    @Test
    void testMoveInvalid() {
        robot.place(0, 0, "WEST");
        robot.move();
        assertEquals(0, robot.x);  // Still at x = 0
        assertEquals(0, robot.y);  // Still at y = 0
    }

    // TEST: Report after a successful move
    @Test
    void testReport() {
        robot.place(0, 0, "NORTH");
        robot.move();
        String report = robot.report();
        assertEquals("0,1,NORTH", report);  // Robot moved to 0, 1 facing NORTH
    }

    // TEST: Report and Output logic
    @Test
    void testReportOutputSuccess() {
        robot.place(1, 1, "EAST");
        String report = robot.report();
        String expectedOutput = "Output: 1,1,EAST";
        assertEquals(expectedOutput, "Output: " + report);  // Validate Output

        // Report should be successful and match the expected output
        String result = "Output: " + report;
        assertEquals(result, "Output: 1,1,EAST");
    }

    // TEST: Report and Output logic after invalid placement
    @Test
    void testReportOutputFail() {
        robot.place(2, 2, "SOUTH");
        String report = robot.report();
        String expectedOutput = "Output: 2,2,EAST";  // An incorrect expected output
        assertNotEquals(expectedOutput, "Output: " + report);  // Should fail
    }
}