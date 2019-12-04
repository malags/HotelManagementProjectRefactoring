import ch.usi.si.codelounge.jsicko.Contract;

/**
 * Interface used to print different types of information on screen.
 */
public interface Logger extends Contract {

    /**
     * @param room
     * @param total Method to print room bill on screen.
     */
    static void printRoomBill(Room room, long total) {
        System.out.println("\n*******");
        System.out.println(" Bill:-" + room.getClients()[0].getName());
        System.out.println("*******");
        System.out.println("\nRoom Charge - " + room.getCharge());
        System.out.println("\n===============");
        System.out.println("Food Charges:- ");
        System.out.println("===============");
        System.out.println("Item   Quantity    Price");
        System.out.println("-------------------------");
        for (Food food : room.getFoods()) {
            String format = "%-10s%-10s%-10s%n";
            System.out.printf(format, food.getItemName(), food.getQuantity(), food.getPrice());
        }
        System.out.println("\nTotal Amount- " + total);
    }

    /**
     * @param i Method to print room features on screen.
     */
    static void roomFeatures(int i) {
        Room.RoomType roomType = Room.intToRoomType(i);
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

    /**
     * Method to print room information on screen.
     */
    static void chooseRoomRequest() {
        System.out.println("\nChoose room type :\n");
        for (Room.RoomType roomType : Room.RoomType.values())
            System.out.format("%s.%s\n", roomType.ordinal() + 1, roomType.getName());
    }

    /**
     * Method to print food menu on screen.
     */
    static void printFoodMenu() {
        System.out.println("\n==========\nMenu:\n==========\n");
        for (Food.FoodType foodType : Food.FoodType.values())
            System.out.format("%s.%-15sRs.%s\n", foodType.ordinal() + 1, foodType.name(), foodType.getPrice());
    }

    /**
     * @param hotel
     * @param roomType Method to print rooms available on screen.
     */
    static void printAvailableRoomNumbers(Hotel hotel, Room.RoomType roomType) {
        System.out.println("\nChoose room number from: ");
        boolean doubleRoom = roomType.isDoubleRoom();
        boolean luxury = roomType.isLuxuryRoom();
        StringBuilder roomsListBuilder = new StringBuilder();
        for (Room room : hotel.availableRooms(doubleRoom, luxury))
            roomsListBuilder.append(room.getRoomNumber() + ",");
        System.out.println(roomsListBuilder.subSequence(0, roomsListBuilder.length()));
    }

    /**
     * Method to print main menu on screen.
     */
    static void requestMainCommand() {
        System.out.println("\nEnter your choice :\n1.Display room details\n2.Display room availability \n3.Book\n4.Order food\n5.Checkout\n6.Exit\n");
    }
}
