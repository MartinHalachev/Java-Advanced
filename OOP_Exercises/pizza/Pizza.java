package pizza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        setName(name);
        ValidatorUtil.checkToppingSize(numberOfToppings);
        this.toppings = new ArrayList<>(numberOfToppings);
    }

    private void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    private void setName(String name) {
        ValidatorUtil.checkPizza(name);
        this.name = name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public double getOverallCalories() {
        return this.dough.calculateCalories() + this.toppings.stream()
                .mapToDouble(Topping::calculateCalories).sum();
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", this.name, getOverallCalories());
    }
}
