import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    
    public void saveGameRework(Colony colony) {

        try {
            FileWriter fileWriter = new FileWriter("save.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = "";
            line += colony.getName() + ",";
            line += colony.resourceManager.getMoney() + ",";
            line += colony.resourceManager.getEnergy() + ",";
            line += colony.resourceManager.getFood() + ",";
            line += colony.resourceManager.getOxygen() + ",";

            for (Generator generator : colony.generatorManager.getGenerators()) {
                line += generator.getClass().getName() + ",";
            }

            line = line.substring(0, line.length() - 1); // Remove the last comma.
            bufferedWriter.write(line);

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving game.");
        }
    }

    public Colony loadGameRework(ResourceManager resourceManager, GeneratorManager generatorManager) {

        try {
            FileReader fileReader = new FileReader("save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            String data[] = line.split(",");

            String name = data[0];
            int money = Integer.parseInt(data[1]);
            int energy = Integer.parseInt(data[2]);
            int food = Integer.parseInt(data[3]);
            int oxygen = Integer.parseInt(data[4]);

            Colony colony = new Colony(name, resourceManager, generatorManager);

            for (int i = 5; i < data.length; i++) {
                switch (data[i]) {
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
