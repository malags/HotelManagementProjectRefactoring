import ch.usi.si.codelounge.jsicko.Contract;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Class that implement interface runnable to store hotel data on a file called backup.
 */
class Writer implements Runnable{

    protected Hotel hotel;

    public Writer(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public void run() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("backup");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hotel.rooms);
            System.out.println("Wrote to file");
        } catch (Exception e) {
            System.out.println("Error in writing " + e);
        }
    }
}
