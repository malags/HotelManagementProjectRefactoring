import java.util.Scanner;

class Hotel {

    static DoubleRoom[] arr1 = new DoubleRoom[10]; //Luxury
    static DoubleRoom[] arr2 = new DoubleRoom[20]; //Deluxe
    static SingleRoom[] arr3 = new SingleRoom[10]; //Luxury
    static SingleRoom[] arr4 = new SingleRoom[20]; //Deluxe

    private static Scanner sc = new Scanner(System.in);

    private static Client createClient(String customerPosition) {
        String name, contact, gender;
        System.out.print("\nEnter " + customerPosition + "customer name:");
        name = sc.next();
        System.out.print("Enter contact number: ");
        contact = sc.next();
        System.out.print("Enter gender: ");
        gender = sc.next();
        return new Client(name, contact, gender);
    }

    private static void CustDetails(int i, int rn) {

        Client client1 = createClient("first");
        Client client2 = null;

        //if doubleroom
        if (i < 3) {
            client2 = createClient("second");
        }

        //TODO: remove switch
        switch (i) {
            case 1:
                arr1[rn] = new DoubleRoom(client1, client2);
                break;
            case 2:
                arr2[rn] = new DoubleRoom(client1, client2);
                break;
            case 3:
                arr3[rn] = new SingleRoom(client1);
                break;
            case 4:
                arr4[rn] = new SingleRoom(client1);
                break;
            default:
                System.out.println("Wrong option!");
                break;
        }
    }

    private static void printRoomNumber(Room[] rooms, int roomType) {
        System.out.println("\nChoose room number from: ");
        for (Room room : rooms)
            if (room.isEmpty())
                System.out.print(room.getRoomNumber() + 1 + ",");

        System.out.print("\nEnter room number: ");
        try {
            int roomNumber = sc.nextInt();
            roomNumber--;
            if (rooms[roomNumber] != null)
                throw new NotAvailable(roomNumber);
            CustDetails(roomType, roomNumber);
        } catch (Exception e) {
            System.out.println("Invalid Option");
        }
    }

    static void bookroom(int roomType) {
        switch (roomType) {
            case 1:
                printRoomNumber(arr1, roomType);
                break;
            case 2:
                printRoomNumber(arr2, roomType);
                break;
            case 3:
                printRoomNumber(arr3, roomType);
                break;
            case 4:
                printRoomNumber(arr4, roomType);
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
        System.out.println("Room Booked");
    }

    static void features(int i) {
        switch (i) {
            case 1:
                System.out.println("Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:4000 ");
                break;
            case 2:
                System.out.println("Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:3000  ");
                break;
            case 3:
                System.out.println("Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:2200  ");
                break;
            case 4:
                System.out.println("Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:1200 ");
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
    }

    private static int roomAvailability(Room[] rooms) {
        int count = 0;
        for (int j = 0; j < 10; j++) {
            if (rooms[j] == null)
                count++;
        }
        return count;
    }

    static void availability(int i) {
        int count = 0;
        switch (i) {
            case 1:
                count = roomAvailability(arr1);
                break;
            case 2:
                count = roomAvailability(arr2);
                break;
            case 3:
                count = roomAvailability(arr3);
                break;
            case 4:
                count = roomAvailability(arr4);
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
        System.out.println("Number of rooms available : " + count);
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

    private static void bill(int roomNumber, int roomType) {
        double amount = 0;
        String[] list = {"Sandwich", "Pasta", "Noodles", "Coke"};
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (roomType) {
            case 1:
                amount += 4000;
                billPrinting(4000);
                amount += calculateBill(amount, arr1[roomNumber], list);
                break;
            case 2:
                amount += 3000;
                billPrinting(3000);
                amount += calculateBill(amount, arr2[roomNumber], list);
                break;
            case 3:
                amount += 2200;
                billPrinting(2200);
                amount += calculateBill(amount, arr3[roomNumber], list);
                break;
            case 4:
                amount += 1200;
                billPrinting(1200);
                amount += calculateBill(amount, arr4[roomNumber], list);
                break;
            default:
                System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount- " + amount);
    }

    private static void deallocationRoom(int roomNumber, int roomType, Room[] rooms) {
        if (rooms[roomNumber].isEmpty())
            System.out.println("Room used by " + arr1[roomNumber].getClient2().getName());
        else {
            System.out.println("Empty Already!");
            return;
        }
        System.out.println("Do you want to checkout?(y/n)");
        char w = sc.next().toLowerCase().charAt(0);
        if (w == 'y') {
            bill(roomNumber, roomType);
            arr1[roomNumber] = null;
            System.out.println("Deallocated succesfully!");
        }
    }

    static void deallocate(int roomNumber, int roomType) {
        switch (roomType) {
            case 1:
                deallocationRoom(roomNumber, roomType, arr1);
                break;
            case 2:
                deallocationRoom(roomNumber, roomType, arr2);
                break;
            case 3:
                deallocationRoom(roomNumber, roomType, arr3);
                break;
            case 4:
                deallocationRoom(roomNumber, roomType, arr4);
                break;
            default:
                System.out.println("\nEnter valid option!");
                break;
        }
    }

    static void order(int roomNumber, int roomType) {
        int i, q;
        char wish;
        try {
            System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
            do {
                i = sc.nextInt();
                System.out.print("Quantity- ");
                q = sc.nextInt();

                switch (roomType) {
                    case 1:
                        arr1[roomNumber].getFoods().add(new Food(i, q));
                        break;
                    case 2:
                        arr2[roomNumber].getFoods().add(new Food(i, q));
                        break;
                    case 3:
                        arr3[roomNumber].getFoods().add(new Food(i, q));
                        break;
                    case 4:
                        arr4[roomNumber].getFoods().add(new Food(i, q));
                        break;
                }
                System.out.println("Do you want to order anything else ? (y/n)");
                wish = sc.next().charAt(0);
            } while (wish == 'y' || wish == 'Y');
        } catch (NullPointerException e) {
            System.out.println("\nRoom not booked");
        } catch (Exception e) {
            System.out.println("Cannot be done");
        }
    }
}
