import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Project3 {

    enum com {DisplayRoomDetails, DisplayRoomAvailability, BookRoom, OrderFood, Checkout, Exit};

    public static void main(String[] args) {

        try {
            File file = new File("backup");
            Hotel hotel;
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                hotel = new Hotel ((Room[]) objectInputStream.readObject());
            }
            else
                hotel = new Hotel();
            Scanner sc = new Scanner(System.in);
            int roomSelection;
            char wish;

            do {

                System.out.println("\nEnter your choice :\n1.Display room details\n2.Display room availability \n3.Book\n4.Order food\n5.Checkout\n6.Exit\n");
                switch (com.values()[sc.nextInt()-1]) {
                    case DisplayRoomDetails:
                        RoomLogger.chooseRoomRequest();
                        roomSelection = sc.nextInt();
                        RoomLogger.features(roomSelection);
                        break;
                    case DisplayRoomAvailability:
                        RoomLogger.chooseRoomRequest();
                        roomSelection = sc.nextInt();
                        System.out.print("Number of rooms available : ");
                        System.out.print(hotel.availability(Room.intToRoomType(roomSelection)));
                        break;
                    case BookRoom:
                        RoomLogger.chooseRoomRequest();
                        roomSelection = sc.nextInt();
                        hotel.printAvailableRoomNumber(Room.intToRoomType(roomSelection));

                        //TODO: extract, other functionality
                        System.out.print("\nEnter room number: ");
                        try {
                            int roomNumber = sc.nextInt();
                            if (!hotel.roomIsAvailable(roomNumber))
                                throw new NotAvailable(roomNumber);

                            Client[] clients = hotel.StdInClientsForRoom(roomNumber);
                            assert (hotel.roomIsAvailable(roomNumber));
                            if(!hotel.roomIsAvailable(roomNumber))
                                System.out.println("The room is already in use");
                            hotel.bookRoom(roomNumber,clients);
                            System.out.println("Booked room number "+ roomNumber);
                            assert (!hotel.roomIsAvailable(roomNumber));
                        } catch (Exception e) {
                            System.out.println("Invalid Option");
                        }
                        break;
                    case OrderFood:
                        System.out.print("\nEnter room number: ");
                        int roomNumber = sc.nextInt();
                        if (roomNumber > 60 || roomNumber < 0)
                            System.out.println("Room doesn't exist");
                        else
                            hotel.order(roomNumber);
                        break;
                    case Checkout:
                        System.out.print("\nEnter room number: ");
                        roomSelection = sc.nextInt();
                        if (roomSelection > 60 || roomSelection < 0)
                            System.out.println("Room doesn't exist");
                        else
                            hotel.checkout(roomSelection);
                        break;
                    case Exit:
                        Thread t = new Thread(new Write(hotel));
                        t.start();
                        return;
                }

                System.out.println("\nContinue : (y/n)");
                wish = sc.next().toLowerCase().charAt(0);
                if (!(wish == 'y' || wish == 'n')) {
                    System.out.println("Invalid Option");
                    System.out.println("\nContinue : (y/n)");
                    wish = sc.next().toLowerCase().charAt(0);
                }
            } while (wish == 'y');

            Thread t = new Thread(new Write(hotel));
            t.start();
        } catch (Exception e) {
            System.out.println("Not a valid input!");
        }
    }
}
