package internationalExtended;

public abstract class BasePerson implements Person{
    private String name;

    protected BasePerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
