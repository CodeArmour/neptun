package nye.hu.progTech;

import java.util.Random;
import java.util.Scanner;

public class World {
    private char[][] world;
    private int size;
    private int numWumpus;
    private int numStones;
    private Hero hero;

    public World(int size) {

        this.size=size;
        this.world = new char[size][size];
        this.numWumpus = 0;
        this.numStones = 0;
        //this.hero = null;

        initializeWorld();



        // Initialize the hero and pass the numWumpus value, rowNumber, and columnLabel
        int x = hero.getHeroX(); // Set the hero's initial row position to the middle of the grid
        int y = hero.getHeroY();     // Set the hero's initial column position to 'A'
        hero = new Hero(x, y, numWumpus);

    }

    public void setWorldDifficulty(String difficulty) {
        if ("Easy".equalsIgnoreCase(difficulty)) {
            size = getRandomNumberEasy();
            initializeWorld();

        } else if ("Normal".equalsIgnoreCase(difficulty)) {
            size = getRandomNumberNormal();
            initializeWorld();

        } else if ("Hard".equalsIgnoreCase(difficulty)) {
            size = getRandomNumberHard();

        }
    }


    public void printInitialInfo() {
        System.out.println("=> Initial Hero Position: Row " + hero.getHeroX() + ", Column " + (char) hero.getHeroY());
        System.out.println("=> Initial Direction: " + hero.getDirection());
        System.out.println("=> World Size: " + size + "x" + size);
        System.out.println("=> Arrows number "+ hero.getArrows());
    }

    public void goldstatus(){
        if(hero.isHasGold()){
            System.out.println("The hero have the gold");
        }else {
            System.out.println("no gold in the bag");
        }
    }





    public Hero getHero() {
        return hero;
    }

    public char[][] getWorld() {
        return world;
    }

    public int getSize() {
        return size;
    }



    private void initializeWorld() {
        // Check the size of the world and determine the number of U (Wumpus) and S (Stones) accordingly.
        if (size <= 8) {
            numWumpus = 1;
            numStones = 3;
        } else if (size >= 9 && size <= 14) {
            numWumpus = 2;
            numStones = 4;
        } else if (size >= 15) {
            numWumpus = 3;
            numStones = 6;
        }



        // Fill the grid with ' ' (empty spaces) initially.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = ' ';
            }
        }

        // Randomly place U (Wumpus) and S (Stones) on the grid.
        placeCharacters('U', numWumpus);
        placeCharacters('S', numStones);

        // Randomly place H (Hero) on the grid.
        placeCharacterH('H');

        // Randomly place G (Gold) on the grid.
        placeCharacters('G',1);
    }

    public int getRandomNumberEasy() {
        Random random = new Random();
        int randomNumber = random.nextInt(5) + 3; // Generates a random number between 0 (inclusive) and 5 (exclusive), then adds 3 to shift the range to [3, 8].
        return randomNumber;
    }

    public int getRandomNumberNormal() {
        Random random = new Random();
        int randomNumber = random.nextInt(9) + 5; // Generates a random number between 0 (inclusive) and 5 (exclusive), then adds 3 to shift the range to [3, 8].
        return randomNumber;
    }

    public int getRandomNumberHard() {
        Random random = new Random();
        int randomNumber = random.nextInt(15) + 5; // Generates a random number between 0 (inclusive) and 5 (exclusive), then adds 3 to shift the range to [3, 8].
        return randomNumber;
    }

    private void placeCharacters(char character, int count) {
        Random random = new Random();
        int remainingCount = count;

        while (remainingCount > 0) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);

            // Check if the chosen cell is empty, then place the character.
            if (world[x][y] == ' ') {
                world[x][y] = character;
                remainingCount--;
            }
        }
    }

    private void placeCharacterH(char character) {
        Random random = new Random();
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        char columnLabel = (char) ('a' + y); // Convert column index to letter
        int rowNumber = x + 1; // Convert row index to number
        world[x][y] = character;
        hero = new Hero(rowNumber, columnLabel);
    }


    public  void printWorld() {

            for (int i = -1; i <= size  ; i++) {
                for (int j = -1; j <= size; j++) {
                    char borderChar = 'W'; // Default border character

                    if (i >= 0 && i < size && j >= 0 && j < size) {
                        borderChar = world[i][j];
                    }

                    System.out.print(borderChar + " ");
                }
                System.out.println();
            }

    }

    public void play() {


        Scanner scanner = new Scanner(System.in);
        String input;

        boolean gameOver = false;


        while (!gameOver) {
            printWorld();
            System.out.println("Enter a command: (turn left, turn right, walk, shoot, give up)");
            input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "turn left":
                    hero.turnLeft();
                    break;
                case "turn right":
                    hero.turnRight();
                    break;
                case "walk":
                    hero.walk(this);
                    break;
                case "shoot":
                    hero.shoot(this);
                    break;
                case "give up":
                    gameOver = true;
                    break;
                default:
                    System.out.println("Invalid command. Please use 'turn left', 'turn right', 'walk', or 'give up'.");
            }

            if (!hero.isAlive()) {
                System.out.println("Game over! The hero is dead.");
                System.out.println("Do you want to play again? (yes or no)");
                input = scanner.nextLine().toLowerCase();
                if (input.equals("yes")) {
                    // Restart the game
                    World world = new World(size);
                    hero = world.getHero();
                } else {
                    gameOver = true;
                }
            }
        }
    }



}
