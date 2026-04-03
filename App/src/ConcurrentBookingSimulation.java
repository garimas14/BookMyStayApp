import java.util.*;

class ConcurrentBookingProcessor {
    private RoomInventory inventory;
    private Queue<Reservation> queue;
    private Map<String, Integer> roomCounter;

    public ConcurrentBookingProcessor(RoomInventory inventory, Queue<Reservation> queue) {
        this.inventory = inventory;
        this.queue = queue;
        this.roomCounter = new HashMap<>();
    }

    public void processBookings() {
        while (true) {
            Reservation r;

            // synchronized queue access
            synchronized (queue) {
                if (queue.isEmpty()) return;
                r = queue.poll();
            }

            // critical section for inventory
            synchronized (inventory) {
                String type = r.getRoomType();
                int available = inventory.getAvailability(type);

                if (available > 0) {
                    int count = roomCounter.getOrDefault(type, 0) + 1;
                    roomCounter.put(type, count);

                    String roomId = type.split(" ")[0].toLowerCase() + " -" + count;

                    inventory.updateAvailability(type, available - 1);

                    System.out.println("booking confirmed for guest: "
                            + r.getGuestName().toLowerCase()
                            + ",room id: " + roomId);
                }
            }
        }
    }
}

public class ConcurrentBookingSimulation {
    public static void main(String[] args) {

        System.out.println("concurrent booking simulation");

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Abhi", "Single Room"));
        queue.add(new Reservation("Vanmathi", "Double Room"));
        queue.add(new Reservation("Kural", "Suite Room"));
        queue.add(new Reservation("Subha", "Single Room"));

        ConcurrentBookingProcessor processor =
                new ConcurrentBookingProcessor(inventory, queue);

        // multiple threads
        Thread t1 = new Thread(() -> processor.processBookings());
        Thread t2 = new Thread(() -> processor.processBookings());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("remaing inventory:");
        System.out.println("single:" + inventory.getAvailability("Single Room"));
        System.out.println("double :" + inventory.getAvailability("Double Room"));
        System.out.println("suite:" + inventory.getAvailability("Suite Room"));
    }
}