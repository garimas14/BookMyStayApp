import java.util.*;

class RoomAllocationService {
    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms;
    private int roomCounter = 1;

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
    }

    public void processBookings(Queue<Reservation> queue) {
        System.out.println("Processing Bookings:\n");

        while (!queue.isEmpty()) {
            Reservation request = queue.poll();
            String roomType = request.getRoomType();

            int available = inventory.getAvailability(roomType);

            if (available > 0) {
                String roomId = generateRoomId(roomType);

                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                allocatedRooms.get(roomType).add(roomId);

                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Booking Confirmed for " + request.getGuestName()
                        + " | Room Type: " + roomType
                        + " | Room ID: " + roomId);
            } else {
                System.out.println("Booking Failed for " + request.getGuestName()
                        + " | Room Type: " + roomType + " (No availability)");
            }
        }
    }

    private String generateRoomId(String roomType) {
        return roomType.substring(0, 1).toUpperCase() + roomCounter++;
    }
}

public class ReservationConfirmationRoomAllocation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Garima", "Single Room"));
        queue.add(new Reservation("Rahul", "Double Room"));
        queue.add(new Reservation("Ananya", "Suite Room"));
        queue.add(new Reservation("Amit", "Suite Room"));

        RoomAllocationService service = new RoomAllocationService(inventory);
        service.processBookings(queue);
    }
}