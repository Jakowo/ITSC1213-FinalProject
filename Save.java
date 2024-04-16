import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    
    // This is going to write the save data into a text file, so that the user can load it later.
    public void saveGame(Colony colony, ResourceManager resourceManager, GeneratorManager generatorManager) {
        try {
            FileWriter fileWriter = new FileWriter("save.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(colony.getName()); // This is the name of the colony.
            bufferedWriter.newLine();
            bufferedWriter.write(resourceManager.getMoney() + "");
            bufferedWriter.newLine();
            bufferedWriter.write(resourceManager.getEnergy() + "");
            bufferedWriter.newLine();
            bufferedWriter.write(resourceManager.getFood() + "");
            bufferedWriter.newLine();
            bufferedWriter.write(resourceManager.getOxygen() + "");
            bufferedWriter.newLine();
            
            for (Generator generator : generatorManager.getGenerators()) {
                bufferedWriter.write(generator.getClass().getName());
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving game.");
        }
    }

    // This is going to load the save data from the text file.
    public void loadGame(Colony colony, ResourceManager resourceManager, GeneratorManager generatorManager) {
        try {
            FileReader fileReader = new FileReader("save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String name = bufferedReader.readLine();
            int money = Integer.parseInt(bufferedReader.readLine());
            int energy = Integer.parseInt(bufferedReader.readLine());
            int food = Integer.parseInt(bufferedReader.readLine());
            int oxygen = Integer.parseInt(bufferedReader.readLine());
            
            colony = new Colony(name, resourceManager, generatorManager);
            resourceManager.setMoney(money);
            resourceManager.setEnergy(energy);
            resourceManager.setFood(food);
            resourceManager.setOxygen(oxygen);
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                switch (line) {
                    case "Farm":
                        colony.buildFarm();
                        break;
                    case "SolarPanel":
                        colony.buildSolarPanel();
                        break;
                    case "CarbonFilter":
                        colony.buildCarbonFilter();
                        break;
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error loading game.");
        }
    }

    public boolean saveExists() {
        try {
            FileReader fileReader = new FileReader("save.txt");
            fileReader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void deleteSave() {
        try {
            FileWriter fileWriter = new FileWriter("save.txt");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error deleting save.");
        }
    }
}
