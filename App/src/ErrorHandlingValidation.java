import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {

    public void validate(String roomType) throws InvalidBookingException {

        List<String> validTypes = Arrays.asList("single", "double", "suite");

        if (!validTypes.contains(roomType.toLowerCase())) {
            throw new InvalidBookingException("invalid room type selected");
        }
    }
}

public class ErrorHandlingValidation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookingValidator validator = new BookingValidator();

        try {
            System.out.print("booking validation \n");

            System.out.print("enter guest name : ");
            String name = sc.nextLine();

            System.out.print("enter room type(single/double/suite):");
            String roomType = sc.nextLine();

            validator.validate(roomType);

            System.out.println("booking successful for " + name);

        } catch (InvalidBookingException e) {
            System.out.println("booking failed:" + e.getMessage());
        }

        sc.close();
    }
}