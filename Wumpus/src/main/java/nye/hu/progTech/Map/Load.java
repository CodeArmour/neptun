package nye.hu.progTech.Map;


import nye.hu.progTech.database.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class Load {
private char[][] loadedWorld;
private String name;
private int size ;
private char direction;
private int heroX;
private int heroY;
private int steps;
private int xGold;
private int yGold;
private int xU;
private int yU;
private int xStone1;
private int yStone1;
private int xStone2;
private int yStone2;
private int xStone3;
private int yStone3;
private boolean isAlive = true;
private  boolean hasGold = false;
private int hasGoldnum;
private int initialX;
private int initialY;
private int arrows;
private boolean hasWumpus = false;
private int haswumpusnum;


    public Load(int size) {
        this.size = size;
        this.loadedWorld=new char[size][size];
        setValuesFromDatabase("ff");
    }

    public char[][] getLoadedWorld() {
        return loadedWorld;
    }

    public void setLoadedWorld(char[][] loadedWorld) {
        this.loadedWorld = loadedWorld;
    }

    public int getSize() {
        return size;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }


    public void setHeroX(int heroX) {
        this.heroX = heroX;
    }

    public void setHeroY(int heroY) {
        this.heroY = heroY;
    }

    public int getHeroX() {
        return heroX;
    }

    public int getHeroY() {
        return heroY;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }



    public void loadWorld(){

    for (int i = -1; i <= size; i++) {
        for (int j = -1; j <= size; j++) {
            char borderChar = 'W';

            if (i >= 0 && i < size && j >= 0 && j < size) {
                borderChar = loadedWorld[i][j];
            }

            System.out.print(borderChar + " ");
        }
        System.out.println();
        creatWorld();



    }

}


public void creatWorld(){

     loadedWorld = new char[size][size];
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            loadedWorld[i][j] = ' ';
        }
    }

    loadedWorld[heroX][heroY]='H';

    if (hasGoldnum==1){
        hasGold=true;
    }else {
        hasGold=false;
    }

    if (hasGold==true) {
        loadedWorld[xGold][yGold] = ' ';
    }else {
        loadedWorld[xGold][yGold] = 'G';
    }

    if (haswumpusnum== 1){
        hasWumpus=true;
    }else {
        hasWumpus=false;
    }

    if (hasWumpus==true) {
        loadedWorld[xU][yU] = ' ';
    }else {
        loadedWorld[xU][yU] = 'U';
    }

    loadedWorld[xStone1][yStone1]='S';
    loadedWorld[xStone2][yStone2]='S';
    loadedWorld[xStone3][yStone3]='S';

}

