public class CarbonFilter extends Generator {

    ResourceManager resourceManager;

    public CarbonFilter(ResourceManager resourceManager) {
        super(200, 10, 0, 0, 10);
        this.resourceManager = resourceManager;
    }

    @Override
    public void generate() { 
        resourceManager.addOxygen(production); // Add the production amount to the oxygen resource
        usage(resourceManager); // Use the generator (super function from abstract class Generator)
    }

    @Override
    public String toString() { // Return the string representation of the object
        return "{Carbon Filter: cost=" + cost + ", energyUsage=" + energyUsage + ", foodUsage=" + foodUsage + ", oxygenUsage=" + oxygenUsage + ", production=" + production + "}";
    }
    
}
