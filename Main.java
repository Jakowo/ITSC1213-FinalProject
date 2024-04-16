import java.util.InputMismatchException;
import java.util.Scanner;

// TODO: Overhaul save system to have multiple saves
// TODO: Format main menu to be more user-friendly
// TODO: Optimize main menu to be more efficient
// TODO: Figure out if there's a way to make mainMenu() and generatorMenu() not require a save object
// TODO: Add comments to all classes and methods

public class Main {

    public static void main(String[] args) {
        
        Save save = new Save();
        ResourceManager resourceManager = new ResourceManager();
        GeneratorManager generatorManager = new GeneratorManager(resourceManager);
        Colony colony = new Colony("Default", resourceManager, generatorManager);
        Scanner scanner = new Scanner(System.in);

        if (save.saveExists()) {
            System.out.println("A save file has been detected. Would you like to load it?");
            System.out.print("Enter 'yes' or 'no': ");
            String choice = scanner.nextLine();

            if (choice.contains("y")) {
                save.loadGameRework(colony);
                System.out.println("Game loaded successfully.");
                mainMenu(scanner, colony, save);
            } else {
                newGame(scanner, colony, resourceManager, generatorManager);
            }
        } else {
            newGame(scanner, colony, resourceManager, generatorManager);
        }
        mainMenu(scanner, colony, save);
    }
    
    static void newGame(Scanner scanner, Colony colony, ResourceManager resourceManager, GeneratorManager generatorManager) {
        System.out.println("Welcome to Space Colony Sim! This is my final project for ITSC1213. To get started, why don't we name your colony?");
        System.out.print("Enter the name of your colony: ");
        String name = scanner.nextLine();

        colony = new Colony(name, resourceManager, generatorManager);

        System.out.println("Great! Your colony has been created. Here are your starting resources:");
        System.out.println(colony.toString());

        System.out.println("You will need to build generators to keep your colony alive. Here are the available generators: ");
        System.out.println("1. Farm: Generates food. Cost: $150, Energy Usage: 5, Food Usage: 2, Oxygen Usage: 5, Production: 10");
        System.out.println("2. Solar Panel: Generates energy. Cost: $200, Energy Usage: 0, Food Usage: 0, Oxygen Usage: 5, Production: 5");
        System.out.println("3. Carbon Filter: Generates oxygen. Cost: $250, Energy Usage: 5, Food Usage: 5, Oxygen Usage: 5, Production: 10");
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
                choice = scanner.nextInt();

                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please try again.");
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
                    generatorMenu(scanner, colony, save);
                    break;
                case 2:
                    System.out.println(colony.toString());
                    break;
                case 3:
                    colony.update();
                    break;
                case 4:
                    save.saveGameRework(colony);
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            mainMenu(scanner, colony, save);
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
                    colony.buildFarm();
                    break;
                case 2:
                    colony.buildSolarPanel();
                    break;
                case 3:
                    colony.buildCarbonFilter();
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