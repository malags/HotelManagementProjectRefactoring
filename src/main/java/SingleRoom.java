public class SingleRoom extends Room {

    public SingleRoom(Client client1, int roomNumber, boolean isLuxury) {
        super(
                roomNumber,
                isLuxury ? RoomType.SingleLuxury : RoomType.SingleNotLuxury,
                new Client[1]);
        this.clients[0] = client1;
    }

    @Override
    boolean isEmpty() {
        return clients[0] == null;
    }

    @Override
    void setEmpty() {
        clients[0] = null;
        foods.clear();
    }

    @Override
    public int countAvailable(boolean doubleRoom, boolean luxury) {
        return (!doubleRoom) && (luxury == isLuxury()) && isEmpty() ? 1 : 0;
    }

    @Override
    public void book(Client... clients) {
        assert (clients.length == 1);
        this.clients[0] = clients[0];
    }

    @Override
    public int getCharge() {
        return isLuxury() ? 2200 : 1200;
    }
}
