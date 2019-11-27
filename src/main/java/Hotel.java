import java.util.Scanner;

class Hotel {

    static DoubleRoom arr1[] = new DoubleRoom[10]; //Luxury
    static DoubleRoom arr2[] = new DoubleRoom[20]; //Deluxe
    static SingleRoom arr3[] = new SingleRoom[10]; //Luxury
    static SingleRoom arr4[] = new SingleRoom[20]; //Deluxe

    static Scanner sc = new Scanner(System.in);

    static Client createClient(String customerPosition) {
        String name, contact, gender;
        System.out.print("\nEnter " + customerPosition + "customer name:");
        name = sc.next();
        System.out.print("Enter contact number: ");
        contact = sc.next();
        System.out.print("Enter gender: ");
        gender = sc.next();
        return new Client(name, contact, gender);
    }

    static void CustDetails(int i, int rn) {

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
                System.out.println("Wrong option");
                break;
        }
    }

    static void printRoomNumber(Room[] rooms, int roomType) {
        for (Room room : rooms)
            if (room.isEmpty())
                System.out.print(room.getRoomNumber() + 1 + ",");

        System.out.print("\nEnter room number: ");
        try {
            int roomNumber = sc.nextInt();
            roomNumber--;
            if (arr1[roomNumber] != null)
                throw new NotAvailable(roomNumber);
            CustDetails(roomType, roomNumber);
        } catch (Exception e) {
            System.out.println("Invalid Option");
            return;
        }
    }

    static void bookroom(int roomType) {
        int j;
        int roomNumber;
        System.out.println("\nChoose room number from: ");
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

    static void availability(int i) {
        int j, count = 0;
        switch (i) {
            case 1:
                for (j = 0; j < 10; j++) {
                    if (arr1[j] == null)
                        count++;
                }
                break;
            case 2:
                for (j = 0; j < arr2.length; j++) {
                    if (arr2[j] == null)
                        count++;
                }
                break;
            case 3:
                for (j = 0; j < arr3.length; j++) {
                    if (arr3[j] == null)
                        count++;
                }
                break;
            case 4:
                for (j = 0; j < arr4.length; j++) {
                    if (arr4[j] == null)
                        count++;
                }
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
        System.out.println("Number of rooms available : " + count);
    }

    static void bill(int rn, int rtype) {
        double amount = 0;
        String list[] = {"Sandwich", "Pasta", "Noodles", "Coke"};
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (rtype) {
            case 1:
                amount += 4000;
                System.out.println("\nRoom Charge - " + 4000);
                System.out.println("\n===============");
                System.out.println("Food Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : arr1[rn].getFood()) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }

                break;
            case 2:
                amount += 3000;
                System.out.println("Room Charge - " + 3000);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : arr2[rn].getFood()) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            case 3:
                amount += 2200;
                System.out.println("Room Charge - " + 2200);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : arr3[rn].getFood()) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            case 4:
                amount += 1200;
                System.out.println("Room Charge - " + 1200);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : arr4[rn].getFood()) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            default:
                System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount- " + amount);
    }

    static void deallocate(int rn, int rtype) {
        int j;
        char w;
        switch (rtype) {
            case 1:
                if (arr1[rn] != null)
                    //System.out.println("Room used by " + arr1[rn].name);
                    System.out.println();
                else {
                    System.out.println("Empty Already");
                    return;
                }
                System.out.println("Do you want to checkout ?(y/n)");
                w = sc.next().charAt(0);
                if (w == 'y' || w == 'Y') {
                    bill(rn, rtype);
                    arr1[rn] = null;
                    System.out.println("Deallocated succesfully");
                }

                break;
            case 2:
                if (arr2[rn] != null)
                    //System.out.println("Room used by " + arr2[rn].name);
                    System.out.println();
                else {
                    System.out.println("Empty Already");
                    return;
                }
                System.out.println(" Do you want to checkout ?(y/n)");
                w = sc.next().charAt(0);
                if (w == 'y' || w == 'Y') {
                    bill(rn, rtype);
                    arr2[rn] = null;
                    System.out.println("Deallocated succesfully");
                }

                break;
            case 3:
                if (arr3[rn] != null)
                    System.out.println("Room used by " + arr3[rn].getClient1().getName());
                else {
                    System.out.println("Empty Already");
                    return;
                }
                System.out.println(" Do you want to checkout ? (y/n)");
                w = sc.next().charAt(0);
                if (w == 'y' || w == 'Y') {
                    bill(rn, rtype);
                    arr3[rn] = null;
                    System.out.println("Deallocated succesfully");
                }

                break;
            case 4:
                if (arr4[rn] != null)
                    System.out.println("Room used by " + arr4[rn].getClient1().getName());
                else {
                    System.out.println("Empty Already");
                    return;
                }
                System.out.println(" Do you want to checkout ? (y/n)");
                w = sc.next().charAt(0);
                if (w == 'y' || w == 'Y') {
                    bill(rn, rtype);
                    arr4[rn] = null;
                    System.out.println("Deallocated succesfully");
                }
                break;
            default:
                System.out.println("\nEnter valid option : ");
                break;
        }
    }

    static void order(int rn, int rtype) {
        int i, q;
        char wish;
        try {
            System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
            do {
                i = sc.nextInt();
                System.out.print("Quantity- ");
                q = sc.nextInt();

                switch (rtype) {
                    case 1:
                        arr1[rn].getFood().add(new Food(i, q));
                        break;
                    case 2:
                        arr2[rn].getFood().add(new Food(i, q));
                        break;
                    case 3:
                        arr3[rn].getFood().add(new Food(i, q));
                        break;
                    case 4:
                        arr4[rn].getFood().add(new Food(i, q));
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