public void setValuesFromDatabase(String player){

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    try{
        String query = "SELECT * FROM status where name ='"+player+"'";
        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery(query);



        if (resultSet.next()) {
            size = resultSet.getInt("size");
            heroX = resultSet.getInt("heroX");
            heroY = resultSet.getInt("heroY");
            xGold = resultSet.getInt("xGold");
            yGold = resultSet.getInt("yGold");
            xU = resultSet.getInt("xU");
            yU = resultSet.getInt("yU");
            xStone1 = resultSet.getInt("xStone1");
            yStone1 = resultSet.getInt("yStone1");
            xStone2 = resultSet.getInt("xStone2");
            yStone2 = resultSet.getInt("yStone2");
            xStone3 = resultSet.getInt("xStone3");
            yStone3 = resultSet.getInt("yStone3");
            steps = resultSet.getInt("steps");
            direction = resultSet.getString("direction").charAt(0);
            initialX = resultSet.getInt("initialX");
            initialY = resultSet.getInt("initialY");
            name = resultSet.getString("name");
            arrows = resultSet.getInt("arrows");
            hasGoldnum = resultSet.getInt("hasGold");
            haswumpusnum = resultSet.getInt("hasWumpus");



        }
    }catch (Exception e){
        System.out.println(e);
    }
}

    public void turnLeftLoad() {
        switch (direction) {
            case 'N':
                direction = 'W';
                break;
            case 'W':
                direction = 'S';
                break;
            case 'S':
                direction = 'E';
                break;
            case 'E':
                direction = 'N';
                break;
        }
    }

    public void turnRightLoad() {
        switch (direction) {
            case 'N':
                direction = 'E';
                break;
            case 'E':
                direction = 'S';
                break;
            case 'S':
                direction = 'W';
                break;
            case 'W':
                direction = 'N';
                break;
        }
    }

    int newX;
    int newY;

    public void walk() {
        newX = heroX;
        newY = heroY;


        switch (direction) {
            case 'N':
                newX--;
                break;
            case 'E':
                newY++;
                break;
            case 'S':
                newX++;
                break;
            case 'W':
                newY--;
                break;
        }

        if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
            char[][] temp = loadedWorld;
            char newPosition = temp[newX][newY];
            if (newPosition != 'U') {
                System.out.println("Next Char:" + newPosition);
                //world.printWorld();
                if (newPosition == 'S') {
                    System.out.println("You cannot move over stons !!");
                } else {
                    heroX = newX;
                    heroY = newY;

                    if (newPosition == 'G') {
                        hasGold = true;
                        System.out.println("You have the gold !!");
                    }
                    steps++;
                    System.out.println("Step number: " + steps);

                    char ascii= (char)(newY+97);
                    System.out.println("The hero position is: "+"X: "+newX+" Y: "+ascii);
                    char position = (char)(initialY+97);
                    System.out.println("back to X: "+initialX +" Y: "+position);
                }
            } else {
                isAlive = false;
            }
        } else {
            System.out.println(" ---------------------------------------------");
            System.out.println(" ------- You cannot pass through borders -----");
            System.out.println(" ---------------------------------------------");

        }
    }

    public void shoot() {

        if (arrows > 0) {
            char[][] temp = loadedWorld;
            int x = heroX;
            int y = heroY;

            switch (direction) {
                case 'N':
                    x--;
                    break;
                case 'E':
                    y++;
                    break;
                case 'S':
                    x++;
                    break;
                case 'W':
                    y--;
                    break;
            }



            if (x >= 0 && x < size && y >= 0 && y < size) {

                char target = temp[x][y];
                System.out.println("char:"+target);
                if (target == 'U') {
                    hasWumpus=true;
                    System.out.println("You shot the Wumpus! It's defeated!");

                } else {
                    System.out.println("Your shot missed. There's nothing to hit.");
                }

                System.out.println("Next Char:" + target);

            } else {
                System.out.println("You cannot shoot outside of the world boundaries.");

            }
            arrows--;
            System.out.println("You have " + arrows + " arrows left.");
        } else {
            System.out.println("You have no arrows left. Cannot shoot.");
        }
    }

    boolean hasReachedStart = false;

    public void play() {

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean gameOver = false;
        loadWorld();
        while (!gameOver) {

            System.out.println("Hero direction: "+direction);
            System.out.println("Enter a command: (turn left, turn right, walk, shoot, give up)");
            input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "turn left":
                    turnLeftLoad();
                    break;
                case "turn right":
                    turnRightLoad();
                    break;
                case "walk":
                    walk();
                    break;
                case "shoot":
                    shoot();
                    break;
                case "give up":
                    gameOver = true;
                    delete();
                    break;
                default:
                    System.out.println("Invalid command. Please use 'turn left', 'turn right', 'walk','shoot','give up'.");
            }
            if (gameOver == false) {
                loadWorld();
            }

            if (!isAlive) {
                System.out.println("Game over! The hero is dead.");
                delete();
                gameOver = true;
            }
            if (hasGold && heroX == initialX && heroY == initialY) {
                System.out.println("Congratulations! You won! You returned to the starting position with the gold!");
                // Add any additional actions upon winning, and set gameOver to true if needed
                hasReachedStart = true;
                addRank(name,steps);
                delete();
                gameOver = true;
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

    public void delete() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String selectID = "SELECT id FROM status WHERE name = ?";
        String deleteStatus = "DELETE FROM status WHERE id = ?";

        try {
            // Retrieve the ID of the record
            PreparedStatement selectStatement = connectDB.prepareStatement(selectID);
            selectStatement.setString(1, name);
            ResultSet resultSet = selectStatement.executeQuery();

            int idToDelete = 0;
            if (resultSet.next()) {
                idToDelete = resultSet.getInt("id");
            }

            // Delete the record based on the retrieved ID
            if (idToDelete != 0) {
                PreparedStatement deleteStatement = connectDB.prepareStatement(deleteStatus);
                deleteStatement.setInt(1, idToDelete);
                deleteStatement.executeUpdate();
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("No matching record found.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // Close the database connection and other resources if needed
            try {
                if (connectDB != null) {
                    connectDB.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }


    public void reviwe(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        try {

            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM status");

            while (resultSet.next()) {
                String column1Value = resultSet.getString("name");
                int column2Value = resultSet.getInt("steps");
                int column3Value = resultSet.getInt("size");

                System.out.println("player: " + column1Value + "  Points: " + column2Value + " Size: " +column3Value);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

}












