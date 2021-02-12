package shoppingSpree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    private void setName(String name) {
        ValidatorUtils.nonNegativeName(name);
        this.name = name;
    }

    private void setCost(double cost) {
        ValidatorUtils.nonNegativeMoney(cost);
        this.cost = cost;
    }
}
