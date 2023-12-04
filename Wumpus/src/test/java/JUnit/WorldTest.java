package JUnit;

import nye.hu.progTech.Map.World;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class WorldTest {

    private World world;

    @Before
    public void setUp() {
        world = new World(10); // Assuming a world size of 10x10
    }

    @Test
    public void testWorldInitialization() {
        assertNotNull(world.getWorld());
        assertEquals(10, world.getSize());
    }

    @Test
    public void testGetRandomNumberHard() {
        World world = new World(10); // Assuming a world size of 10x10

        // Call getRandomNumberHard multiple times and check if the generated numbers are within the expected range
        for (int i = 0; i < 100; i++) {
            int randomNumber = world.getRandomNumberHard();
            assertTrue(randomNumber >= 4 && randomNumber <= 14);
        }
    }

    @Test
    public void testGetRandomNumberNormal() {
        World world = new World(10); // Assuming a world size of 10x10

        int min = 9;
        int max = 14;

        // Call getRandomNumberNormal multiple times and check if the generated numbers are within the expected range
        for (int i = 0; i < 100; i++) {
            int randomNumber = world.getRandomNumberNormal();
            assertTrue(randomNumber >= min && randomNumber <= max);
        }
    }

    @Test
    public void testGetRandomNumberEasy() {
        World world = new World(10); // Assuming a world size of 10x10

        int min = 5;
        int max = 8;

        // Call getRandomNumberEasy multiple times and check if the generated numbers are within the expected range
        for (int i = 0; i < 100; i++) {
            int randomNumber = world.getRandomNumberEasy();
            assertTrue(randomNumber >= min && randomNumber <= max);
        }
    }


    @Test
    public void testPlaceCharacterG() {
        World world = new World(10); // Assuming a world size of 10x10

        char character = 'G';
        int count = 3; // Assuming you want to place 3 'G' characters

        world.initializeWorld(); // Initialize the world (if required)

        // Call placeCharacterG method
        world.placeCharacterG(character, count);

        char[][] grid = world.getWorld();

        int placedCount = 0;
        // Count the number of 'G' characters placed in the world
        for (int i = 0; i < world.getSize(); i++) {
            for (int j = 0; j < world.getSize(); j++) {
                if (grid[i][j] == character) {
                    placedCount++;
                }
            }
        }

        assertEquals(count+1, placedCount); // Check if the expected count matches the actually placed 'G' characters
    }

    @Test
    public void testPlaceCharacterS1() {
        World world = new World(10); // Assuming a world size of 10x10
        char character = '1';

        world.setxStone1(3);
        world.setyStone1(3);

        world.initializeWorld(); // Initialize the world (if required)

        // Call placeCharacterS1 method
        world.placeCharacterS1(character);

        char[][] grid = world.getWorld();

        // Check if the character '1' is placed at the coordinates xStone1 and yStone1
        assertEquals(character, grid[world.getxStone1()][world.getyStone1()]);
    }

    @Test
    public void testSetHero() {
        // Create a world with a specific size for testing purposes
        int size = 5;
        World world = new World(size);

        // Set up initial coordinates
        int initialX = 2;
        int initialY = 2;
        world.setHero(initialX, initialY, 0, 0);

        // Assert that 'H' is set at the new coordinates and old coordinates are empty
        assertEquals('H', world.getWorld()[initialX][initialY]);
        assertEquals(' ', world.getWorld()[0][0]); // Assuming (0, 0) was the old coordinates
    }

    @Test
    public void testPrintWorld() {
        // Set up output stream redirection to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a world and set up initial conditions for testing
        int size = 5;
        World world = new World(size);
        world.setHero(2, 2, 0, 0); // Assuming initial hero position is (2, 2)

        // Call the method to be tested
        world.printWorld();

        // Get the printed output from the redirected stream
        String printedOutput = outputStream.toString();

        // Verify the printed output contains the expected elements
        assertTrue("Hero not found in printed output", printedOutput.contains("H"));
        assertTrue("Border character not found in printed output", printedOutput.contains("W"));
        assertTrue("Direction not found in printed output", printedOutput.contains("Current direction:"));
    }

    @Test
    public void testNumWumpusBasedOnSize() {
        // Test for a size <= 8
        World smallWorld = new World(8);
        assertEquals(1, smallWorld.getNumWumpus());

        // Test for sizes between 9 and 14 (inclusive)
        World mediumWorld = new World(12);
        assertEquals(2, mediumWorld.getNumWumpus());

        // Test for a size >= 15
        World largeWorld = new World(20);
        assertEquals(3, largeWorld.getNumWumpus());
    }


}
