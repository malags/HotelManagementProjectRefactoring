public class DoubleRoom extends Room {

    Client clients[] = new Client[2];

    public DoubleRoom(Client c1, Client c2) {
        clients[0] = c1;
        clients[1] = c2;
    }

}
