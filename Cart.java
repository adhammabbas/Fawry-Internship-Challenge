import java.util.Map;
import java.util.HashMap;

public class Cart {
    private Map<Product,Integer> products = new HashMap<>();

    public void add(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Quantity exceeds available stock.");
        }
        products.put(product, quantity);
    }

    public Map<Product,Integer> getProducts() {
        return products;
    }
}
