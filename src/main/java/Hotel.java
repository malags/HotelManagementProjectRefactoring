import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Hotel {

    final Room[] rooms;

    final private static Scanner sc = new Scanner(System.in);


    public Hotel(Room[] rooms) {
        this.rooms = rooms;
    }

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

    private Room getRoomByNumber(int roomNumber) {
        return rooms[roomNumber - 1];
    }

    public boolean roomIsAvailable(int roomNumber) {
        return getRoomByNumber(roomNumber).isEmpty();
    }


    public Client[] StdInClientsForRoom(int roomNumber) {

        Client client1 = Client.createClientFromInput("first ");
        Client client2 = null;
        Room room = getRoomByNumber(roomNumber);

        if (room.isDoubleRoom()) {
            client2 = Client.createClientFromInput("second ");
            return new Client[]{client1, client2};
        }

        return new Client[]{client1};
    }

    public List<Room> availableRooms(boolean doubleRoom, boolean luxury) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms)
            if (room.isEmpty() && (room.isLuxury() == luxury) && (room instanceof DoubleRoom == doubleRoom)) {
                availableRooms.add(room);
            }
        return availableRooms;
    }


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


    int availability(Room.RoomType roomType) {
        boolean doubleRoom = roomType.isDoubleRoom();
        boolean luxury = roomType.isLuxuryRoom();
        int count = 0;
        for (Room room : rooms) {
            count += room.countAvailable(doubleRoom, luxury);
        }
        return count;
    }


    private long calculateBill(Room room) {
        long amount = room.getCharge();
        for (Food food : room.getFoods()) {
            amount += food.getPrice();
        }
        return amount;
    }

    private void bill(Room room) {
        Logger.printRoomBill(room, calculateBill(room));
    }

    void checkout(int roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        if (room.isEmpty()) {
            System.out.println("The room is already empty");
            return;
        }

        System.out.println("Room used by " + room.getClients()[0].getName());
        System.out.println("Do you want to checkout ?(y/n)");
        char reply = sc.next().charAt(0);
        if (reply == 'y') {
            bill(room);
            room.setEmpty();
            System.out.println("Deallocated successfully");
        } else {
            System.out.println("Checkout cancelled");
        }

    }

    void order(int roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        if (room.isEmpty()) {
            System.out.println("The room is not booked");
            return;
        }
        char wish;

        do {
            Logger.printFoodMenu();

            System.out.print("\nSelect Item ");
            int itemNr = sc.nextInt();

            if (itemNr < 1 || itemNr > 4) {
                System.out.println("Invalid Item");
                return;
            }

            System.out.print("Select Quantity ");
            int quantity = sc.nextInt();
            if (quantity < 1) {
                System.out.println("Invalid Quantity");
                return;
            }

            room.addFood(new Food(Food.getFoodType(itemNr), quantity));

            System.out.println("Do you want to order anything else ? (y/n)");
            wish = sc.next().charAt(0);
        } while (wish == 'y' || wish == 'Y');

    }
}
