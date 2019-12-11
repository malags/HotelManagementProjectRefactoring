import ch.usi.si.codelounge.jsicko.Contract;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Project3{

    /**
     * Enum that represent possible menu choices.
     */
    enum command {DisplayRoomDetails, DisplayRoomAvailability, BookRoom, OrderFood, Checkout, Exit}

    /**
     * @param scanner
     * @return Method to choose room number.
     */
    protected static int chooseRoomNumber(Scanner scanner) {
        System.out.print("\nEnter room number: ");
        int roomNumber = scanner.nextInt();
        if (roomNumber > 60 || roomNumber < 0) {
            System.out.println("Room doesn't exist");
            return -1;
        }
        return roomNumber;
    }

    /**
     * @param scanner
     * @return Method to choose room.
     */
    protected static int roomSelection(Scanner scanner) {
        Logger.chooseRoomRequest();
        return scanner.nextInt();
    }

    /**
     * @param scanner
     * @return Method that allow the continuation of the execution.
     */
    protected static boolean continuation(Scanner scanner) {
        char choice;
        do {
            System.out.println("\nContinue : (y/n)");
            choice = scanner.next().toLowerCase().charAt(0);
        } while (!(choice == 'y' || choice == 'n'));

        return choice == 'y';
    }

    /**
     * @param hotel Method tha allow to write the current status on a backup file.
     */
    public static void writeFile(Hotel hotel) {
        Thread t = new Thread(new Writer(hotel));
        t.start();
    }

    /**
     * @param args Main function.
     */
    public static void main(String[] args) {

        Hotel hotel = null;

        try {
            File file = new File("backup");

            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                hotel = new Hotel((Room[]) objectInputStream.readObject());
            } else
                hotel = new Hotel();

            Scanner scanner = new Scanner(System.in);
            int roomSelection;
            int roomNumber;
            boolean flag = true;

            while (flag) {
                Logger.requestMainCommand();
                switch (command.values()[scanner.nextInt() - 1]) {
                    case DisplayRoomDetails:
                        roomSelection = roomSelection(scanner);
                        Logger.roomFeatures(roomSelection);
                        break;
                    case DisplayRoomAvailability:
                        roomSelection = roomSelection(scanner);
                        System.out.print("Number of rooms available : ");
                        System.out.print(hotel.availability(Room.intToRoomType(roomSelection)));
                        break;
                    case BookRoom:
                        roomSelection = roomSelection(scanner);
                        Logger.printAvailableRoomNumbers(hotel, Room.intToRoomType(roomSelection));

                        System.out.print("\nEnter room number: ");
                        try {
                            roomNumber = scanner.nextInt();
                            if (!hotel.roomIsAvailable(roomNumber))
                                throw new NotAvailable(roomNumber);

                            Client[] clients = hotel.StdInClientsForRoom(roomNumber);
                            assert (hotel.roomIsAvailable(roomNumber));
                            if (!hotel.roomIsAvailable(roomNumber))
                                System.out.println("The room is already in use");
                            hotel.bookRoom(roomNumber, clients);
                            System.out.println("Booked room number " + roomNumber);
                            assert (!hotel.roomIsAvailable(roomNumber));
                        } catch (Exception e) {
                            System.out.println("Invalid Option");
                        }
                        break;
                    case OrderFood:
                        roomNumber = chooseRoomNumber(scanner);
                        if (roomNumber > -1)
                            hotel.order(roomNumber);
                        break;
                    case Checkout:
                        roomNumber = chooseRoomNumber(scanner);
                        if (roomNumber > -1)
                            hotel.checkout(roomNumber);
                        break;
                    case Exit:
                        writeFile(hotel);
                        return;
                }
                flag = continuation(scanner);
            }
        } catch (Exception e) {
            System.out.println("Not a valid input!");
        }
        writeFile(hotel);
    }
}
