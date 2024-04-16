public class CarbonFilter extends Generator {

    ResourceManager resourceManager;

    public CarbonFilter(ResourceManager resourceManager) {
        super(200, 10, 0, 0, 10);
        this.resourceManager = resourceManager;
    }

    @Override
    public void generate() {
        resourceManager.addOxygen(production);
        resourceManager.subtractEnergy(energyUsage);
        resourceManager.subtractFood(foodUsage);
        resourceManager.subtractOxygen(oxygenUsage);
    }

    @Override
    public String toString() {
        return "{Carbon Filter: cost=" + cost + ", energyUsage=" + energyUsage + ", foodUsage=" + foodUsage + ", oxygenUsage=" + oxygenUsage + ", production=" + production + "}";
    }
    
}
