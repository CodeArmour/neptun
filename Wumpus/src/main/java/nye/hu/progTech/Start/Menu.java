package nye.hu.progTech.Start;

import nye.hu.progTech.Map.Load;
import nye.hu.progTech.Map.World;

import java.util.Scanner;

public class Menu {
    private World world;
    private Load load;




    public Menu(World world) {
        this.world = world;
        this.load =new Load(0);
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Edit World");
            System.out.println("2. The Ranking");
            System.out.println("3. Load from Database");
            System.out.println("4. Saved Maps");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Set world size to a random value for Easy difficulty
                    editWorldMenu();
                    break;
                case 2:
                    System.out.println("\n");
                    world.reviwe();
                    System.out.println("\n");
                    showMainMenu();
                    break;
                case 3:
                    int a =load.getSize();
                    load = new Load(a);

                    load.play();
                    break;
                case 4:
                    System.out.println("\n");
                    load.reviwe();
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Exiting the game.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private void editWorldMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Edit World Menu:");
            System.out.println("1. Easy");
            System.out.println("2. Normal");
            System.out.println("3. Hard");
            System.out.println("4. Back to Main Menu");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    //world.setWorldDifficulty("Easy");
                    int easySize = world.getRandomNumberEasy();
                    world = new World(easySize);
                    System.out.println("World difficulty set to Easy.");
                    world.play();
                    break;

                case 2:
                    //world.setWorldDifficulty("Normal");
                    int normalSize = world.getRandomNumberNormal();
                    world = new World(normalSize);
                    System.out.println("World difficulty set to Normal.");
                    world.play();
                    break;
                case 3:
                    //world.setWorldDifficulty("Hard");
                    int hardSize = world.getRandomNumberHard();
                    world = new World(hardSize);
                    System.out.println("World difficulty set to Hard.");
                    world.play();
                    break;
                case 4:
                    return; // Go back to the Main Menu
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    // Rest of the Menu class remains the same.
}


