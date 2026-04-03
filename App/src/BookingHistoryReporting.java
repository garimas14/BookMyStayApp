import java.util.*;

class BookingHistory {
    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void displayAllBookings() {
        System.out.println("Booking History and Reporting");
        System.out.println("Booking History Report");

        List<Reservation> list = history.getAllReservations();

        for (Reservation r : list) {
            System.out.println("guest:" + r.getGuestName().toLowerCase()
                    + ",room type:" + r.getRoomType().toLowerCase());
        }
    }
}

public class BookingHistoryReporting {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Single"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService(history);
        reportService.displayAllBookings();
    }
}