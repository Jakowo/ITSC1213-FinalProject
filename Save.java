import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    public void saveGame(Colony colony) {

        try {

            FileWriter fileWriter = new FileWriter("save.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Find the next empty line in the file, and write the data there.

            int id = nextSaveSlot(); // Find the next empty line in the file
            
            switch(id) {
                case -1: // Error finding next save slot
                    System.out.println("Error saving game.");
                    bufferedWriter.close();
                    fileWriter.close();
                    return;
                case 0: // File is empty
                    break;
                default: // Else
                    bufferedWriter.newLine();
                    break;
            }

            String line = "";
            line += id + ","; // Save slot
            line += colony.getName() + ","; // Colony name
            line += colony.resourceManager.getMoney() + ","; // Money
            line += colony.resourceManager.getEnergy() + ","; // Energy
            line += colony.resourceManager.getFood() + ","; // Food
            line += colony.resourceManager.getOxygen() + ","; // Oxygen

            for (Generator generator : colony.generatorManager.getGenerators()) { // Loop through the generators
                line += generator.getClass().getName() + ","; // Add the generator to the save data
            }

            line = line.substring(0, line.length() - 1); // Remove the last comma.
            bufferedWriter.write(line); // Write the line to the file

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving game.");
        }
    }

    public Colony loadGame(ResourceManager resourceManager, GeneratorManager generatorManager, int saveSlot) {

        try {
            FileReader fileReader = new FileReader("save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int i = 0; i < saveSlot; i++) {
                bufferedReader.readLine(); // Skip to the save slot
            }

            String line = bufferedReader.readLine(); 
            String data[] = line.split(","); // Split the line into an array

            String name = data[1]; // Get the colony name
            int money = Integer.parseInt(data[2]); // Get the money
            int energy = Integer.parseInt(data[3]); // Get the energy
            int food = Integer.parseInt(data[4]); // Get the food
            int oxygen = Integer.parseInt(data[5]); // Get the oxygen

            Colony colony = new Colony(name, resourceManager, generatorManager); // Create a new colony object

            for (int i = 6; i < data.length; i++) { // Loop through the generators
                switch (data[i]) {
                    case "Farm":
                        colony.buildFarm(); // If the generator is a farm, build a farm
                        break;
                    case "SolarPanel":
                        colony.buildSolarPanel(); // If the generator is a solar panel, build a solar panel
                        break;
                    case "CarbonFilter":
                        colony.buildCarbonFilter(); // If the generator is a carbon filter, build a carbon filter
                        break;
                }
            }

            // Set the resources to the values loaded from the file
            resourceManager.setMoney(money); 
            resourceManager.setEnergy(energy);
            resourceManager.setFood(food);
            resourceManager.setOxygen(oxygen);

            bufferedReader.close();
            fileReader.close();
            
            return colony;
        } catch (IOException e) {
            System.out.println("Error loading game.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data.");
        }

        return null;
    }

    // This is going to write the save data into a text file, so that the user can load it later.

    public boolean saveExists() {
        try {
            FileReader fileReader = new FileReader("save.txt"); // If a save file exists, the file reader will be able to read it.
            if (fileReader.read() == -1) { // If the file is empty, return false.
                fileReader.close();
                return false;
            }
            fileReader.close();
            return true; // If the file is not empty, return true.
        } catch (IOException e) {
            return false;
        }
    }

    public void listSaves() {
        try {
            FileReader fileReader = new FileReader("save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) { // Loop through the file
                System.out.println(line); // Print each line in the file
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error listing saves.");
        }
    }

    public int nextSaveSlot() {
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("save.txt"));

            int count = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                count++;
                if (line.equals("")) {
                    break; // If the line is empty, return the count
                }
            } 

            bufferedReader.close();
            return count + 1; // Return the count + 1 (the next save slot)
        } catch (IOException e) {
            System.out.println("Error finding next save slot.");
        }
        return -1; // Return -1 if there is an error
    }

    public void deleteSave() {
        try {
            FileWriter fileWriter = new FileWriter("save.txt"); // Overwrite the save file
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error deleting save.");
        }
    }
}