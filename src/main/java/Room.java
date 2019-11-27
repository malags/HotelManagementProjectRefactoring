import java.util.ArrayList;

public abstract class Room {

    int roomNumber;
    private ArrayList<Food> foods = new ArrayList<>();
    Client[] clients;


    public ArrayList<Food> getFoods() {
        return foods;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    abstract boolean isEmpty();
}
