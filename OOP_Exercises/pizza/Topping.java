package pizza;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        ValidatorUtil.checkToppingWeight(toppingType, weight);
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setWeight(double weight) {
        this.weight = weight;
    }

    private void setToppingType(String toppingType) {
        ValidatorUtil.checkTopping(toppingType);
        this.toppingType = toppingType;
    }

    private String getToppingType() {
        return toppingType;
    }

    public double calculateCalories() {
        double calories = this.weight * 2;

        switch (getToppingType()) {
            case "Meat":
                calories *= Toppings.MEAT.getModifier();
                break;
            case "Veggies":
                calories *= Toppings.VEGGIES.getModifier();
                break;
            case "Cheese":
                calories *= Toppings.CHEESE.getModifier();
                break;
            case "Sauce":
                calories *= Toppings.SAUCE.getModifier();
                break;

        }
        return calories;
    }
}
