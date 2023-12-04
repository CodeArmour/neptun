package nye.hu.progTech;

import nye.hu.progTech.Map.World;
import nye.hu.progTech.Start.Menu;


public class Main {

    public static void main(String[] args) {
        World world = new World(100); // Start with an initial size of 5x5

        // Create a Menu object and pass the World to it.
        Menu menu = new Menu(world);

        // Start the game by showing the main menu.
        menu.showMainMenu();
    }
}