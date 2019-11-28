import java.io.Serializable;

public class SingleRoom extends Room implements Serializable {

    public SingleRoom(Client client1, int roomNumber, boolean isLuxury) {
        super(isLuxury, roomNumber);
        this.clients = new Client[1];
        this.clients[0] = client1;
    }

    @Override
    boolean isEmpty() {
        return clients[0] == null;
    }

    @Override
    void setEmpty() {
        clients = new Client[1];
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


}
