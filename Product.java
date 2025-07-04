import java.util.Date;

public class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected Date expiryDate;

    public Product(String name, double price, int quantity, Date expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        if (expiryDate == null) return false;
        return expiryDate.before(new Date());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        this.quantity = q;
    }
}
