public class CentralizedRoomInventoryManagement {

    static class SearchService {
        private RoomInventory inventory;

        public SearchService(RoomInventory inventory) {
            this.inventory = inventory;
        }

        public void displayAvailableRooms(Room[] rooms) {
            System.out.println("Available Rooms:\n");

            for (Room room : rooms) {
                int available = inventory.getAvailability(room.getRoomType());

                if (available > 0) {
                    room.displayRoomDetails();
                    System.out.println("Available Rooms: " + available + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        SearchService searchService = new SearchService(inventory);
        searchService.displayAvailableRooms(rooms);
    }
}