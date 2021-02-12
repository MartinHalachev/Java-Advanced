package classBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void setLength(double length) {
        nonNegativeInput("Length", length);
        this.length = length;
    }

    private void setWidth(double width) {
        nonNegativeInput("Width", width);
        this.width = width;
    }

    private void setHeight(double height) {
        nonNegativeInput("Height", height);
        this.height = height;
    }

    public double calculateSurfaceArea() {
        return 2 * (this.length * this.width) + calculateLateralSurfaceArea();
    }

    public double calculateLateralSurfaceArea() {
        return 2 * (this.length * this.height) + 2 * (this.width * this.height);
    }

    public double calculateVolume() {
        return this.length * this.height * this.width;
    }

    private void nonNegativeInput(String type, double number) {
        if (number <= 0) {
            throw new IllegalArgumentException(String.format("%s cannot be zero or negative.", type));
        }
    }
}
