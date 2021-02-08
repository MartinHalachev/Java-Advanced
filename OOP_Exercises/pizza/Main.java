package pizza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String[] pizzaData = scanner.nextLine().split(" ");
            Pizza pizza = new Pizza(pizzaData[1], Integer.parseInt(pizzaData[2]));
            
            String[] doughData = scanner.nextLine().split(" ");
            Dough dough = new Dough(doughData[1], doughData[2], Double.parseDouble(doughData[3]));
            pizza.setDough(dough);

            String[] readTopping = scanner.nextLine().split(" ");
            while (!readTopping[0].equals("END")) {
                Topping topping = new Topping(readTopping[1], Double.parseDouble(readTopping[2]));
                pizza.addTopping(topping);
                readTopping = scanner.nextLine().split(" ");
            }
            System.out.println(pizza.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
