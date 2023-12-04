package JUnit;

import nye.hu.progTech.Map.Load;
import nye.hu.progTech.Map.World;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LoadTest {

    @Test
    public void testTurnLeft() {
        Load load = new Load(10); // Create a Load object with a size of 10 or appropriate size

        // Test turning left from each direction
        load.setDirection('N');
        load.turnLeftLoad();
        assertEquals('W', load.getDirection());

        load.setDirection('W');
        load.turnLeftLoad();
        assertEquals('S', load.getDirection());

        load.setDirection('S');
        load.turnLeftLoad();
        assertEquals('E', load.getDirection());

        load.setDirection('E');
        load.turnLeftLoad();
        assertEquals('N', load.getDirection());
    }

    @Test
    public void testTurnRight() {
        Load load = new Load(10); // Create a Load object with a size of 10 or appropriate size

        // Test turning left from each direction
        load.setDirection('N');
        load.turnRightLoad();
        assertEquals('E', load.getDirection());

        load.setDirection('E');
        load.turnRightLoad();
        assertEquals('S', load.getDirection());

        load.setDirection('S');
        load.turnRightLoad();
        assertEquals('W', load.getDirection());

        load.setDirection('W');
        load.turnRightLoad();
        assertEquals('N', load.getDirection());
    }

    @Test
    public void testWalkN() {
        Load load = new Load(5); // Create a Load object with a size of 5 or appropriate size

        // Set up the initial conditions
        load.setHeroX(2); // Assuming starting X position is 2
        load.setHeroY(2); // Assuming starting Y position is 2
        load.setDirection('N'); // Assuming initial direction is North

        // Simulate a walk in the North direction
        load.walk();

        // Ensure that heroX and heroY have changed correctly after walking
        assertEquals(1, load.getHeroX()); // Assert the new X position after walking North
        assertEquals(2, load.getHeroY()); // Assert the Y position remains the same after walking North
    }

    @Test
    public void testWalkE() {
        Load load = new Load(5); // Create a Load object with a size of 5 or appropriate size

        // Set up the initial conditions
        load.setHeroX(2); // Assuming starting X position is 2
        load.setHeroY(2); // Assuming starting Y position is 2
        load.setDirection('E'); // Assuming initial direction is North

        // Simulate a walk in the North direction
        load.walk();

        // Ensure that heroX and heroY have changed correctly after walking
        assertEquals(2, load.getHeroX()); // Assert the new X position after walking North
        assertEquals(3, load.getHeroY()); // Assert the Y position remains the same after walking North
    }

    @Test
    public void testWalkW() {
        Load load = new Load(5); // Create a Load object with a size of 5 or appropriate size

        // Set up the initial conditions
        load.setHeroX(2); // Assuming starting X position is 2
        load.setHeroY(2); // Assuming starting Y position is 2
        load.setDirection('W'); // Assuming initial direction is North

        // Simulate a walk in the North direction
        load.walk();

        // Ensure that heroX and heroY have changed correctly after walking
        assertEquals(2, load.getHeroX()); // Assert the new X position after walking North
        assertEquals(1, load.getHeroY()); // Assert the Y position remains the same after walking North
    }

    @Test
    public void testWalkS() {
        Load load = new Load(5); // Create a Load object with a size of 5 or appropriate size

        // Set up the initial conditions
        load.setHeroX(2); // Assuming starting X position is 2
        load.setHeroY(2); // Assuming starting Y position is 2
        load.setDirection('S'); // Assuming initial direction is North

        // Simulate a walk in the North direction
        load.walk();

        // Ensure that heroX and heroY have changed correctly after walking
        assertEquals(3, load.getHeroX()); // Assert the new X position after walking North
        assertEquals(2, load.getHeroY()); // Assert the Y position remains the same after walking North
    }

    @Test
    public void testShootHitWumpus() {
        Load load = new Load(5); // Create a Load object with a size of 5 or appropriate size
        load.setHeroX(2); // Assuming starting X position is 2
        load.setHeroY(2); // Assuming starting Y position is 2
        load.setDirection('N'); // Assuming initial direction is North
        load.setArrows(1); // Set arrows to 1 for the test

        // Set up the world with a Wumpus at the designated position
        char[][] worldWithWumpus = {
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'U', ' ', ' '}, // Position of the Wumpus ('U')
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        };
        load.setLoadedWorld(worldWithWumpus);

        // Perform the shoot action
        load.shoot();

        // Check if the Wumpus was hit (i.e., the 'U' was removed)
        assertEquals(' ', load.getLoadedWorld()[1][2]); // Assuming the Wumpus was at (1, 2)
    }

    @Test
    public void testShootMiss() {
        Load load = new Load(5); // Create a Load object with a size of 5 or appropriate size
        load.setHeroX(2); // Assuming starting X position is 2
        load.setHeroY(2); // Assuming starting Y position is 2
        load.setDirection('N'); // Assuming initial direction is North
        load.setArrows(1); // Set arrows to 1 for the test

        // Set up the world without any target (no Wumpus)
        char[][] emptyWorld = {
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}, // No Wumpus in this world
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        };
        load.setLoadedWorld(emptyWorld);

        // Perform the shoot action
        load.shoot();

        // Check if the target was empty (no Wumpus)
        assertEquals(' ', load.getLoadedWorld()[1][2]); // Assuming the target position is (1, 2)
    }

    @Test
    public void testLoadWorld() {
        // Create an instance of the Load class
        Load load = new Load(7);

        // Call the loadWorld method
        load.loadWorld();

        // Retrieve the loaded world
        char[][] loadedWorld = load.getLoadedWorld();

        // Perform assertions on the loaded world data
        // Example assertions to ensure the loadedWorld is not null and has the expected size or content
        assertNotNull(loadedWorld);
        // Add more assertions based on your specific requirements
    }
    @Test
    public void testGetSize() {
        int expectedSize = 7; // Set your expected size here
        Load load = new Load(expectedSize);

        int actualSize = load.getSize();

        assertEquals(expectedSize, actualSize);
    }





}
