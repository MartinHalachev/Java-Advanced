package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.BaseComputer;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.HashMap;
import java.util.Map;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computers;

    public ControllerImpl() {
        this.computers = new HashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computers.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        if (!computerType.equals("Laptop") && !computerType.equals("DesktopComputer")) {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        Computer computer = null;
        switch (computerType) {
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
        }
        computers.put(id, computer);
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        computerExist(computerId);

        if (!componentType.equals("CentralProcessingUnit") && !componentType.equals("Motherboard")
                && !componentType.equals("PowerSupply") && !componentType.equals("RandomAccessMemory")
                && !componentType.equals("SolidStateDrive") && !componentType.equals("VideoCard")) {
            throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        Component component = null;

        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
        }
        for (Component comp : computers.get(computerId).getComponents()) {
            if (comp.getId() == id) {
                throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
            }
        }
        computers.get(computerId).addComponent(component);
        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        computerExist(computerId);

        if (!peripheralType.equals("Headset") && !peripheralType.equals("Keyboard")
                && !peripheralType.equals("Monitor") && !peripheralType.equals("Mouse")) {
            throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }

        Peripheral peripheral = null;

        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
        }
        for (Peripheral peri : computers.get(computerId).getPeripherals()) {
            if (peri.getId() == id) {
                throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
            }
        }
        computers.get(computerId).addPeripheral(peripheral);
        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        computerExist(computerId);
        int id = computers.get(computerId).removePeripheral(peripheralType).getId();
        return String.format(REMOVED_PERIPHERAL, peripheralType, id);
    }


    @Override
    public String removeComponent(String componentType, int computerId) {
        computerExist(computerId);
        int id = computers.get(computerId).removeComponent(componentType).getId();
        return String.format(REMOVED_COMPONENT, componentType, id);
    }

    @Override
    public String buyComputer(int id) {
        computerExist(id);
        Computer removed = computers.remove(id);
        return removed.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        boolean enoughBudget = false;
        for (Computer value : computers.values()) {
            if (value.getPrice() <= budget) {
                enoughBudget = true;
                break;
            }
        }
        if (computers.isEmpty() || !enoughBudget) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }
        Computer currentBest = new Laptop(1, "Martin", "MartinModel", 1);
        int id = 0;
        for (Computer computer : computers.values()) {
            if (currentBest.getPrice() <= computer.getPrice() && computer.getPrice() <= budget) {
                currentBest = computer;
                id = computer.getId();
            }
        }
        return computers.remove(id).toString();

    }

    @Override
    public String getComputerData(int id) {
        computerExist(id);
        return computers.get(id).toString();
    }

    private void computerExist(int id) {
        if (!computers.containsKey(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
    }
}
