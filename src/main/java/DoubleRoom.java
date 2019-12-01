/**
 * Class representing a double room.
 * Extends the abstract room class.
 */
public class DoubleRoom extends Room {

    public DoubleRoom(Client client1, Client client2, int roomNumber, boolean isLuxury) {
        super(roomNumber, isLuxury ? RoomType.DoubleLuxury : RoomType.DoubleNotLuxury, new Client[2]);
        clients[0] = client1;
        clients[1] = client2;
    }

    /**
     * @return whether a room is occupied or not.
     */
    @Override
    boolean isEmpty() {
        return clients[0] == null && clients[1] == null;
    }

    /**
     * Set a room as unoccupied.
     */
    @Override
    void setEmpty() {
        clients[0] = null;
        clients[1] = null;
        foods.clear();
    }

    /**
     * @param doubleRoom
     * @param luxury
     * @return one if the room is free otherwise zero.
     */
    @Override
    public int countAvailable(boolean doubleRoom, boolean luxury) {
        return doubleRoom && (luxury == isLuxury()) && isEmpty() ? 1 : 0;
    }

    /**
     * @param clients Method to book a room.
     */
    @Override
    public void book(Client... clients) {
        assert (clients.length <= 2);
        if (clients.length < 2)
            this.clients[0] = clients[0];
        else {
            this.clients[0] = clients[0];
            this.clients[1] = clients[1];
        }
    }

    /**
     * @return Method to return the value of a room depending on its type.
     * Return 4000 if the room is luxury, otherwise 3000.
     */
    @Override
    public int getCharge() {
        return isLuxury() ? 4000 : 3000;
    }
}
