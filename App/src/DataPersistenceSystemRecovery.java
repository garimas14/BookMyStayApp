import java.io.*;
import java.util.*;

// Serializable Inventory Class



// Persistence Service
class PersistenceService {
    private static final String FILE_NAME = "inventory.dat";

    // SAVE
    public static void save(RoomInventory inventory) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            System.out.println("inventory saved successfully");

        } catch (Exception e) {
            System.out.println("error saving inventory");
        }
    }

    // LOAD
    public static RoomInventory load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (RoomInventory) ois.readObject();

        } catch (Exception e) {
            System.out.println("no valid inventory data found. Starting fresh.");
            return new RoomInventory();
        }
    }
}

// MAIN CLASS
public class DataPersistenceSystemRecovery {

    public static void main(String[] args) {

        System.out.println("system recovery");

        // Load previous state
        RoomInventory inventory = PersistenceService.load();

        // Display inventory
        System.out.println("Current Inventory:");
        System.out.println("single:" + inventory.getAvailability("Single Room"));
        System.out.println("double: " + inventory.getAvailability("Double Room"));
        System.out.println("suite :" + inventory.getAvailability("Suite Room"));

        // Save current state
        PersistenceService.save(inventory);
    }
}