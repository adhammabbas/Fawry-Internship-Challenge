import java.util.Map;

public class ShippingService {
    public void ship(Map<Shippable, Integer> items) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        for (Map.Entry<Shippable, Integer> entry : items.entrySet()) {
            Shippable item = entry.getKey();
            int quantity = entry.getValue();
            double weight = item.getWeight() * quantity;
            String unit = "g";
            if (weight >= 1000) {
                weight /= 1000;
                unit = "kg";
            }
            System.out.println(quantity+"x " + item.getName()+"\t"+weight + unit);
            totalWeight += item.getWeight() * quantity;
        }
        String totalUnit = "g";
        if (totalWeight >= 1000) {
            totalWeight /= 1000;
            totalUnit = "kg";
        }
        System.out.println("Total package weight "+totalWeight+totalUnit+"\n");
    }
}
