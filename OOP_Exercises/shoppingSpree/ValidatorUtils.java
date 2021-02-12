package shoppingSpree;

public class ValidatorUtils {
    public static void nonNegativeName(String name) {
        if (name.trim().isEmpty() || name.contains(" ")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public static void nonNegativeMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }
}
