package nye.hu.progTech;

import java.util.Random;

public class Hero {
     private int heroX;
     private int heroY;
     private char direction; // Character representing the direction ('N', 'S', 'E', 'W')
     private boolean isAlive;
     private boolean hasGold;
     private int arrows; // Number of arrows


     public Hero(int x, int y, int numWumpus) {
          this.heroX = x;
          this.heroY = y;
          this.arrows = numWumpus; // Initialize the number of arrows
          this.direction = getRandomDirection();
          this.hasGold = false;
     }




     public Hero(int x, int y) {
          this.heroX = x;
          this.heroY = y;
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

     public int getArrows() {
          return arrows;
     }

     public boolean isHasGold() {
          return hasGold;
     }

     public void setHeroPosition(int x, int y) {
          this.heroX = x;
          this.heroY = y;
     }

     public boolean isAlive() {
          return isAlive;
     }

     public char getRandomDirection() {
          char[] directions = {'N', 'S', 'E', 'W'};
          Random random = new Random();
          int randomIndex = random.nextInt(directions.length);
          return directions[randomIndex];
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

     public void walk(World world) {
          int newX = heroX;
          int newY = heroY;

          // Calculate the new position based on the current direction
          switch (direction) {
               case 'N':
                    newY--;
                    break;
               case 'E':
                    newX++;
                    break;
               case 'S':
                    newY++;
                    break;
               case 'W':
                    newX--;
                    break;
          }

          if (newX >= 0 && newX < world.getSize() && newY >= 0 && newY < world.getSize()) {
               char newPosition = world.getWorld()[newY][newX];
               if (newPosition != 'U') {
                    // The hero can walk to the new position since it's not 'U' (Wumpus)
                    setHeroPosition(newX, newY);

                    if (newPosition == 'G') {
                         // Hero collected gold, you can implement gold collection logic here
                         System.out.println("Hero collected gold!");
                         // Implement your gold collection logic here
                         world.getWorld()[newY][newX] = ' ';
                         hasGold=true;
                    }
               } else {
                    // The hero walked into 'U' (Wumpus), so they die
                    isAlive = false;
               }
          }
     }


     public void shoot(World world) {

          if (arrows > 0) { // Check if the hero has arrows
               int x = heroX;
               int y = heroY;

               // Calculate the coordinates of the cell in the direction the hero is facing
               switch (direction) {
                    case 'N':
                         y--;
                         break;
                    case 'E':
                         x++;
                         break;
                    case 'S':
                         y++;
                         break;
                    case 'W':
                         x--;
                         break;
               }

               // Check if the calculated coordinates are within the world bounds
               if (x >= 0 && x < world.getSize() && y >= 0 && y < world.getSize()) {
                    char target = world.getWorld()[y][x];
                    if (target == 'U') {
                         System.out.println("You shot the Wumpus! It's defeated!");
                         world.getWorld()[y][x] = ' '; // Remove the defeated Wumpus
                    } else {
                         System.out.println("Your shot missed. There's nothing to hit.");
                    }
               } else {
                    System.out.println("You cannot shoot outside of the world boundaries.");
               }

               arrows--; // Reduce the number of arrows after a shot
               System.out.println("You still have just "+ arrows +" arrows!");
          } else {
               System.out.println("You have no arrows left. Cannot shoot.");
          }
     }


}
