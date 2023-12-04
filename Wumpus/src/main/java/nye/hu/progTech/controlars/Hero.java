package nye.hu.progTech.controlars;
import nye.hu.progTech.Map.World;

import java.util.Random;


public class Hero {
    private int heroX;
    private int heroY;
    private char direction;
    private boolean isAlive;
    private boolean hasGold;
    private int arrows;




    public Hero(int x, int y, int numWumpus) {
        heroX = x;
        heroY = y;
        arrows = numWumpus;
        direction = getRandomDirection();
        isAlive = true;
        hasGold = false;
    }

    public int getHeroX() {
        return heroX;
    }

    public int getHeroY() {
        return heroY;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public int getArrows() {
        return arrows;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isHasGold() {
        return hasGold;
    }

    public void turnLeft() {
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

    public void turnRight() {
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

    public int have = 0;
    int newX;
    int newY;
    public int steps = 0;
    public void walk(World world) {
        newX = heroX;
        newY = heroY;

        int oldx = heroX;
        int oldy = heroY;

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

        if (newX >= 0 && newX < world.getSize() && newY >= 0 && newY < world.getSize()) {
            char[][] temp = world.getWorld();
            char newPosition = temp[newX][newY];
            if (newPosition != 'U') {
                System.out.println("Next Char:" + newPosition);
                //world.printWorld();
                if (newPosition == 'S') {
                    System.out.println("You cannot move over stons !!");
                } else {
                    heroX = newX;
                    heroY = newY;
                    //world[heroX][heroY] = 'H';
                    world.setHero(heroX, heroY, oldx, oldy);



                    if (newPosition == 'G') {
                        hasGold = true;
                        have = 1;
                        world.getWorld()[newX][newY] = ' ';
                        System.out.println("You have the gold !!");
                        }
                    steps++;
                    System.out.println("Step number: " + steps);

                    char ascii= (char)(newY + 97);
                    System.out.println("The hero position is: " + "X: " + newX + " Y: " + ascii);
                    }
            } else {
                isAlive = false;
            }
        } else {
            System.out.println(" --------------------------------------------");
            System.out.println(" ------- You cannot pass through borders ----");
            System.out.println(" --------------------------------------------");

        }
    }

    public int haveU = 0;
    public void shoot(World world) {

        if (arrows > 0) {
            char[][] temp = world.getWorld();
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




            if (x >= 0 && x < world.getSize() && y >= 0 && y < world.getSize()) {

                char target = temp[x][y];
                System.out.println("char:"+target);
                if (target == 'U') {
                    System.out.println("You shot the Wumpus! It's defeated!");
                    world.getWorld()[x][y] = ' ';
                    haveU = 1;
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



    public char getRandomDirection() {
        char[] directions = {'N', 'S', 'E', 'W'};
        Random random = new Random();
        int randomIndex = random.nextInt(directions.length);
        return directions[randomIndex];
    }




    public void haveGold(){
        if (hasGold) {
            System.out.println("the hero have Gold lets back to safe zone !!!");
        } else {
            System.out.println("Bag is empty !!!");
        }
    }


}

