package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;

public interface DrinkRepository<T> extends Repository<T> {
    T getByNameAndBrand(String drinkName, String drinkBrand);
}
