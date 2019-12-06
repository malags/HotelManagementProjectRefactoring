import ch.usi.si.codelounge.jsicko.Contract;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class that represent a room
 */
public abstract class Room implements Serializable, Contract {

    @Invariant
    @Pure
    private boolean room_size_legal(){
        return clients.length <=2;
    }

    @Invariant
    @Pure
    private boolean room_number_legal(){
        return roomNumber >= 1 && roomNumber <= 60;
    }

    protected Room(int roomNumber, RoomType roomType, Client[] clients) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.clients = clients;
    }

    public enum RoomType implements Contract {
        DoubleLuxury("Luxury Double Room", true, true),
        DoubleNotLuxury("Deluxe Double Room", true, false),
        SingleLuxury("Luxury Single Room", false, true),
        SingleNotLuxury("Deluxe Single Room", false, false);
    
        private String name;
        private boolean _isLuxuryRoom;
        private boolean _isDoubleRoom;
    
        @Pure
        boolean roomtype_is_double(boolean returns) {
            return returns == this.equals(DoubleLuxury) || this.equals(DoubleNotLuxury);
        }

        @Pure
        boolean roomtype_is_luxury(boolean returns) {
            return returns == this.equals(SingleLuxury) || this.equals(SingleNotLuxury);
        }
    
        RoomType(String name, boolean isDoubleRoom, Boolean isLuxuryRoom) {
            this.name = name;
            this._isDoubleRoom = isDoubleRoom;
            this._isLuxuryRoom = isLuxuryRoom;
        }
    
        @Pure
        public String getName() {
            return name;
        }
    
        @Pure
        @Ensures("roomtype_is_double")
        public boolean isDoubleRoom() {
            return this._isDoubleRoom;
        }
    
        @Pure
        @Ensures("roomtype_is_luxury")
        public boolean isLuxuryRoom() {
            return this._isLuxuryRoom;
        }
    }

    /**
     *
     * @param roomType: 1 to 4, represent the index of the roomType in the enum + 1
     * @return :the value associated with the index
     */
    @Pure
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
    @Pure
    public ArrayList<Food> getFoods() {
        return foods;
    }

    /**
     *
     * @return room number.
     */
    @Pure
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     *
     * @return true if a room is empty, otherwise false.
     */
    @Pure
    abstract boolean isEmpty();

    /**
     *
     * @return true ia a room is luxury, otherwise false.
     */
    @Pure
    boolean isLuxury() {
        return roomType.isLuxuryRoom();
    }

    /**
     * Abstract method to set up empty a particular room.
     */
    @Ensures("isEmpty")
    abstract void setEmpty();

    /**
     *
     * @param doubleRoom : is double
     * @param luxury : is luxury
     * @return
     * Abstract method to count rooms available.
     */
    @Pure
    public abstract int countAvailable(boolean doubleRoom, boolean luxury);

    /**
     *
     * @return client array.
     */
    @Pure
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
    @Pure
    public abstract int getCharge();

    /**
     *
     * @return true if the room is double, otherwise false.
     */
    @Pure
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
