/**
 * Class that represent a single room.
 */
public class SingleRoom extends Room {

    public SingleRoom(Client client1, int roomNumber, boolean isLuxury) {
        super(roomNumber, isLuxury ? RoomType.SingleLuxury : RoomType.SingleNotLuxury, new Client[1]);
        this.clients[0] = client1;
    }

    /**
     * @return whether a room is occupied or not.
     */
    @Override
    boolean isEmpty() {
        return clients[0] == null;
    }

    /**
     * Set a room as unoccupied.
     */
    @Override
    void setEmpty() {
        clients[0] = null;
        foods.clear();
    }

    /**
     *
     * @param doubleRoom
     * @param luxury
     * @return one if the room is free otherwise zero.
     */
    @Override
    public int countAvailable(boolean doubleRoom, boolean luxury) {
        return (!doubleRoom) && (luxury == isLuxury()) && isEmpty() ? 1 : 0;
    }

    /**
     *
     * @param clients Method to book a room.
     */
    @Override
    public void book(Client... clients) {
        assert (clients.length == 1);
        this.clients[0] = clients[0];
    }

    /**
     *
     * @return Method to return the value of a room depending on its type.
     *      * Return 2200 if the room is luxury, otherwise 1200.
     */
    @Override
    public int getCharge() {
        return isLuxury() ? 2200 : 1200;
    }
}
