import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        ResourceManager resourceManager = new ResourceManager();
        GeneratorManager generatorManager = new GeneratorManager(resourceManager);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Space Colony Sim! This is my final project for ITSC1213. To get started, why don't we name your colony?");
        System.out.print("Enter the name of your colony: ");
        String name = scanner.nextLine();
        
        Colony colony = new Colony(name, resourceManager, generatorManager);

        System.out.println("Great! Your colony has been created. Here are your starting resources:");
        System.out.println(colony.toString());

        System.out.println("You will need to build generators to keep your colony alive. Here are the available generators: ");
        System.out.println("1. Farm: Generates food. Cost: $150, Energy Usage: 5, Food Usage: 2, Oxygen Usage: 5, Production: 10");
        System.out.println("2. Solar Panel: Generates energy. Cost: $200, Energy Usage: 0, Food Usage: 0, Oxygen Usage: 5, Production: 5");
        System.out.println("3. Carbon Filter: Generates oxygen. Cost: $250, Energy Usage: 5, Food Usage: 5, Oxygen Usage: 5, Production: 10");

        mainMenu(scanner, colony);

    }
    
    static void mainMenu(Scanner scanner, Colony colony) {
        
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
                    generatorMenu(scanner, colony);
                    break;
                case 2:
                    System.out.println(colony.toString());
                    break;
                case 3:
                    colony.update();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            mainMenu(scanner, colony);
        } while (choice < 1 || choice > 4);
    }

    static void generatorMenu(Scanner scanner, Colony colony) {
        int choice = 0;

        do {
            try {
                System.out.println("Which generator would you like to build?");
                System.out.println("1. Farm");
                System.out.println("2. Solar Panel");
                System.out.println("3. Carbon Filter");
                System.out.println("4. Go back");
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

            mainMenu(scanner, colony);
        } while (choice < 1 || choice > 4);
    }
}