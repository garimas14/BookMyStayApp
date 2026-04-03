import java.util.*;

class AddOnService {
    private String name;
    private int cost;

    public AddOnService(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public int calculateTotalCost(String reservationId) {
        int total = 0;
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        System.out.println("\nServices for Reservation ID: " + reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println(s.getName() + " - ₹" + s.getCost());
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

public class AddOnServiceSelection {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1200));
        manager.addService(reservationId, new AddOnService("Extra Bed", 800));

        manager.displayServices(reservationId);
    }
}