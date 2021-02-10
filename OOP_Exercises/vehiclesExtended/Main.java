package vehiclesExtended;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] carInput = scanner.nextLine().split(" ");
        String[] truckInput = scanner.nextLine().split(" ");
        String[] busInput = scanner.nextLine().split(" ");

        Vehicle car = new Car(Double.parseDouble(carInput[1]), Double.parseDouble(carInput[2]), Double.parseDouble(carInput[3]));
        Vehicle truck = new Truck(Double.parseDouble(truckInput[1]), Double.parseDouble(truckInput[2]), Double.parseDouble(truckInput[3]));
        Vehicle bus = new Bus(Double.parseDouble(busInput[1]), Double.parseDouble(busInput[2]), Double.parseDouble(busInput[3]));

        int rotations = Integer.parseInt(scanner.nextLine());

        boolean added = false;
        while (rotations-- != 0) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].equals("Drive")) {
                try {
                    switch (command[1]) {
                        case "Car":
                            System.out.println(car.drive(Double.parseDouble(command[2])));
                            break;
                        case "Truck":
                            System.out.println(truck.drive(Double.parseDouble(command[2])));
                            break;
                        case "Bus":
                            if (!added) {
                                bus.setFuelConsumptionPerKm(Double.parseDouble(busInput[2]) + 1.4);
                                added = true;
                            }
                            System.out.println(bus.drive(Double.parseDouble(command[2])));
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command[0].equals("Refuel")) {
                try {
                    switch (command[1]) {
                        case "Car":
                            car.refuel(Double.parseDouble(command[2]));
                            break;
                        case "Truck":
                            truck.refuel(Double.parseDouble(command[2]));
                            break;
                        case "Bus":
                            bus.refuel(Double.parseDouble(command[2]));
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command[0].equals("DriveEmpty")) {
                try {
                    System.out.println(bus.drive(Double.parseDouble(command[2])));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n", truck.getFuelQuantity());
        System.out.printf("Bus: %.2f", bus.getFuelQuantity());

    }
}
