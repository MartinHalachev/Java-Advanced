package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] carInput = scanner.nextLine().split(" ");
        String[] truckInput = scanner.nextLine().split(" ");
        Car car = new Car(Double.parseDouble(carInput[1]), Double.parseDouble(carInput[2]));
        Truck truck = new Truck(Double.parseDouble(truckInput[1]), Double.parseDouble(truckInput[2]));

        int rotations = Integer.parseInt(scanner.nextLine());


        while (rotations-- != 0) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].equals("Drive")) {
                switch (command[1]) {
                    case "Car":
                        System.out.println(car.drive(Double.parseDouble(command[2])));
                        break;
                    case "Truck":
                        System.out.println(truck.drive(Double.parseDouble(command[2])));
                        break;
                }
            } else if (command[0].equals("Refuel")) {
                switch (command[1]) {
                    case "Car":
                        car.refuel(Double.parseDouble(command[2]));
                        break;
                    case "Truck":
                        truck.refuel(Double.parseDouble(command[2]));
                        break;
                }
            }
        }
        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f", truck.getFuelQuantity());

    }
}
