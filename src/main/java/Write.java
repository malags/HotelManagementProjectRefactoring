import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

class Write implements Runnable {
    @Override
    public void run() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("backup");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //TODO: change to single array
            objectOutputStream.writeObject(Hotel.arr1);
            objectOutputStream.writeObject(Hotel.arr2);
            objectOutputStream.writeObject(Hotel.arr3);
            objectOutputStream.writeObject(Hotel.arr4);
        } catch (Exception e) {
            System.out.println("Error in writing " + e);
        }

    }
}
