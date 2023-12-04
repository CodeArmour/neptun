package nye.hu.progTech;

import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        final int WORLD_SIZE = 5;

        char[][] world = new char[WORLD_SIZE][WORLD_SIZE];
        int heroX, heroY, goldX, goldY, wumpusX, wumpusY;
        boolean hasGold = false;
        boolean isAlive = true;
        String direction = "N"; // Initial direction (North)

        // Initialize the game world
        initializeWorld(world, WORLD_SIZE);

        // Place the hero, gold, and wumpus in random locations
        Random random = new Random();
        heroX = random.nextInt(WORLD_SIZE);
        heroY = random.nextInt(WORLD_SIZE);
        goldX = random.nextInt(WORLD_SIZE);
        goldY = random.nextInt(WORLD_SIZE);
        wumpusX = random.nextInt(WORLD_SIZE);
        wumpusY = random.nextInt(WORLD_SIZE);

        // Place some stones in random locations
        int numStones = 5;
        for (int i = 0; i < numStones; i++) {
            int stoneX, stoneY;
            do {
                stoneX = random.nextInt(WORLD_SIZE);
                stoneY = random.nextInt(WORLD_SIZE);
            } while (world[stoneY][stoneX] != ' ');
            world[stoneY][stoneX] = 'S';
        }

        // Main game loop
        while (isAlive) {
            printWorld(world, heroX, heroY);

            if (hasGold && heroX == 0 && heroY == 0) {
                System.out.println("Congratulations! You have won the game!");
                break;
            }

            String action = getUserInput("Enter action (pickup, walk, turn left, turn right, shoot): ").toLowerCase();

            if (action.equals("pickup")) {
                pickupGold(heroX, heroY, goldX, goldY, hasGold, world);
            } else if (action.equals("walk")) {
                int[] newCoords = getNewPosition(heroX, heroY, direction, world);
                heroX = newCoords[0];
                heroY = newCoords[1];
            } else if (action.equals("turn left")) {
                direction = turnLeft(direction);
            } else if (action.equals("turn right")) {
                direction = turnRight(direction);
            } else if (action.equals("shoot")) {
                shootWumpus(heroX, heroY, wumpusX, wumpusY, world);
            } else {
                System.out.println("Invalid action. Try again.");
            }

            if (world[heroY][heroX] == 'S') {
                System.out.println("Be careful! You've run into a stone.");
            }

            if (heroX == wumpusX && heroY == wumpusY) {
                isAlive = false;
            }
        }

        printWorld(world, heroX, heroY);
        if (!isAlive) {
            System.out.println("Game Over! You were eaten by the wumpus.");
        }
    }

    // Initialize the game world
    public static void initializeWorld(char[][] world, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = ' ';
            }
        }
    }

    // Print the game world
    public static void printWorld(char[][] world, int heroX, int heroY) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (i == heroY && j == heroX) {
                    System.out.print("H ");
                } else {
                    System.out.print(world[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Get user input
    public static String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }

    // Perform the pickup action
    public static void pickupGold(int heroX, int heroY, int goldX, int goldY, boolean hasGold, char[][] world) {
        if (heroX == goldX && heroY == goldY && !hasGold) {
            world[goldY][goldX] = ' ';
            System.out.println("Gold picked up!");
            hasGold = true;
        } else if (hasGold) {
            System.out.println("You already have the gold.");
        } else {
            System.out.println("No gold here.");
        }
    }

    // Perform the walk action
    public static int[] getNewPosition(int heroX, int heroY, String direction, char[][] world) {
        int[] newCoords = { heroX, heroY };
        if (direction.equals("N") && heroY > 0) {
            newCoords[1] = heroY - 1;
        } else if (direction.equals("S") && heroY < world.length - 1) {
            newCoords[1] = heroY + 1;
        } else if (direction.equals("E") && heroX < world[0].length - 1) {
            newCoords[0] = heroX + 1;
        } else if (direction.equals("W") && heroX > 0) {
            newCoords[0] = heroX - 1;
        } else {
            System.out.println("Invalid move. Try again.");
        }
        world[heroY][heroX] = ' ';
        world[newCoords[1]][newCoords[0]] = 'H';
        return newCoords;
    }

    // Perform the shoot action
    public static void shootWumpus(int heroX, int heroY, int wumpusX, int wumpusY, char[][] world) {
        if ((heroX == wumpusX && Math.abs(heroY - wumpusY) <= 1) || (heroY == wumpusY && Math.abs(heroX - wumpusX) <= 1)) {
            world[wumpusY][wumpusX] = ' ';
            System.out.println("Wumpus killed!");
        } else {
            System.out.println("No wumpus in sight.");
        }
    }

    // Turn the hero left
    public static String turnLeft(String direction) {
        switch (direction) {
            case "N":
                return "W";
            case "W":
                return "S";
            case "S":
                return "E";
            case "E":
                return "N";
            default:
                return direction;
        }
    }

    // Turn the hero right
    public static String turnRight(String direction) {
        switch (direction) {
            case "N":
                return "E";
            case "E":
                return "S";
            case "S":
                return "W";
            case "W":
                return "N";
            default:
                return direction;
        }
    }
}