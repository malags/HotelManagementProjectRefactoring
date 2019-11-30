public interface RoomLogger {

    static void printBill(Room room) {
        System.out.println("\nRoom Charge - " + room.getCharge());
        System.out.println("\n===============");
        System.out.println("Food Charges:- ");
        System.out.println("===============");
        System.out.println("Item   Quantity    Price");
        System.out.println("-------------------------");
        for(Food food: room.getFoods())
        {
            String format = "%-10s%-10s%-10s%n";
            System.out.printf(format,food.getItemName(),food.getQuantity(),food.getPrice());
        }
    }

    static void features(int i) {
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

    static void chooseRoomRequest(){
        System.out.println("\nChoose room type :\n");
        for(Room.RoomType roomType : Room.RoomType.values())
            System.out.format("%s.%s\n", new Object[]{roomType.ordinal()+1, roomType.getName()});
    }
}
