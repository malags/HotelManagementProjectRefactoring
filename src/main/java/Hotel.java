import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Hotel {

    final Room[] rooms;

    final private static Scanner sc = new Scanner(System.in);

    public enum RoomType {DoubleLuxury, DoubleNotLuxury, SingleLuxury, SingleNotLuxury}

    public static RoomType intToRoomType(int roomType) {
        int index = roomType - 1;
        if(index < 0 || index > RoomType.values().length){
            System.out.println("Enter valid option");
            throw new IllegalArgumentException("The selected room type doesn't exist");
        }
        return RoomType.values()[index];
    }

    private Room getRoomByNumber(int roomNumber){
        return rooms[roomNumber - 1];
    }

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

    private static Client createClient(String customerPosition) {
        String name, contact, gender;
        System.out.println("\nEnter " + customerPosition + "customer name:");
        name = sc.next();
        System.out.println("Enter contact number:");
        contact = sc.next();
        System.out.println("Enter gender:");
        gender = sc.next();
        return new Client(name, contact, gender);
    }

    public boolean roomIsAvailable(int roomNumber) {
        return rooms[roomNumber - 1].isEmpty();
    }


    public Client[] StdInClientsForRoom(int roomNumber) {

        Client client1 = createClient("first ");
        Client client2 = null;
        Room room = rooms[roomNumber - 1];

        //if doubleroom
        if (room instanceof DoubleRoom) {
            client2 = createClient("second ");
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

    public void printAvailableRoomNumber(RoomType roomType) {
        System.out.println("\nChoose room number from: ");

        boolean doubleRoom = roomType == RoomType.DoubleNotLuxury || roomType == RoomType.DoubleLuxury;
        boolean luxury = roomType == RoomType.SingleNotLuxury || roomType == RoomType.SingleLuxury;
        StringBuilder roomsListBuilder = new StringBuilder();
        for (Room room : availableRooms(doubleRoom, luxury))
            roomsListBuilder.append(room.getRoomNumber() + ",");
        System.out.println(roomsListBuilder.subSequence(0,roomsListBuilder.length()));
    }

    //TODO: possibly use this instead
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


    static void features(int i) {
        RoomType roomType = intToRoomType(i);
        switch (roomType) {
            case DoubleLuxury:
                System.out.println("Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:4000 ");
                break;
            case DoubleNotLuxury:
                System.out.println("Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:3000  ");
                break;
            case SingleLuxury:
                System.out.println("Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:2200  ");
                break;
            case SingleNotLuxury:
                System.out.println("Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:1200 ");
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
    }


    int availability(RoomType roomType) {
        boolean doubleRoom = roomType == RoomType.DoubleLuxury || roomType == RoomType.DoubleNotLuxury;
        boolean luxury = roomType == RoomType.SingleLuxury || roomType == RoomType.DoubleLuxury;
        int count = 0;
        for (Room room : rooms) {
            count += room.countAvailable(doubleRoom, luxury);
        }
        return count;
    }

    private static void billPrinting(Room room) {
        System.out.println("\nRoom Charge - " + room.getCharge());
        System.out.println("\n===============");
        System.out.println("Food Charges:- ");
        System.out.println("===============");
        System.out.println("Item   Quantity    Price");
        System.out.println("-------------------------");
        for(Food food: room.getFoods())
        {
            String format = "%-10s%-10s%-10s%n";
            System.out.printf(format,food.getItemName(),food.getQuantity(),food.getPrice() * food.getQuantity());
        }
    }

    private void calculateBill(Room room) {
        double amount = room.getCharge();
        billPrinting(room);

        for (Food food : room.getFoods()) {
            amount += food.getPrice();
        }
        System.out.println("\nTotal Amount- " + amount);
    }

    private void bill(Room room) {
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");
        calculateBill(room);
    }

    void checkout(int roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        if(room.isEmpty()){
            System.out.println("The room is already empty");
            return;
        }

        System.out.println("Room used by " + room.getClients()[0].getName());
        System.out.println("Do you want to checkout ?(y/n)");
        char reply = sc.next().charAt(0);
        if(reply == 'y'){
            bill(room);
            room.setEmpty();
            System.out.println("Deallocated successfully");
        }
        else{
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
            System.out.println("\n==========\nMenu:\n==========\n");

            for(Food.FoodType foodType : Food.FoodType.values())
                System.out.format("%s.%-15sRs.%s\n", new Object[]{foodType.ordinal() + 1, foodType.name(), foodType.getPrice()});

            System.out.print("\nSelect Item");
            int itemNr = sc.nextInt();

            if(itemNr < 1 || itemNr > 4) {
                System.out.println("Invalid Item");
                return;
            }

            System.out.print("Select Quantity");
            int quantity = sc.nextInt();
            if(quantity < 1) {
                System.out.println("Invalid Quantity");
                return;
            }

            room.getFoods().add(new Food(Food.FoodType.values()[itemNr], quantity));

            System.out.println("Do you want to order anything else ? (y/n)");
            wish = sc.next().charAt(0);
        } while (wish == 'y' || wish == 'Y');

    }
}
