public class Colony {
    
    String name;

    ResourceManager resourceManager;
    GeneratorManager generatorManager;
    
    public Colony(String name, ResourceManager resourceManager, GeneratorManager generatorManager) {
        this.name = name;
        this.resourceManager = resourceManager;
        this.generatorManager = generatorManager;
    }
    
    public void buildSolarPanel() {
        SolarPanel solarPanel = new SolarPanel(resourceManager);
        if (resourceManager.getMoney() < solarPanel.getCost()) {
            System.out.println("Not enough money to build a solar panel.");
            return;
        }

        generatorManager.addGenerator(solarPanel);
    }

    public void buildFarm() {
        Farm farm = new Farm(resourceManager);
        if (resourceManager.getMoney() < farm.getCost()) {
            System.out.println("Not enough money to build a farm.");
            return;
        }
        generatorManager.addGenerator(farm);
    }

    public void buildCarbonFilter() {
        CarbonFilter carbonFilter = new CarbonFilter(resourceManager);
        if (resourceManager.getMoney() < carbonFilter.getCost()) {
            System.out.println("Not enough money to build a carbon filter.");
            return;
        }
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
