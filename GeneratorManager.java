import java.util.ArrayList;

public class GeneratorManager {
    
    ResourceManager resourceManager;
    private ArrayList<Generator> generators;

    public GeneratorManager(ResourceManager resourceManager) {
        this.generators = new ArrayList<Generator>();
        this.resourceManager = resourceManager;
    }

    public ArrayList<Generator> getGenerators() {
        return generators; // Return the list of generators
    }

    public void addGenerator(Generator generator) {
        generators.add(generator); // Add a generator to the list
        resourceManager.subtractMoney(generator.getCost()); // Subtract the cost of the generator from the money resource
    }

    public void removeGenerator(Generator generator) {
        generators.remove(generator); // Remove a generator from the list
    }

    public void generate() {
        for (Generator generator : generators) {
            generator.generate(); // Generate resources
        }
    }

    public String toString() {
        String result = "";
        for (Generator generator : generators) {
            result += generator.toString() + "\n";
        }
        return result;
    }

}
