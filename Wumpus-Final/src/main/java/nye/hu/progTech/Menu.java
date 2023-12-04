package nye.hu.progTech;

import java.util.Scanner;

public class Menu {
    private World world;

    public Menu(World world) {
        this.world = world;
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Edit World");
            System.out.println("2. Save in Database");
            System.out.println("3. Load from Database");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    editWorldMenu();
                    break;
                case 2:
                    saveToDatabase();
                    break;
                case 3:
                    loadFromDatabase();
                    break;
                case 4:
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
                    System.out.println("World difficulty set to Easy.");
                    break;
                case 2:
                    //world.setWorldDifficulty("Normal");
                    System.out.println("World difficulty set to Normal.");
                    break;
                case 3:
                    //world.setWorldDifficulty("Hard");
                    System.out.println("World difficulty set to Hard.");
                    break;
                case 4:
                    return; // Go back to the Main Menu
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private void saveToDatabase() {
        // Implement saving to a database
        System.out.println("Saving to the database...");
    }

    private void loadFromDatabase() {
        // Implement loading from a database
        System.out.println("Loading from the database...");
    }
}

