package nye.hu.progTech.Map;


import nye.hu.progTech.controlars.Hero;
import nye.hu.progTech.database.DatabaseConnection;


import javax.xml.bind.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class World {
    private char[][] world;
    private int size;
    private int numWumpus;
    private Hero hero;
    private int initialHeroX;
    private int initialHeroY;
    // Instance of the Saver class






    public World(int size) {
        this.size = size;
        this.world = new char[size][size];
        // Initialize the Saver instance
        // Initialize the world based on the provided size.
        initializeWorld();
    }


    public void setHero(int x, int y, int xold, int yold) {
        world[x][y] = 'H';
        world[xold][yold] = ' ';
    }

    public char[][] getWorld() {
        return world;
    }

    public int getSize() {
        return size;
    }


    public void setxStone1(int xStone1) {
        this.xStone1 = xStone1;
    }

    public void setyStone1(int yStone1) {
        this.yStone1 = yStone1;
    }

    public int getxStone1() {
        return xStone1;
    }

    public int getyStone1() {
        return yStone1;
    }

    public int getNumWumpus() {
        return numWumpus;
    }

    public void initializeWorld() {
        // Determine the number of Wumpus and Stones based on the size.
        if (size <= 8) {
            numWumpus = 1;

        } else if (size >= 9 && size <= 14) {
            numWumpus = 2;

        } else if (size >= 15) {
            numWumpus = 3;

        }

        // Create the world grid and populate it with empty spaces.
        world = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = ' ';
            }
        }

        // Place characters in the world.
        placeCharacterU('U', numWumpus);
        placeCharacterS1('S');
        placeCharacterS2('S');
        placeCharacterS3('S');
        placeCharacterG('G', 1);
        placeCharacterH('H');
    }

    public int getRandomNumberEasy() {

        Random random = new Random();
        int min = 5;
        int max = 8;

        int randomNum = random.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public int getRandomNumberNormal() {
        Random random = new Random();
        int min = 9;
        int max = 14;
        int randomnum = random.nextInt((max - min) + 1) + min;
        return randomnum;

    }

    public int getRandomNumberHard() {
        Random random = new Random();
        return random.nextInt(15 - 4) + 4;
    }

    public int xGold;
    public int yGold;
    public void placeCharacterG(char character, int count) {
        Random random = new Random();
        int remainingCount = count;

        while (remainingCount > 0) {
            xGold = random.nextInt(size);
            yGold = random.nextInt(size);

            if (world[xGold][yGold] == ' ') {
                world[xGold][yGold] = character;
                remainingCount--;
            }
        }
    }


    public int xStone1;
    public int yStone1;
    public void placeCharacterS1(char character) {
        Random random = new Random();

        xStone1 = random.nextInt(size);
        yStone1 = random.nextInt(size);

        if (world[xStone1][yStone1] == ' ') {
            world[xStone1][yStone1] = character;
        }

    }

    public int xStone2;
    public int yStone2;
    public void placeCharacterS2(char character) {
        Random random = new Random();

        xStone2 = random.nextInt(size);
        yStone2 = random.nextInt(size);

        if (world[xStone2][yStone2] == ' ') {
            world[xStone2][yStone2] = character;
        }

    }

    public int xStone3;
    public int yStone3;
    public void placeCharacterS3(char character) {
        Random random = new Random();

        xStone3 = random.nextInt(size);
        yStone3 = random.nextInt(size);

        if (world[xStone3][yStone3] == ' ') {
            world[xStone3][yStone3] = character;
        }

    }

    public int xU;
    public int yU;
    public void placeCharacterU(char character, int count) {
        Random random = new Random();
        int remainingCount = count;

        while (remainingCount > 0) {
            xU = random.nextInt(size);
            yU = random.nextInt(size);

            if (world[xU][yU] == ' ') {
                world[xU][yU] = character;
                remainingCount--;
            }
        }
    }

    public void placeCharacterH(char character) {
        Random random = new Random();
        initialHeroX = random.nextInt(size);
        initialHeroY = random.nextInt(size);
        world[initialHeroX][initialHeroY] = character;
        hero = new Hero(initialHeroX, initialHeroY, numWumpus);
    }

    public void printWorld() {
        for (int i = -1; i <= size; i++) {
            for (int j = -1; j <= size; j++) {
                char borderChar = 'W';

                if (i >= 0 && i < size && j >= 0 && j < size) {
                    borderChar = world[i][j];
                }

                System.out.print(borderChar + " ");
            }
            System.out.println();

        }
        System.out.println("Current direction:" + hero.getDirection());
    }

    public void hint(){
        int asciivalue= initialHeroY+97;
        char ypos = (char)asciivalue;
        System.out.println("You have to take the gold and go back to position: "+"X: "+ initialHeroX +" and "+ "Y: "+ypos);
    }

    public String fileName;
    public void saveWorld() {
        System.out.println("Enter the file name to save the game state: ");
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insert = "insert status (heroX,heroY,steps,size,xGold,yGold,xStone1,yStone1,xU,yU,xStone2,yStone2,xStone3,yStone3,name,direction,initialX,initialY,arrows,hasGold,hasWumpus) values("
                +hero.getHeroX()+","
                +hero.getHeroY()+","
                +hero.steps+","
                +size+","
                +xGold+","
                +yGold+","
                +xStone1+","
                +yStone1+","
                +xU+","
                +yU+","+
                +xStone2+","
                +yStone2+","
                +xStone3+","
                +yStone3+",'"
                +fileName+"','"
                +hero.getDirection()+"',"
                +initialHeroX+","
                +initialHeroY+","
                +hero.getArrows()+","+hero.have+","+hero.haveU+")";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void play() {


        Scanner scanner = new Scanner(System.in);
        String input;
        boolean gameOver = false;
        printWorld();
        while (!gameOver) {

            System.out.println("Enter a command: (turn left, turn right, walk, shoot ,'bag', 'hint','save', give up)");
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
                case "bag":
                    hero.haveGold();
                    break;
                case "hint":
                    hint();
                    break;
                case "save":
                    saveWorld();
                    gameOver = true;
                    break;
                case "give up":
                    System.out.println("Are you sure you want to give up? (yes or no)");
                    String giveUpChoice = scanner.nextLine().toLowerCase();
                    if (giveUpChoice.equals("yes")) {
                        gameOver = true;
                    }
                    break;
                default:
                    System.out.println("Invalid command. Please use 'turn left', 'turn right', 'walk', 'bag', 'hint', 'give up'.");
            }
            if (gameOver == false){
                printWorld();
            }

            if (!hero.isAlive()) {
                System.out.println("Game over! The hero is dead.");
                System.out.println("Do you want to play again? (yes or no)");
                input = scanner.nextLine().toLowerCase();
                if (input.equals("yes")) {
                    hero = new Hero(initialHeroX, initialHeroY, numWumpus);
                    initializeWorld();
                    printWorld();
                } else {
                    gameOver = true;
                }
            }
            if (initialHeroX == hero.getHeroX() && initialHeroY == hero.getHeroY() && hero.isHasGold() == true){
                System.out.println("Congratulation !!! You won !! Your sccore is: "+ hero.steps);

                Scanner player = new Scanner(System.in);
                System.out.println("Please enter your name:");
                String name = scanner.nextLine();
                scanner.close();

                addRank(name,hero.steps);


                System.exit(0);
            }
        }
    }

    public void addRank(String name, int num) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insert = "insert into rank_tb(playerName, points) values('" + name + "','" + num + "');";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void reviwe(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        try {

            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rank_tb");

            while (resultSet.next()) {
                String column1Value = resultSet.getString("playerName");
                int column2Value = resultSet.getInt("points");

                System.out.println(" player name : " + column1Value + "  Points: " + column2Value);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }



}