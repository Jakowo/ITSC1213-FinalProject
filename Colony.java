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
        SolarPanel solarPanel = new SolarPanel(resourceManager); // Create a new solar panel
        if (checkMoney(solarPanel.getCost())) { // Check if there is enough money to build the generator
            generatorManager.addGenerator(solarPanel); // Add the generator to the generator manager
        } 
    }

    public void buildFarm() {
        Farm farm = new Farm(resourceManager);
        if (checkMoney(farm.getCost())) {
            generatorManager.addGenerator(farm);
        } 
    }

    public void buildCarbonFilter() {
        CarbonFilter carbonFilter = new CarbonFilter(resourceManager);
        if (checkMoney(carbonFilter.getCost())) {
            generatorManager.addGenerator(carbonFilter);
        } 
    }

    public void update() {
        generatorManager.generate(); // Generate resources
        checkStatus(); // Check the status of the colony (if resources are low or negative)
        resourceManager.turnUpdate(); // Update the resources
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

    private boolean checkMoney(int cost) {
        if (resourceManager.getMoney() < cost) {
            System.out.println("Not enough money to build this generator.");
            return false;
        }
        return true;
    }

    public String toString() {
        return name + "\n" + generatorManager.toString() + resourceManager.toString();
    }

}
