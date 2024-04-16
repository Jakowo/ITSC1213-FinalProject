public abstract class Generator {
    
    int cost;
    int energyUsage;
    int foodUsage;
    int oxygenUsage;
    int production;

    public Generator(int cost, int energyUsage, int foodUsage, int oxygenUsage, int production) {
        this.cost = cost;
        this.energyUsage = energyUsage;
        this.foodUsage = foodUsage;
        this.oxygenUsage = oxygenUsage;
        this.production = production;
    }

    public int getCost() {
        return cost;
    }

    public int getEnergyUsage() {
        return energyUsage;
    }

    public int getFoodUsage() {
        return foodUsage;
    }

    public int getOxygenUsage() {
        return oxygenUsage;
    }

    public int getProduction() {
        return production;
    }

    public void usage(ResourceManager resourceManager) {
        resourceManager.subtractEnergy(energyUsage);
        resourceManager.subtractFood(foodUsage);
        resourceManager.subtractOxygen(oxygenUsage);
    }

    public abstract void generate();

    public abstract String toString();

}
