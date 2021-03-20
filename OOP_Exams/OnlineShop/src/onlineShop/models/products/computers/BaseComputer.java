package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        }
        return super.getOverallPerformance() + components.stream().mapToDouble(Component::getOverallPerformance).average().orElse(0);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + components.stream().mapToDouble(Component::getPrice).sum()
                + peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (components.contains(component)) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, component.getClass().getSimpleName(),
                    this.getClass().getSimpleName(),
                    getId()));
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        boolean containsComponent = false;
        Component removed = null;
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                containsComponent = true;
                removed = component;
                break;
            }
        }
        if (components.isEmpty() || !containsComponent) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType,
                    this.getClass().getSimpleName(),
                    getId()));
        }

        components.remove(removed);
        return removed;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.contains(peripheral)) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(),
                    this.getClass().getSimpleName(),
                    getId()));
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        boolean containsPeripheral = false;
        Peripheral removed = null;
        for (Peripheral peripheral : peripherals) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                containsPeripheral = true;
                removed = peripheral;
                break;
            }
        }
        if (peripherals.isEmpty() || !containsPeripheral) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType,
                    this.getClass().getSimpleName(),
                    getId()));
        }

        peripherals.remove(removed);
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Overall Performance: %.2f. Price: %.2f - %s: " +
                        "%s %s (Id: %d)",
                getOverallPerformance(),
                getPrice(),
                this.getClass().getSimpleName(),
                this.getManufacturer(),
                this.getModel(),
                getId()));

        result.append(System.lineSeparator());
        result.append(String.format(" Components (%d):", components.size()));
        result.append(System.lineSeparator());
        for (Component component : components) {
            result.append("  ");
            result.append(component.toString());
            result.append(System.lineSeparator());
        }
        result.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):",
                peripherals.size(),
                peripherals.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0)));
        result.append(System.lineSeparator());
        for (Peripheral peripheral : peripherals) {
            result.append("  ");
            result.append(peripheral.toString());
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}
