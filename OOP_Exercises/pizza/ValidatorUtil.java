package pizza;

public class ValidatorUtil {
    public static void checkFlourTypeAndTechnique(String input) {
        if (!input.equals("White") && !input.equals("Wholegrain") &&
                !input.equals("Crispy") && !input.equals("Chewy") && !input.equals("Homemade")) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public static void checkDoughWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
    }

    public static void checkTopping(String topping) {
        if (!topping.equals("Meat") && !topping.equals("Veggies")
                && !topping.equals("Cheese") && !topping.equals("Sauce")) {
            throw new IllegalArgumentException(
                    String.format("Cannot place %s on top of your pizza.", topping));
        }
    }

    public static void checkToppingWeight(String topping, double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", topping));
        }
    }

    public static void checkPizza(String pizza) {
        if (pizza.trim().isEmpty() || pizza.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
    }

    public static void checkToppingSize(int size) {
        if (size < 1 || size > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
    }
}
