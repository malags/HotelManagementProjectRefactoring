public class DoubleRoom extends Room {

    public DoubleRoom(Client client1, Client client2, int roomNumber, boolean isLuxury) {
        super(
                roomNumber,
                isLuxury ? RoomType.DoubleLuxury : RoomType.DoubleNotLuxury,
                new Client[2]);
        clients[0] = client1;
        clients[1] = client2;
    }

    @Override
    boolean isEmpty() {
        return clients[0] == null && clients[1] == null;
    }

    @Override
    void setEmpty() {
        clients[0] = null;
        clients[1] = null;
        foods.clear();
    }

    @Override
    public int countAvailable(boolean doubleRoom, boolean luxury) {
        return doubleRoom && (luxury == isLuxury()) && isEmpty() ? 1 : 0;
    }

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

    @Override
    public int getCharge() {
        return isLuxury() ? 4000 : 3000;
    }
}
