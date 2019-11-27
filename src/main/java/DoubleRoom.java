public class DoubleRoom extends Room {

    public DoubleRoom(Client client1, Client client2) {
        this.clients = new Client[2];
        clients[0] = client1;
        clients[1] = client2;
    }

    public Client getClient1() {
        return this.clients[0];
    }

    public Client getClient2() {
        return this.clients[1];
    }

    @Override
    boolean isEmpty() {
        return getClient1() == null || getClient2() == null;
    }
}
