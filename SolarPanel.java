public class SolarPanel extends Generator {
    
    ResourceManager resourceManager;

    public SolarPanel(ResourceManager resourceManager) {
        super(100, 5, 0, 0, 10);
        this.resourceManager = resourceManager;
    }

    @Override
    public void generate() {
        resourceManager.addEnergy(production); // Add energy to the resource manager
        usage(resourceManager); // Use resources
    }

    @Override
    public String toString() {
        return "{Solar Panel: cost=" + cost + ", energyUsage=" + energyUsage + ", foodUsage=" + foodUsage + ", oxygenUsage=" + oxygenUsage + ", production=" + production + "}";
    }

}
