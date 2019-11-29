import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Hotel {

    final Room[] rooms;

    final private static Scanner sc = new Scanner(System.in);

    public enum RoomType {DoubleLuxury, DoubleNotLuxury, SingleLuxury, SingleNotLuxury}

    public static RoomType intToRoomType(int roomType) {
        return RoomType.values()[roomType - 1];
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
            //room.book(client1,client2);
        }

        return new Client[]{client1};
        //room.book(client1);
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
        for (Room room : availableRooms(doubleRoom, luxury))
            System.out.print(room.getRoomNumber() + ",");
    }

    //TODO: possibly use this instead
    public void bookRoom(int roomNumber, Client[] clients) {
        Room room = rooms[roomNumber - 1];
        if (room.clients.length < clients.length) {
            System.out.println("Room " + roomNumber + " can't fit that many people");
            return;
        }
        if (!room.isEmpty()) {
            System.out.println("Room " + roomNumber + " isn't available");
            return;
        }
        System.out.println("Room Booked");
        rooms[roomNumber - 1].book(clients);
    }


    static void features(int i) {
        //TODO: refactor
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

    private static void billPrinting(double value) {
        System.out.println("\nRoom Charge - " + value);
        System.out.println("\n===============");
        System.out.println("Food Charges:- ");
        System.out.println("===============");
        System.out.println("Item   Quantity    Price");
        System.out.println("-------------------------");
    }

    private static double calculateBill(double initialAmount, Room rooms, String[] list) {
        double amount = initialAmount;
        for (Food food : rooms.getFoods()) {
            amount += food.getPrice();
            String format = "%-10s%-10s%-10s%n";
            System.out.printf(format, list[food.getItemno() - 1], food.getQuantity(), food.getPrice());
        }
        return amount;
    }

    private void bill(int roomNumber, int roomType2) {
        //TODO: refactor
        RoomType roomType = Hotel.intToRoomType(roomType2);
        double amount = 0;
        String[] list = {"Sandwich", "Pasta", "Noodles", "Coke"};
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (roomType) {
            case DoubleLuxury:
                amount += 4000;
                billPrinting(4000);
                amount += calculateBill(amount, rooms[roomNumber - 1], list);
                break;
            case DoubleNotLuxury:
                amount += 3000;
                billPrinting(3000);
                amount += calculateBill(amount, rooms[roomNumber - 1], list);
                break;
            case SingleLuxury:
                amount += 2200;
                billPrinting(2200);
                amount += calculateBill(amount, rooms[roomNumber - 1], list);
                break;
            case SingleNotLuxury:
                amount += 1200;
                billPrinting(1200);
                amount += calculateBill(amount, rooms[roomNumber - 1], list);
                break;
            default:
                System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount- " + amount);
    }

    void deallocate(int roomNumber) {
        rooms[roomNumber - 1].setEmpty();
    }

    void order(int roomNumber) {
        int i, q;
        char wish;
        try {
            System.out.println("\n==========\nMenu:\n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
            do {
                i = sc.nextInt();
                System.out.print("Quantity- ");
                q = sc.nextInt();

                if (rooms[roomNumber - 1].isEmpty()) {
                    System.out.println("The room is not booked");
                    return;
                }

                rooms[roomNumber - 1].getFoods().add(new Food(i, q));

                System.out.println("Do you want to order anything else ? (y/n)");
                wish = sc.next().charAt(0);
            } while (wish == 'y' || wish == 'Y');
        } catch (Exception e) {
            System.out.println("Cannot be done");
        }
    }
}
