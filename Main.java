import java.util.InputMismatchException;
import java.util.Scanner;

// TODO: Format main menu to be more user-friendly
// TODO: Optimize main menu to be more efficient
// TODO: Figure out if there's a way to make mainMenu() and generatorMenu() not require a save object
// TODO: Add save overwrite functionality / ability to delete select saves
// TODO: Add a population system that effects money gain and resource usage (This is low priority because it would require a lot of changes to the existing code)

public class Main {

    public static void main(String[] args) {
        
        // Initialize necessary objects
        Scanner scanner = new Scanner(System.in);
        ResourceManager resourceManager = new ResourceManager();
        GeneratorManager generatorManager = new GeneratorManager(resourceManager);
        Save save = new Save();

        if (save.saveExists()) { // If there is a save file, ask the user if they want to load it
            System.out.println("A save file has been detected. Would you like to load it?");
            System.out.print("Enter 'yes' or 'no': ");

            String choice = scanner.nextLine();
            if (choice.contains(("y"))) {

                System.out.println("What save would you like to load?");
                save.listSaves(); // List all saves in the save file
                System.out.print("Enter the number of the save you would like to load (Press 0 to start a new game): ");

                int saveSlot = 0;
                do { // Exception handling for save slot choice
                    try {
                        saveSlot = scanner.nextInt();
                        if (saveSlot < 0 || saveSlot > save.nextSaveSlot()) {
                            throw new IllegalArgumentException();
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid choice. Please try again.");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (saveSlot < 0 || saveSlot > save.nextSaveSlot()); // Loop until a valid save slot is chosen

                Colony colony = save.loadGame(resourceManager, generatorManager, saveSlot - 1); // Load the save file (saveSlot - 1 because save slots are 1-indexed, but the save file is 0-indexed)
                if (colony == null) {
                    System.out.println("Error loading save file. Starting new game.");
                    newGame(scanner, resourceManager, generatorManager); // If the save file fails to load, start a new game
                }

                mainMenu(scanner, colony, save); // Start the main menu
            } else {
                Colony colony = newGame(scanner, resourceManager, generatorManager);
                mainMenu(scanner, colony, save);
            }
        }
    }
    
    static Colony newGame(Scanner scanner, ResourceManager resourceManager, GeneratorManager generatorManager) {
        System.out.println("Welcome to Space Colony Sim! This is my final project for ITSC1213. To get started, why don't we name your colony?");
        System.out.print("Enter the name of your colony: ");
        String name = scanner.nextLine(); // Strings don't need exception handling

        Colony colony = new Colony(name, resourceManager, generatorManager); // Create a new colony object

        System.out.println("Great! Your colony has been created. Here are your starting resources:");
        System.out.println(colony.toString());

        System.out.println("You will need to build generators to keep your colony alive. Here are the available generators: ");
        System.out.println("1. Farm: Generates food. Cost: $150, Energy Usage: 5, Food Usage: 2, Oxygen Usage: 5, Production: 10");
        System.out.println("2. Solar Panel: Generates energy. Cost: $200, Energy Usage: 0, Food Usage: 0, Oxygen Usage: 5, Production: 5");
        System.out.println("3. Carbon Filter: Generates oxygen. Cost: $250, Energy Usage: 5, Food Usage: 5, Oxygen Usage: 5, Production: 10");

        return colony; // Return the colony object; this function returns a colony to avoid global variables
    }

    static void mainMenu(Scanner scanner, Colony colony, Save save) {
        
        int choice = 0;

        do {
            try {
                System.out.println("What would you like to do?");
                System.out.println("1. Build a generator");
                System.out.println("2. Get colony status");
                System.out.println("3. Go to next turn");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt(); // Exception handling for user input

                if (choice < 1 || choice > 4) {
                    throw new IllegalArgumentException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine(); // This is necessary to clear the scanner buffer
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice. Please try again.");
            }
            
            switch (choice) {
                case 1: // If the user chooses to build a generator, call the generatorMenu function
                    generatorMenu(scanner, colony, save); // This function is called to show the generator menu
                    break;
                case 2: // If the user chooses to get the colony status, print the colony status
                    System.out.println(colony.toString()); //  This function is called to show the colony status
                    break;
                case 3: // If the user chooses to go to the next turn, update the colony
                    colony.update();
                    break;
                case 4:
                    save.saveGame(colony); // If the user chooses to exit, save the game
                    scanner.close(); // Close the scanner
                    System.exit(0); // Gracefully exit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            mainMenu(scanner, colony, save); // This is called to make the main menu recursive
        } while (choice < 1 || choice > 4);
    }

    static void generatorMenu(Scanner scanner, Colony colony, Save save) {
        int choice = 0;

        do {
            try {
                System.out.println("\nWhich generator would you like to build?");
                System.out.println("\t1. Farm");
                System.out.println("\t2. Solar Panel");
                System.out.println("\t3. Carbon Filter");
                System.out.println("\t4. Go back");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                if (choice < 1 || choice > 4) {
                    throw new IllegalArgumentException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice. Please try again.");
            }
            
            switch (choice) {
                case 1:
                    colony.buildFarm(); // If the user chooses to build a farm, call the buildFarm function
                    break;
                case 2:
                    colony.buildSolarPanel(); // If the user chooses to build a solar panel, call the buildSolarPanel function
                    break;
                case 3:
                    colony.buildCarbonFilter(); // If the user chooses to build a carbon filter, call the buildCarbonFilter function
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            mainMenu(scanner, colony, save);
        } while (choice < 1 || choice > 4);
    }
}