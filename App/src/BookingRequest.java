import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public void displayRequests() {
        System.out.println("Booking Requests (FIFO Order):\n");

        for (Reservation r : queue) {
            System.out.println("Guest: " + r.getGuestName() + " | Room Type: " + r.getRoomType());
        }
    }
}

public class BookingRequest {
    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Garima", "Single Room"));
        requestQueue.addRequest(new Reservation("Rahul", "Double Room"));
        requestQueue.addRequest(new Reservation("Ananya", "Suite Room"));

        requestQueue.displayRequests();
    }
}