import org.junit.Test;

public class DoubleRoomTest {

    private final Client client1 = new Client("name","contact","gender");
    private final Client client2 = new Client("name2","contact2","gender2");
    private final int roomNumber = 1;

    @Test
    public void doubleRoomRightSize(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,true);
        assert (doubleRoom.clients.length == 2);
    }

    @Test
    public void doubleRoomRightClient1(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,true);
        assert (doubleRoom.getClients()[0] == client1);
    }

    @Test
    public void doubleRoomRightClient2(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,true);
        assert (doubleRoom.getClients()[1] == client2);
    }

    @Test
    public void doubleRoomIsLuxury(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,true);
        assert (doubleRoom.isLuxury());
    }

    @Test
    public void doubleRoomIsNotLuxury(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,false);
        assert (!doubleRoom.isLuxury());
    }

    @Test
    public void emptyDoubleRoomIsEmpty(){
        DoubleRoom doubleRoom = new DoubleRoom(null,null, roomNumber,false);
        assert (doubleRoom.isEmpty());
    }

    @Test
    public void nonEmptyDoubleRoomIsNotEmptyClient1(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,null, roomNumber,false);
        assert (!doubleRoom.isEmpty());
    }

    @Test
    public void nonEmptyDoubleRoomIsNotEmptyClient2(){
        DoubleRoom doubleRoom = new DoubleRoom(null,client2, roomNumber,false);
        assert (!doubleRoom.isEmpty());
    }

    @Test
    public void fullDoubleRoomIsNotEmpty(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,false);
        assert (!doubleRoom.isEmpty());
    }

    @Test
    public void DoubleRoomIsEmptied(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,false);
        assert (!doubleRoom.isEmpty());
        doubleRoom.setEmpty();
        assert (doubleRoom.isEmpty());
        assert (doubleRoom.clients.length == 2);
    }

    @Test
    public void DoubleRoomCountDoubleRoomNotLuxury(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,false);
        assert (doubleRoom.countAvailable(true,true) == 0);
    }

    @Test
    public void DoubleRoomCountDoubleRoomLuxuryBooked(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,true);
        assert (doubleRoom.countAvailable(true,true) == 0);
    }

    @Test
    public void DoubleRoomCountDoubleRoomLuxuryFree(){
        DoubleRoom doubleRoom = new DoubleRoom(null,null, roomNumber,true);
        assert (doubleRoom.countAvailable(true,true) == 1);
    }

    @Test
    public void DoubleRoomCountSingleRoomNotLuxury(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,false);
        assert (doubleRoom.countAvailable(false,false) == 0);
    }

    @Test
    public void DoubleRoomCountSingleRoomLuxury(){
        DoubleRoom doubleRoom = new DoubleRoom(client1,client2, roomNumber,true);
        assert (doubleRoom.countAvailable(false,true) == 0);
    }

    @Test
    public void DoubleRoomSingleBook(){
        DoubleRoom doubleRoom = new DoubleRoom(null,null, roomNumber,false);
        assert (doubleRoom.isEmpty());
        doubleRoom.book(client1);
        assert (!doubleRoom.isEmpty());
        assert (doubleRoom.getClients().length == 2);
        assert (doubleRoom.getClients()[0] == client1);
        assert (doubleRoom.getClients()[1] == null);
    }

    @Test
    public void DoubleRoomBook(){
        DoubleRoom doubleRoom = new DoubleRoom(null,null, roomNumber,false);
        assert (doubleRoom.isEmpty());
        doubleRoom.book(client1,client2);
        assert (!doubleRoom.isEmpty());
        assert (doubleRoom.getClients().length == 2);
        assert (doubleRoom.getClients()[0] == client1);
        assert (doubleRoom.getClients()[1] == client2);
    }
}
