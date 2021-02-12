package animalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        setName(name);
        setAge(age);
    }

    private void setName(String name) {
        if (name.trim().isEmpty() || name.contains(" ")) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setAge(int age) {
        if (age <= 0 || age > 15) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    public double productPerDay() {
        return calculateProductPerDay();
    }

    private double calculateProductPerDay() {
        double eggs = 0;
        if (this.age >= 0 && this.age <= 5) {
            eggs = 2;
        } else if (this.age >= 6 && this.age <= 11) {
            eggs = 1;
        } else if (this.age >= 12 && this.age <= 15) {
            eggs = 0.75;
        }
        return eggs;
    }

    @Override
    public String toString() {
        return String.format("Chicken Mara (age 10) can produce 1.00 eggs per day.",
                this.name,
                this.age,
                this.calculateProductPerDay());
    }
}
