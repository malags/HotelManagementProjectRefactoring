import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Project3 {

    static final String chooseRoom = "\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room \n4.Deluxe Single Room\n";
    public static void main(String[] args) {

        try {
            //File file = new File("backup");
            Hotel hotel;
            /*if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                hotel = new Hotel ((Room[]) objectInputStream.readObject());
            }
            else*/
                hotel = new Hotel();
            Scanner sc = new Scanner(System.in);
            int command,roomSelection;
            char wish;
            x:
            do {

                System.out.println("\nEnter your choice :\n1.Display room details\n2.Display room availability \n3.Book\n4.Order food\n5.Checkout\n6.Exit\n");
                command = sc.nextInt();
                switch (command) {
                    case 1:
                        System.out.println(chooseRoom);
                        roomSelection = sc.nextInt();
                        Hotel.features(roomSelection);
                        break;
                    case 2:
                        System.out.println(chooseRoom);
                        roomSelection = sc.nextInt();
                        System.out.print("Number of rooms available : ");
                        System.out.print(hotel.availability(Hotel.intToRoomType(roomSelection)));
                        break;
                    case 3:
                        System.out.println(chooseRoom);
                        roomSelection = sc.nextInt();
                        hotel.printAvailableRoomNumber(Hotel.intToRoomType(roomSelection));

                        //TODO: extract, other functionality
                        System.out.print("\nEnter room number: ");
                        try {
                            int roomNumber = sc.nextInt();
                            if (!hotel.roomIsAvailable(roomNumber))
                                throw new NotAvailable(roomNumber);

                            Client[] clients = hotel.StdInClientsForRoom(roomNumber);
                            assert (hotel.roomIsAvailable(roomNumber));
                            hotel.bookRoom(roomNumber,clients);
                            System.out.println("Booked room number "+ roomNumber);
                            assert (!hotel.roomIsAvailable(roomNumber));
                        } catch (Exception e) {
                            System.out.println("Invalid Option");
                        }
                        break;
                    case 4:
                        System.out.print("Room Number -");
                        roomSelection = sc.nextInt();
                        if (roomSelection > 60 || roomSelection < 0)
                            System.out.println("Room doesn't exist");
                        else
                            hotel.order(roomSelection);
                        break;
                    case 5:
                        roomSelection = sc.nextInt();
                        if (roomSelection > 60 || roomSelection < 0)
                            System.out.println("Room doesn't exist");
                        else
                            hotel.deallocate(roomSelection);
                        break;
                    case 6:
                        break x;

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
