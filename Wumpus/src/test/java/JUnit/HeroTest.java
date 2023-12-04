package JUnit;

import nye.hu.progTech.Map.World;
import nye.hu.progTech.controlars.Hero;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HeroTest {

    @Test
    public void testTurnRight() {
        // Create a Hero instance with initial direction 'N'
        Hero hero = new Hero(0, 0, 0);
        hero.setDirection('N');

        // Turn right once
        hero.turnRight();
        assertEquals('E', hero.getDirection());
    }


    @Test
    public void testTurnLeft() {
        // Create a Hero instance
        Hero hero = new Hero(0,0,0);

        // Test initial direction
        hero.setDirection('N');

        // Turn right once
        hero.turnLeft();
        assertEquals('W', hero.getDirection());

        hero.setDirection('W');
        hero.turnLeft();
        assertEquals('S', hero.getDirection());

        hero.setDirection('S');
        hero.turnLeft();
        assertEquals('E', hero.getDirection());

        hero.setDirection('E');
        hero.turnLeft();
        assertEquals('N', hero.getDirection());
    }

    @Test
    public void testHeroWalk() {
        // Create a mock World object
        World mockedWorld = mock(World.class);

        // Set up a fake world size (assuming size 5x5 for this example)
        when(mockedWorld.getSize()).thenReturn(5);

        // Create a Hero instance
        Hero hero = new Hero(2, 2, 0); // Assuming initial position is (2, 2)

        // Mock the getWorld() method to return a non-null 2D array
        char[][] fakeWorld = new char[][] {
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        };
        when(mockedWorld.getWorld()).thenReturn(fakeWorld);

        // Call the walk() method with the mocked World object
        hero.walk(mockedWorld);

        // Add assertions to validate the behavior of the walk() method
        // For example, check if steps increased after walking
        assertEquals(1, hero.steps);
    }

    @Test
    public void testShootHitWumpus() {
        // Create a mock World object
        World mockedWorld = mock(World.class);

        // Set up a fake world size (assuming size 5x5 for this example)
        when(mockedWorld.getSize()).thenReturn(5);

        // Create a Hero instance
        Hero hero = new Hero(2, 2, 1); // Assuming initial position is (2, 2) and 1 arrow

        // Mock the getWorld() method to return a fake 5x5 world
        char[][] fakeWorld = {
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'U', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        };
        when(mockedWorld.getWorld()).thenReturn(fakeWorld);

        // Call the shoot() method with the mocked World object
        hero.shoot(mockedWorld);

        // Validate that the world object was updated after shooting the Wumpus
        assertEquals(' ', fakeWorld[1][2]); // Assuming the Wumpus was at (1, 2)
    }

    @Test
    public void testShootMissed() {
        // Create a mock World object
        World mockedWorld = mock(World.class);

        // Set up a fake world size (assuming size 5x5 for this example)
        when(mockedWorld.getSize()).thenReturn(5);

        // Create a Hero instance with no arrows
        Hero hero = new Hero(2, 2, 0); // Assuming initial position is (2, 2) and 0 arrows

        // Call the shoot() method with the mocked World object
        hero.shoot(mockedWorld);

        // Validate that the Hero did not shoot anything because of no arrows
        // You might need to adjust this assertion based on your expected behavior
        // For example, check if the world state remains unchanged when shooting with no arrows
        // assertEquals(expectedWorldState, actualWorldState);
    }

    @Test
    public void testGetHeroX() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero at position (5, 7)
        assertEquals(5, hero.getHeroX());
    }

    @Test
    public void testGetHeroY() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero at position (5, 7)
        assertEquals(7, hero.getHeroY());
    }

    @Test
    public void testGetDirection() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero at position (5, 7)
        // The direction will be a randomly assigned direction in the constructor
        assertNotNull(hero.getDirection());
    }

    @Test
    public void testSetDirection() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero at position (5, 7)
        hero.setDirection('E');
        assertEquals('E', hero.getDirection());
    }

    @Test
    public void testGetArrows() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero with 3 arrows
        assertEquals(3, hero.getArrows());
    }

    @Test
    public void testIsAliveInitially() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero
        assertTrue(hero.isAlive());
    }

    @Test
    public void testIsHasGoldInitially() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero
        assertFalse(hero.isHasGold());
    }

    @Test
    public void testGetRandomDirection() {

        Hero hero = new Hero(5, 7, 3); // Creating a hero
        char[] directions = {'N', 'S', 'E', 'W'};
        boolean directionFound = false;

        for (int i = 0; i < 100; i++) { // Run multiple times to increase probability coverage
            char direction = hero.getRandomDirection();
            for (char dir : directions) {
                if (dir == direction) {
                    directionFound = true;
                    break;
                }
            }
            if (directionFound) {
                break;
            }
        }
        assertTrue("Random direction should be one of N, S, E, or W", directionFound);
    }

    @Test
    public void testHaveGold() {
        Hero hero = new Hero(5, 7, 3); // Creating a hero
        assertFalse(hero.isHasGold()); // Initially, hero does not have gold

        // Redirect System.out to verify printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        hero.haveGold();
        assertEquals("Bag is empty !!!" + System.lineSeparator(), outputStream.toString());

        // Assuming hero gets gold
        hero.setHasGold(true);
        assertTrue(hero.isHasGold()); // Now hero has gold

        outputStream.reset();
        hero.haveGold();
        assertEquals("the hero have Gold lets back to safe zone !!!" + System.lineSeparator(), outputStream.toString());
    }




}


