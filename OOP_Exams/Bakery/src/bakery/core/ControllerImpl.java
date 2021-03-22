package bakery.core;

import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.bakedFoods.interfaces.Bread;
import bakery.entities.bakedFoods.interfaces.Cake;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.drinks.interfaces.Tea;
import bakery.entities.drinks.interfaces.Water;
import bakery.entities.tables.interfaces.InsideTable;
import bakery.entities.tables.interfaces.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import java.util.ArrayList;
import java.util.List;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private DrinkRepository<Drink> drinkRepository;
    private FoodRepository<BakedFood> foodRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome = 0;


    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addFood(String type, String name, double price) {
        for (BakedFood baseFood : foodRepository.getAll()) {
            if (baseFood.getName().equals(name)) {
                throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, name, type));
            }
        }
        BakedFood bakedFood = null;
        switch (type) {
            case "Bread":
                bakedFood = new Bread(name, price);
                break;
            case "Cake":
                bakedFood = new Cake(name, price);
                break;
        }
        foodRepository.add(bakedFood);
        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        for (Drink drink : drinkRepository.getAll()) {
            if (drink.getName().equals(name)) {
                throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
            }
        }
        switch (type) {
            case "Tea":
                drinkRepository.add(new Tea(name, portion, brand));
                break;
            case "Water":
                drinkRepository.add(new Water(name, portion, brand));
                break;
        }
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        for (Table table : tableRepository.getAll()) {
            if (table.getTableNumber() == tableNumber) {
                throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
            }
        }
        switch (type) {
            case "InsideTable":
                tableRepository.add(new InsideTable(tableNumber, capacity));
                break;
            case "OutsideTable":
                tableRepository.add(new OutsideTable(tableNumber, capacity));
                break;
        }
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        for (Table table : tableRepository.getAll()) {
            if (!table.isReserved() && table.getCapacity() >= numberOfPeople) {
                table.reserve(numberOfPeople);
                return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);

    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        BakedFood food = foodRepository.getByName(foodName);
        if (food == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }
        table.orderFood(food);
        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Drink drink = drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }
        if (tableRepository.getAll().stream().noneMatch(e -> e.getTableNumber() == tableNumber)) {
            throw new IllegalArgumentException(String.format(WRONG_TABLE_NUMBER, tableNumber));
        }
        tableRepository.getByNumber(tableNumber).orderDrink(drinkRepository.getByNameAndBrand(drinkName, drinkBrand));
        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (table == null) {
            throw new IllegalArgumentException(String.format(WRONG_TABLE_NUMBER, tableNumber));
        }
        this.totalIncome += tableRepository.getByNumber(tableNumber).getBill();
        String result = String.format(BILL, tableNumber, tableRepository.getByNumber(tableNumber).getBill());
        tableRepository.getByNumber(tableNumber).clear();
        return result;
    }

    @Override
    public String getFreeTablesInfo() {
        List<Table> freeTables = new ArrayList<>();
        for (Table table : tableRepository.getAll()) {
            if (!table.isReserved()) {
                freeTables.add(table);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Table freeTable : freeTables) {
            result.append(freeTable.getFreeTableInfo());
            result.append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, totalIncome);
    }
}
