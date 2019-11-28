import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

class Write implements Runnable {
    Hotel hotel;

    public Write(Hotel hotel){
        this.hotel = hotel;
    }


    @Override
    public void run() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("backup");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //TODO: change to single array
            objectOutputStream.writeObject(hotel.rooms);
        } catch (Exception e) {
            System.out.println("Error in writing " + e);
        }

    }
}
