package international;

public class Chinese implements Person {
    private String name;

    public Chinese(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String sayHello() {
        return "Djydjybydjy";
    }
}
