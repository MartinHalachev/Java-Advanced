package bakery.repositories.interfaces;

import bakery.entities.drinks.interfaces.BaseDrink;
import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    private Collection<Drink> drinks;

    public DrinkRepositoryImpl() {
        this.drinks = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        for (Drink drink : drinks) {
            if (drink.getBrand().equals(drinkBrand) && drink.getName().equals(drinkName)) {
                return drink;
            }
        }
        return null;
    }

    @Override
    public Collection<Drink> getAll() {
        return this.drinks;
    }

    @Override
    public void add(Drink drink) {
        this.drinks.add(drink);
    }
}
