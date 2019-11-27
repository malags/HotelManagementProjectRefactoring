public class SingleRoom extends Room {

    public SingleRoom(Client client1) {
        this.clients = new Client[1];
        this.clients[0] = client1;
    }

    public Client getClient1() {
        return this.clients[0];
    }

    @Override
    boolean isEmpty() {
        return getClient1() == null;
    }
}
