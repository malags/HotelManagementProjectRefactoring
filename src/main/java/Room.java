import java.io.Serializable;
import java.util.ArrayList;

public abstract class Room implements Serializable {

    public enum RoomType {
        DoubleLuxury("Luxury Double Room"){
            @Override
            public boolean isDoubleRoom() {
                return true;
            }

            @Override
            public boolean isLuxuryRoom() {
                return true;
            }
        }, DoubleNotLuxury("Deluxe Double Room") {
            @Override
            public boolean isDoubleRoom() {
                return true;
            }

            @Override
            public boolean isLuxuryRoom() {
                return false;
            }
        }, SingleLuxury("Luxury Single Room") {
            @Override
            public boolean isDoubleRoom() {
                return false;
            }

            @Override
            public boolean isLuxuryRoom() {
                return true;
            }
        }, SingleNotLuxury("Deluxe Single Room") {
            @Override
            public boolean isDoubleRoom() {
                return false;
            }

            @Override
            public boolean isLuxuryRoom() {
                return false;
            }
        };

        private String name;
        RoomType(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }

        public abstract boolean isDoubleRoom();
        public abstract boolean isLuxuryRoom();
    }

    public static RoomType intToRoomType(int roomType) {
        int index = roomType - 1;
        if(index < 0 || index > RoomType.values().length){
            System.out.println("Enter valid option");
            throw new IllegalArgumentException("The selected room type doesn't exist");
        }
        return RoomType.values()[index];
    }

    int roomNumber;
    RoomType roomType;
    private ArrayList<Food> foods = new ArrayList<>();
    Client[] clients;

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    abstract boolean isEmpty();

    boolean isLuxury(){
        return roomType.isLuxuryRoom();
    }

    abstract void setEmpty();

    public abstract int countAvailable(boolean doubleRoom, boolean luxury);

    public Client[] getClients(){
        return clients;
    }

    abstract  public void book(Client... clients);

    public abstract int getCharge();

    public boolean isDoubleRoom(){
        return roomType.isDoubleRoom();
    }

    public void addFood(Food food){
        foods.add(food);
    }
}
