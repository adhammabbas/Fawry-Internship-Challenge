import java.util.Date;

public class ShippableProduct extends Product implements Shippable {
    private double weight; // in grams

    public ShippableProduct(String name, double price, int quantity, Date expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
