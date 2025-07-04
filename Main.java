import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void checkout(Customer customer, Cart cart) {
        Map<Product, Integer> products = cart.getProducts();
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        for(Product product : products.keySet()) {
            if (product.isExpired()) {
                throw new IllegalArgumentException(product.getName() + " is expired.");
            }
        }

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (quantity > product.getQuantity()) {
                throw new IllegalArgumentException("Quantity of " + product.getName() + " exceeds available stock.");
            }
        }

        double subtotal = 0;
        Map<Shippable, Integer> itemsToShip = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof Shippable) {
                subtotal += product.getPrice() * quantity;
                Shippable shippableProduct = (Shippable) product;
                itemsToShip.put(shippableProduct,quantity);
            }
        }

        double shipping = itemsToShip.isEmpty() ? 0 : 30;
        double total = subtotal + shipping;

        if (total > customer.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        if (!itemsToShip.isEmpty()) {
            ShippingService shippingService = new ShippingService();
            shippingService.ship(itemsToShip);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            product.setQuantity(product.getQuantity() - entry.getValue());
            int quantity = entry.getValue();
            double price = product.getPrice() * quantity;
            System.out.println(quantity + "x " + product.getName() + "\t"+price);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t"+subtotal);
        System.out.println("Shipping\t"+ shipping);
        System.out.println("Amount\t"+ total);

        customer.deductBalance(total);
    }

    public static void main(String[] args) {
        Customer customer = new Customer("John Doe", "john.doe@email.com", "1234567890", 3530.0);

        Cart cart = new Cart();

        Product cheese = new Product("Cheese", 20.0, 10, null);
        Product tv = new ShippableProduct("TV", 1000.0, 5, null, 5000.0);
        Product scratchCard = new Product("Scratch Card", 5.0, 100, null);

        cart.add(cheese, 2);
        cart.add(tv, 3);
        cart.add(scratchCard, 1);


        try {
            checkout(customer, cart);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
