public class SolarPanel extends Generator {
    
    ResourceManager resourceManager;

    public SolarPanel(ResourceManager resourceManager) {
        super(100, 5, 0, 5, 10);
        this.resourceManager = resourceManager;
    }

    @Override
    public void generate() {
        resourceManager.addEnergy(production);
        resourceManager.subtractEnergy(energyUsage);
        resourceManager.subtractFood(foodUsage);
        resourceManager.subtractOxygen(oxygenUsage);
    }

    @Override
    public String toString() {
        return "{Solar Panel: cost=" + cost + ", energyUsage=" + energyUsage + ", foodUsage=" + foodUsage + ", oxygenUsage=" + oxygenUsage + ", production=" + production + "}";
    }

}
