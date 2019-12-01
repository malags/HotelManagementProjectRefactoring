import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class that represent a room
 */
public abstract class Room implements Serializable {

    protected Room(int roomNumber, RoomType roomType, Client[] clients) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.clients = clients;
    }

    /**
     * Enum that represent a particular room type.
     */
    public enum RoomType {
        DoubleLuxury("Luxury Double Room") {
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

        RoomType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract boolean isDoubleRoom();

        public abstract boolean isLuxuryRoom();
    }

    /**
     *
     * @param roomType
     * @return
     */
    public static RoomType intToRoomType(int roomType) {
        int index = roomType - 1;
        if (index < 0 || index > RoomType.values().length) {
            System.out.println("Enter valid option");
            throw new IllegalArgumentException("The selected room type doesn't exist");
        }
        return RoomType.values()[index];
    }

    /**
     * Field that represent a room object.
     */
    final int roomNumber;
    final RoomType roomType;
    ArrayList<Food> foods = new ArrayList<>();
    final Client[] clients;

    /**
     *
     * @return food list.
     */
    public ArrayList<Food> getFoods() {
        return foods;
    }

    /**
     *
     * @return room number.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     *
     * @return true if a room is empty, otherwise false.
     */
    abstract boolean isEmpty();

    /**
     *
     * @return true ia a room is luxury, otherwise false.
     */
    boolean isLuxury() {
        return roomType.isLuxuryRoom();
    }

    /**
     * Abstract method to set up empty a particular room.
     */
    abstract void setEmpty();

    /**
     *
     * @param doubleRoom
     * @param luxury
     * @return
     * Abstract method to count rooms available.
     */
    public abstract int countAvailable(boolean doubleRoom, boolean luxury);

    /**
     *
     * @return client array.
     */
    public Client[] getClients() {
        return clients;
    }

    /**
     *
     * @param clients
     * Abstract to book a room.
     */
    abstract public void book(Client... clients);

    /**
     *
     * @return
     * Abstract method.
     */
    public abstract int getCharge();

    /**
     *
     * @return true if the room is double, otherwise false.
     */
    public boolean isDoubleRoom() {
        return roomType.isDoubleRoom();
    }

    /**
     *
     * @param food
     * Method to add food to the Arraylist.
     */
    public void addFood(Food food) {
        foods.add(food);
    }
}
