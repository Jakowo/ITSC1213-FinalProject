public class Colony {
    
    String name;

    ResourceManager resourceManager;
    GeneratorManager generatorManager;
    
    public Colony(String name, ResourceManager resourceManager, GeneratorManager generatorManager) {
        this.name = name;
        this.resourceManager = resourceManager;
        this.generatorManager = generatorManager;
    }

    public String getName() {
        return name;
    }
    
    public void buildSolarPanel() {
        SolarPanel solarPanel = new SolarPanel(resourceManager);
        generatorManager.addGenerator(solarPanel);
    }

    public void buildFarm() {
        Farm farm = new Farm(resourceManager);
        generatorManager.addGenerator(farm);
    }

    public void buildCarbonFilter() {
        CarbonFilter carbonFilter = new CarbonFilter(resourceManager);
        generatorManager.addGenerator(carbonFilter);
    }

    public void update() {
        generatorManager.generate();
        checkStatus();
        resourceManager.turnUpdate();
    }
    
    private void checkStatus() {    
        if (resourceManager.getEnergy() < 20) {
            System.out.println("Warning: Energy is low! Consider building a solar panel.");
        }

        if (resourceManager.getFood() < 20) {
            System.out.println("Warning: Food is low! Consider building a farm.");
        }

        if (resourceManager.getOxygen() < 20) {
            System.out.println("Warning: Oxygen is low! Consider building a carbon filter.");
        }


        if (resourceManager.getEnergy() < 0 || resourceManager.getFood() < 0 || resourceManager.getOxygen() < 0) {
            System.out.println(name + " has run out of resources! Game over.");
            System.exit(0);
        }
    }

    public String toString() {
        return generatorManager.toString() + resourceManager.toString();
    }

}
