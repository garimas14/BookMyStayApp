import java.util.*;

class CancellationService {
    private RoomInventory inventory;
    private Stack<String> rollbackStack;

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.rollbackStack = new Stack<>();
    }

    public void cancelBooking(String roomType, String roomId) {

        if (roomType == null || roomType.isEmpty()) {
            System.out.println("invalid cancellation request");
            return;
        }

        // push to rollback stack (LIFO)
        rollbackStack.push(roomType + " -" + roomId);

        // restore inventory
        int current = inventory.getAvailability(roomType);
        inventory.updateAvailability(roomType, current + 1);

        System.out.println("booking cancellation");
        System.out.println("booking cancelled successfully. inventory restored for room type :"
                + roomType.toLowerCase());

        System.out.println("rollback history(most recent first ):");

        while (!rollbackStack.isEmpty()) {
            System.out.println("released reservation id: " + rollbackStack.pop());
        }

        System.out.println("updated " + roomType.toLowerCase() + " room availability:"
                + inventory.getAvailability(roomType));
    }
}

public class BookingCancellationInventoryRollback {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        CancellationService service = new CancellationService(inventory);

        // simulate cancellation
        service.cancelBooking("Single Room", "1");
    }
}