import java.util.*;

public class Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] liquidsInput = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] ingredientsInput = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> liquids = new ArrayDeque<>();
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();

        for (int liquid : liquidsInput) {
            liquids.offer(liquid);
        }
        for (int ingredient : ingredientsInput) {
            ingredients.push(ingredient);
        }
        Map<String, Integer> products = new TreeMap<>();
        products.put("Bread", 0);
        products.put("Cake", 0);
        products.put("Pastry", 0);
        products.put("Fruit Pie", 0);

        while (!ingredients.isEmpty() && !liquids.isEmpty()) {
            int sum = liquids.peek() + ingredients.peek();

            switch (sum) {
                case 25:
                    addProduct(products, "Bread", ingredients, liquids);
                    break;
                case 50:
                    addProduct(products, "Cake", ingredients, liquids);
                    break;
                case 75:
                    addProduct(products, "Pastry", ingredients, liquids);
                    break;
                case 100:
                    addProduct(products, "Fruit Pie", ingredients, liquids);
                    break;
                default:
                    liquids.poll();
                    ingredients.push(ingredients.pop() + 3);
                    break;

            }
        }
        if (hasAllProducts(products)) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to to cook everything.");
        }

        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            System.out.println("Liquids left: " + printWhatsLeft(liquids));
        }
        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            System.out.println("Ingredients left: " + printWhatsLeft(ingredients));
        }

        products.entrySet()
                .stream()
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

    }

    private static String printWhatsLeft(ArrayDeque<Integer> materials) {
        StringBuilder result = new StringBuilder();
        for (Integer material : materials) {
            result.append(material).append(", ");
        }
        return result.toString().substring(0, result.length() - 2).trim();
    }

    private static boolean hasAllProducts(Map<String, Integer> products) {
        boolean all = true;
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            if (entry.getValue() == 0) {
                all = false;
            }
        }
        return all;
    }

    private static void addProduct(Map<String, Integer> products, String product, ArrayDeque<Integer> ingredients, ArrayDeque<Integer> liquids) {
        products.put(product, products.get(product) + 1);
        ingredients.pop();
        liquids.poll();
    }
}
