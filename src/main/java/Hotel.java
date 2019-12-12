import ch.usi.si.codelounge.jsicko.Contract;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing a Hotel object.
 */
public class Hotel implements Contract{

//    @Pure
//    @Invariant
//    protected boolean hotel_size_is_60(){
//        return rooms.length == 60;
//    }

    /**
     * Fields that represent the hotel object.
     */
    protected final Room[] rooms;
    protected final static Scanner scanner = new Scanner(System.in);

    /**
     * @param rooms Constructor in which an array of rooms is passed as a parameter.
     */
    public Hotel(Room[] rooms) {
        this.rooms = rooms;
    }

    /**
     * Constructor that initializes the array of rooms.
     */
    public Hotel() {
        //can factor out some more
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            rooms.add(new DoubleRoom(null, null, i + 1, true));
            rooms.add(new SingleRoom(null, i + 11, true));
        }
        for (int i = 20; i < 40; ++i) {
            rooms.add(new DoubleRoom(null, null, i + 1, false));
            rooms.add(new SingleRoom(null, i + 21, false));
        }
        rooms.sort(Comparator.comparingInt(Room::getRoomNumber));
        this.rooms = rooms.toArray(new Room[60]);
    }

    /**
     * @param roomNumber
     * @return an room from array depending on the value passed as a parameter.
     */
//    @Pure
//    @Requires("room_number_is_valid")
//    @Ensures("room_has_room_number")
    private Room getRoomByNumber(int roomNumber) {
        return this.rooms[roomNumber - 1];
    }

//    @Pure
    protected boolean room_number_is_valid(int roomNumber){
        return roomNumber >= 0 && roomNumber <= 60;
    }

//    @Pure
    protected boolean room_has_room_number(Room returns, int roomNumber){
        return returns.roomNumber == roomNumber;
    }

    /**
     * @param roomNumber
     * @return a true value if the room is available, otherwise false.
     */
//    @Pure
    public boolean roomIsAvailable(int roomNumber) {
        return getRoomByNumber(roomNumber).isEmpty();
    }

    /**
     * @param roomNumber
     * @return method to initialize client array.
     */
//    @Pure
    public Client[] StdInClientsForRoom(int roomNumber) {

        Client client1 = Client.createClientFromInput("first ");
        Client client2;
        Room room = getRoomByNumber(roomNumber);

        if (room.isDoubleRoom()) {
            client2 = Client.createClientFromInput("second ");
            return new Client[]{client1, client2};
        }

        return new Client[]{client1};
    }

    /**
     * @param doubleRoom
     * @param luxury
     * @return list of rooms available.
     */
//    @Pure
//    @Ensures("rooms_are_available")
    public List<Room> availableRooms(boolean doubleRoom, boolean luxury) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms)
            if (room.isEmpty() && (room.isLuxury() == luxury) && (room instanceof DoubleRoom == doubleRoom)) {
                availableRooms.add(room);
            }
        return availableRooms;
    }

    boolean rooms_are_available(List<Room> returns){
        for(Room room : returns)
            if(!room.isEmpty())
                return false;
        return true;
    }


    /**
     * @param roomNumber
     * @param clients    method to book a room.
     */
//    @Requires("room_is_empty")
//    @Ensures("!room_is_empty")
    public void bookRoom(int roomNumber, Client[] clients) {
        Room room = getRoomByNumber(roomNumber);
        if (room.clients.length < clients.length) {
            System.out.println("Room " + roomNumber + " can't fit that many people");
            return;
        }
        if (!room.isEmpty()) {
            System.out.println("Room " + roomNumber + " isn't available");
            return;
        }
        System.out.println("Room Booked");
        room.book(clients);
    }

    /**
     * @param roomType
     * @return the number of rooms available.
     */
//    @Pure
    int availability(Room.RoomType roomType) {
        boolean doubleRoom = roomType.isDoubleRoom();
        boolean luxury = roomType.isLuxuryRoom();
        int count = 0;
        for (Room room : rooms) {
            count += room.countAvailable(doubleRoom, luxury);
        }
        return count;
    }

    /**
     * @param room
     * @return the value of a room bill.
     */
//    @Pure
//    @Requires("!room_is_empty")
    protected long calculateBill(Room room) {
        long amount = room.getCharge();
        for (Food food : room.getFoods()) {
            amount += food.getPrice();
        }
        return amount;
    }

    /**
     * @param room Method to print room bill.
     */
//    @Pure
//    @Requires("!room_is_empty")
    protected void bill(Room room) {
        Logger.printRoomBill(room, calculateBill(room));
    }

//    @Pure
    protected boolean room_is_empty(Room room){
        return room.isEmpty();
    }

    /**
     * @param roomNumber Method to free up a busy room.
     */
//    @Ensures("room_number_is_empty")
    protected void checkout(int roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        if (room.isEmpty()) {
            System.out.println("The room is already empty");
            return;
        }

        System.out.println("Room used by " + room.getClients()[0].getName());
        System.out.println("Do you want to checkout ?(y/n)");
        char reply = scanner.next().toLowerCase().charAt(0);
        if (reply == 'y') {
            bill(room);
            room.setEmpty();
            System.out.println("Deallocated successfully");
        } else {
            System.out.println("Checkout cancelled");
        }
    }

//    @Pure
    protected boolean room_number_is_empty(int roomNumber){
        return this.rooms[roomNumber-1].isEmpty();
    }

    /**
     * @param roomNumber Method to order a food for a room.
     *                   The room number depends on the value passed as a parameter.
     */
//    @Requires("!room_number_is_empty")
    protected void order(int roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        if (room.isEmpty()) {
            System.out.println("The room is not booked");
            return;
        }
        char wish;

        do {
            Logger.printFoodMenu();

            System.out.print("\nSelect Item ");
            int itemNr = scanner.nextInt();

            if (itemNr < 1 || itemNr > 4) {
                System.out.println("Invalid Item");
                return;
            }

            System.out.print("Select Quantity ");
            int quantity = scanner.nextInt();
            if (quantity < 1) {
                System.out.println("Invalid Quantity");
                return;
            }

            room.addFood(new Food(Food.getFoodType(itemNr), quantity));

            System.out.println("Do you want to order anything else ? (y/n)");
            wish = scanner.next().toLowerCase().charAt(0);
        } while (wish == 'y');
    }
}
