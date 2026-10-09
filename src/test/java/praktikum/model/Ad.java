package praktikum.model;

public class Ad {

    private final String name;
    private final String description;
    private final String price;

    public Ad(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}