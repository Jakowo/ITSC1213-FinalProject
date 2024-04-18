public class ResourceManager {
    
    int money;
    int oxygen;
    int food;
    int energy;

    public ResourceManager() {
        this.money = 1000;
        this.oxygen = 100;
        this.food = 100;
        this.energy = 100;
    }

    public int getMoney() {
        return money;
    }

    public int getOxygen() {
        return oxygen;
    }

    public int getFood() {
        return food;
    }

    public int getEnergy() {
        return energy;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void addOxygen(int oxygen) {
        this.oxygen += oxygen;
    }

    public void addFood(int food) {
        this.food += food;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
    }

    public void subtractMoney(int money) {
        this.money -= money;
    }

    public void subtractOxygen(int oxygen) {
        this.oxygen -= oxygen;
    }

    public void subtractFood(int food) {
        this.food -= food;
    }

    public void subtractEnergy(int energy) {
        this.energy -= energy;
    }

    public void turnUpdate() { // Adds a set income, and takes away a set amount of resources every turn
        addMoney(50);
        subtractOxygen(5);
        subtractFood(5);
        subtractEnergy(5);
    }

    public void printResources() { // Prints the resources to the console
        System.out.println("Money: " + money);
        System.out.println("Oxygen: " + oxygen);
        System.out.println("Food: " + food);
        System.out.println("Energy: " + energy);
    }

    public String toString() {
        return "Money: " + money + ", Oxygen: " + oxygen + ", Food: " + food + ", Energy: " + energy;
    }

}
