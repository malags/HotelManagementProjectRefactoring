import org.junit.Test;

public class SingleRoomTest {
    final Client client1 = new Client("name", "contact", "gender");
    final int roomNumber = 1;

    @Test
    public void roomRightSize() {
        SingleRoom room = new SingleRoom(client1, roomNumber, true);
        assert (room.clients.length == 1);
    }

    @Test
    public void singleRoomRightClient() {
        SingleRoom room = new SingleRoom(client1, roomNumber, true);
        assert (room.getClients()[0] == client1);
    }


    @Test
    public void singleRoomIsLuxury() {
        SingleRoom room = new SingleRoom(client1, roomNumber, true);
        assert (room.isLuxury());
    }

    @Test
    public void singleRoomIsNotLuxury() {
        SingleRoom room = new SingleRoom(client1, roomNumber, false);
        assert (!room.isLuxury());
    }

    @Test
    public void emptySingleRoomIsEmpty() {
        SingleRoom room = new SingleRoom(null, roomNumber, false);
        assert (room.isEmpty());
    }

    @Test
    public void nonEmptySingleRoomIsNotEmpty() {
        SingleRoom room = new SingleRoom(client1, roomNumber, false);
        assert (!room.isEmpty());
    }

    @Test
    public void singleRoomIsEmptied() {
        SingleRoom room = new SingleRoom(client1, roomNumber, false);
        assert (!room.isEmpty());
        room.setEmpty();
        assert (room.isEmpty());
        assert (room.clients.length == 1);
    }

    @Test
    public void SingleRoomCountSingleRoomNotLuxuryFree() {
        SingleRoom singleRoom = new SingleRoom(null, roomNumber, false);
        assert (singleRoom.countAvailable(false, false) == 1);
    }

    @Test
    public void SingleRoomCountSingleRoomNotLuxuryBooked() {
        SingleRoom singleRoom = new SingleRoom(client1, roomNumber, false);
        assert (singleRoom.countAvailable(false, false) == 0);
    }

    @Test
    public void SingleRoomCountSingleRoomLuxury() {
        SingleRoom singleRoom = new SingleRoom(client1, roomNumber, false);
        assert (singleRoom.countAvailable(false, true) == 0);
    }

    @Test
    public void SingleRoomCountDoubleRoomNotLuxury() {
        SingleRoom singleRoom = new SingleRoom(client1, roomNumber, false);
        assert (singleRoom.countAvailable(true, false) == 0);
    }

    @Test
    public void SingleRoomCountDoubleRoomLuxury() {
        SingleRoom singleRoom = new SingleRoom(client1, roomNumber, false);
        assert (singleRoom.countAvailable(true, true) == 0);
    }

    @Test
    public void SingleRoomSingleBook() {
        SingleRoom singleRoom = new SingleRoom(null, roomNumber, false);
        assert (singleRoom.isEmpty());
        singleRoom.book(client1);
        assert (!singleRoom.isEmpty());
        assert (singleRoom.getClients().length == 1);
        assert (singleRoom.getClients()[0] == client1);
    }

    @Test
    public void SingleRoomNotLuxuryCharge() {
        SingleRoom singleRoom = new SingleRoom(null, roomNumber, false);
        assert (singleRoom.getCharge() == 1200);
    }

    @Test
    public void SingleRoomLuxuryCharge() {
        SingleRoom singleRoom = new SingleRoom(null, roomNumber, true);
        assert (singleRoom.getCharge() == 2200);
    }

    @Test
    public void SingleRoomLuxuryRightRoomType() {
        SingleRoom singleRoom = new SingleRoom(null, roomNumber, true);
        assert (singleRoom.isLuxury());
        assert (!singleRoom.isDoubleRoom());
    }

    @Test
    public void SingleRoomNotLuxuryRightRoomType() {
        SingleRoom singleRoom = new SingleRoom(null, roomNumber, false);
        assert (!singleRoom.isLuxury());
        assert (!singleRoom.isDoubleRoom());
    }

    @Test
    public void SingleRoomEmptiedAndBookedClearPrice() {
        SingleRoom singleRoom = new SingleRoom(client1, roomNumber, false);
        singleRoom.addFood(new Food(Food.FoodType.Pasta,10));
        assert (!singleRoom.foods.isEmpty());
        singleRoom.setEmpty();
        assert (singleRoom.foods.isEmpty());
    }

}
