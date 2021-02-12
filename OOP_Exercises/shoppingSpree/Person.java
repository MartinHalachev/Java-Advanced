package shoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        ValidatorUtils.nonNegativeName(name);
        this.name = name;
    }

    private void setMoney(double money) {
        ValidatorUtils.nonNegativeMoney(money);
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {
        if (this.money > product.getCost()) {
            products.add(product);
            money -= product.getCost();
            System.out.printf("%s bought %s%n",
                    this.name,
                    product.getName());
        } else {
            System.out.printf("%s can't afford %s%n",
                    this.name,
                    product.getName());
        }
    }
}
