import ch.usi.si.codelounge.jsicko.Contract;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

import java.io.Serializable;
import java.util.ArrayList;
import static ch.usi.si.codelounge.jsicko.Contract.old;
/**
 * Abstract class that represent a room
 */
public abstract class Room implements Serializable, Contract {

    //Jsicko wants this. violates invariant
    public Room() {
        this.roomNumber = Integer.MIN_VALUE;
        this.roomType = null;
        this.clients = null;
    }

    @Invariant
    @Pure
    protected boolean room_size_legal(){
        return clients.length == 1 || clients.length == 2;
    }

    @Invariant
    @Pure
    protected boolean room_number_legal(){
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
            return returns == this.equals(SingleLuxury) || this.equals(DoubleLuxury);
        }

        @Pure
        boolean valid_roomType_name(String returns){
            return
                    returns.equals("Luxury Double Room") ||
                    returns.equals("Deluxe Double Room") ||
                    returns.equals("Luxury Single Room") ||
                    returns.equals("Deluxe Single Room");
        }

        RoomType(String name, boolean isDoubleRoom, Boolean isLuxuryRoom) {
            this.name = name;
            this._isDoubleRoom = isDoubleRoom;
            this._isLuxuryRoom = isLuxuryRoom;
        }

        @Pure
        @Ensures("valid_roomType_name")
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
    @Requires("roomType_number_is_valid")
    public static RoomType intToRoomType(int roomType) {
        int index = roomType - 1;
        if (index < 0 || index > RoomType.values().length) {
            System.out.println("Enter valid option");
            throw new IllegalArgumentException("The selected room type doesn't exist");
        }
        return RoomType.values()[index];
    }

    @Pure
    protected boolean roomType_number_is_valid(int roomType){
        return roomType >=1 && roomType <= RoomType.values().length;
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
    @EnsuresNonNull("returns")
    public ArrayList<Food> getFoods() {
        return foods;
    }

    /**
     *
     * @return room number.
     */
    @Pure
    @Ensures("returns_correct_number")
    public int getRoomNumber() {
        return roomNumber;
    }

    boolean returns_correct_number(int returns){
        return returns == this.roomNumber;
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
    @Ensures("number_of_rooms_available_is_possible")
    public abstract int countAvailable(boolean doubleRoom, boolean luxury);

    boolean number_of_rooms_available_is_possible(int returns, boolean doubleRoom, boolean luxury){
        if(luxury)
            return returns >= 0 && returns <= 10;
        return returns >= 0 && returns <= 20;
    }

    /**
     *
     * @return client array.
     */
    @Pure
    @EnsuresNonNull("returns")
    public Client[] getClients() {
        return clients;
    }


    /**
     *
     * @param clients
     * Abstract to book a room.
     */
    @Requires({"clients_not_null", "clients_size_1_or_2",  "isEmpty"})
    @Ensures({"set_clients_in_room_success", "!isEmpty"})
    abstract public void book(Client... clients);


    @Pure
    protected boolean set_clients_in_room_success(Client... clients){
        boolean success = this.clients[0] == clients[0];
        if(clients.length == 2)
            success = success && this.clients[1] == clients[1];
        return success;
    }

    @Pure
    protected boolean clients_size_1_or_2(Client... clients){
        return clients.length >= 1 && clients.length <= 2;
    }

    @Pure
    protected boolean clients_not_null(Client... clients){
        for(Client client : clients)
            if(client == null)
                return false;
        return true;
    }

    /**
     *
     * @return
     * Abstract method.
     */
    @Pure
    @Ensures("nonNegative")
    public abstract int getCharge();

    @Pure
    protected boolean nonNegative(int returns){
        return returns >= 0;
    }

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
    @Ensures({"increase_food_list","added_food_is_new_element","rest_of_list_unchanged"})
    public void addFood(Food food) {
        foods.add(food);
    }

    @Pure
    protected boolean increase_food_list(){
        return old(this).foods.size() + 1 == this.foods.size();
    }

    @Pure
    protected boolean added_food_is_new_element(Food food){
        int oldLast = old(this).foods.size() - 1;
        return old(this).foods.get(oldLast) == this.foods.get(oldLast);
    }

    @Pure
    protected boolean rest_of_list_unchanged(){
        for (int index = 0 ; index < old(this).foods.size() ; ++index)
            if (old(this).foods.get(index) != this.foods.get(index))
                return false;
        return true;
    }
}
