public class Farm extends Generator {

    ResourceManager resourceManager;

    public Farm(ResourceManager resourceManager) {
        super(100, 5, 2, 3, 7);
        this.resourceManager = resourceManager;
    }

    @Override
    public void generate() {
        resourceManager.addFood(production);
        resourceManager.subtractEnergy(energyUsage);
        resourceManager.subtractFood(foodUsage);
        resourceManager.subtractOxygen(oxygenUsage);
    }

    @Override
    public String toString() {
        return "{Farm: cost=" + cost + ", energyUsage=" + energyUsage + ", foodUsage=" + foodUsage + ", oxygenUsage=" + oxygenUsage + ", production=" + production + "}";
    }
}
