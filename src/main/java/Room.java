import java.util.ArrayList;

public abstract class Room {

    int roomNumber;
    private ArrayList<Food> foods = new ArrayList<>();
    Client[] clients;
    private boolean isLuxury;

    public Room(boolean isLuxury,int roomNumber) {

        this.isLuxury = isLuxury;
        this.roomNumber = roomNumber;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    abstract boolean isEmpty();

    boolean isLuxury(){
        return isLuxury;
    }

    abstract void setEmpty();

    public abstract int countAvailable(boolean doubleRoom, boolean luxury);

    public Client[] getClients(){
        return clients;
    }

    abstract  public void book(Client... clients);
}
