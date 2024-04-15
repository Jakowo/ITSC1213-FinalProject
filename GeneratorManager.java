import java.util.ArrayList;

public class GeneratorManager {
    
    ResourceManager resourceManager;
    private ArrayList<Generator> generators;

    public GeneratorManager(ResourceManager resourceManager) {
        this.generators = new ArrayList<Generator>();
        this.resourceManager = resourceManager;
    }

    public void addGenerator(Generator generator) {
        generators.add(generator);
        resourceManager.subtractMoney(generator.getCost());
    }

    public void removeGenerator(Generator generator) {
        generators.remove(generator);
    }

    public void generate() {
        for (Generator generator : generators) {
            generator.generate();
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
