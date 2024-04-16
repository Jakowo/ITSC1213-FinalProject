public class Farm extends Generator {

    ResourceManager resourceManager;

    public Farm(ResourceManager resourceManager) {
        super(100, 5, 5, 5, 10);
        this.resourceManager = resourceManager;
    }

    @Override
    public void generate() {
        resourceManager.addFood(production);
        usage(resourceManager);
    }

    @Override
    public String toString() {
        return "{Farm: cost=" + cost + ", energyUsage=" + energyUsage + ", foodUsage=" + foodUsage + ", oxygenUsage=" + oxygenUsage + ", production=" + production + "}";
    }
}
