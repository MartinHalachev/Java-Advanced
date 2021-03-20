package onlineShop.models.products.components;

public class CentralProcessingUnit extends BaseComponent {
    private final static double OVERALL_PERFORMANCE_MULTIPLIER = 1.25;

    public CentralProcessingUnit(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * OVERALL_PERFORMANCE_MULTIPLIER, generation);
    }
}
