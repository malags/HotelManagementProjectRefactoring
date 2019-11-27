import java.util.ArrayList;

public abstract class Room {

    int roomNumber;
    private ArrayList<Food> food = new ArrayList<>();
    Client[] clients;


    public ArrayList<Food> getFood() {
        return food;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    abstract boolean isEmpty();
}
