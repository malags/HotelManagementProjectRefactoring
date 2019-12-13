import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class HotelTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    private Client client1;
    private Hotel hotel;

    @Before
    public void setUp() {
        client1 = new Client("name", "contact", "gender");
        hotel = Mockito.spy(new Hotel());
    }

    @Test
    public void hotelHasRightSize() {
        assert (hotel.rooms.length == 60);
    }

    @Test
    public void hotelLuxuryDoubleNrAvailable() {
        assert (hotel.availability(Room.RoomType.DoubleLuxury) == 10);
    }

    @Test
    public void hotelNotLuxuryDoubleNrAvailable() {
        assert (hotel.availability(Room.RoomType.DoubleNotLuxury) == 20);
    }

    @Test
    public void hotelLuxurySingleNrAvailable() {
        assert (hotel.availability(Room.RoomType.SingleLuxury) == 10);
    }

    @Test
    public void hotelNotLuxurySingleNrAvailable() {
        assert (hotel.availability(Room.RoomType.SingleNotLuxury) == 20);
    }

    @Test
    public void availableSingleRoomNotLuxurySize() {
        List<Room> availableSingleNotLuxury = hotel.availableRooms(false, false);
        assert (availableSingleNotLuxury.size() == 20);
    }

    @Test
    public void availableSingleRoomLuxurySize() {
        List<Room> availableSingleLuxury = hotel.availableRooms(false, true);
        assert (availableSingleLuxury.size() == 10);
    }

    @Test
    public void availableDoubleRoomNotLuxurySize() {
        List<Room> availableDoubleNotLuxury = hotel.availableRooms(true, false);
        assert (availableDoubleNotLuxury.size() == 20);
    }

    @Test
    public void availableDoubleRoomLuxurySize() {
        List<Room> availableDoubleLuxury = hotel.availableRooms(true, true);
        assert (availableDoubleLuxury.size() == 10);
    }

    @Test
    public void availableSingleRoomNotLuxuryType() {
        List<Room> availableSingleNotLuxury = hotel.availableRooms(false, false);
        for (Room room : availableSingleNotLuxury) {
            assert (room.isEmpty());
            assert (!room.isLuxury());
            assert (room instanceof SingleRoom);
        }
    }

    @Test
    public void availableSingleRoomLuxuryType() {
        List<Room> availableSingleLuxury = hotel.availableRooms(false, true);
        for (Room room : availableSingleLuxury) {
            assert (room.isEmpty());
            assert (room.isLuxury());
            assert (room instanceof SingleRoom);
        }
    }

    @Test
    public void availableDoubleRoomNotLuxuryType() {
        List<Room> availableDoubleNotLuxury = hotel.availableRooms(true, false);
        for (Room room : availableDoubleNotLuxury) {
            assert (room.isEmpty());
            assert (!room.isLuxury());
            assert (room instanceof DoubleRoom);
        }
    }

    @Test
    public void availableDoubleRoomLuxuryType() {
        List<Room> availableDoubleLuxury = hotel.availableRooms(true, true);
        for (Room room : availableDoubleLuxury) {
            assert (room.isEmpty());
            assert (room.isLuxury());
            assert (room instanceof DoubleRoom);
        }
    }

    @Test
    public void availableSingleRoomNotLuxurySizeBooked() {
        final boolean doubleRoom = false;
        final boolean luxury = false;
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 20);
        hotel.availableRooms(doubleRoom, luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 19);
    }

    @Test
    public void availableSingleRoomLuxurySizeBooked() {
        final boolean doubleRoom = false;
        final boolean luxury = true;
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 10);
        hotel.availableRooms(doubleRoom, luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 9);
    }

    @Test
    public void availableDoubleRoomNotLuxurySizeBooked() {
        final boolean doubleRoom = true;
        final boolean luxury = false;
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 20);
        hotel.availableRooms(doubleRoom, luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 19);
    }

    @Test
    public void availableDoubleRoomLuxurySizeBooked() {
        final boolean doubleRoom = true;
        final boolean luxury = true;
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 10);
        hotel.availableRooms(doubleRoom, luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom, luxury).size() == 9);
    }


    @Test
    public void roomIsAvailable() {
        assertTrue(hotel.roomIsAvailable(1));
    }

    @Test
    public void stdInClientsForRoom01() {
        systemInMock.provideLines("test1", "test1", "test1", "test2", "test2", "test2");
        Client[] clients = hotel.StdInClientsForRoom(1);
        assertNotNull(clients);
        assertTrue(hotel.rooms[0].isDoubleRoom());
        assertEquals(2, clients.length);
        assertEquals("test1", clients[0].getName());
        assertEquals("test2", clients[1].getName());
    }

    @Test
    public void stdInClientsForRoom02() {
        systemInMock.provideLines("test1", "test1", "test1");
        int roomNumber = hotel.rooms.length;
        Client[] clients = hotel.StdInClientsForRoom(roomNumber);
        assertNotNull(clients);
        assertFalse(hotel.rooms[roomNumber - 1].isDoubleRoom());
        assertEquals(1, clients.length);
        assertEquals("test1", clients[0].getName());
    }

    @Test
    public void bill() {
        Client client1 = new Client("test1", "test1", "test1");
        Client client2 = new Client("test2", "test2", "test2");

        DoubleRoom doubleRoomLuxury = new DoubleRoom(client1, client2, 1, true);

        hotel.bill(doubleRoomLuxury);
        Mockito.verify(hotel, Mockito.times(1)).bill(doubleRoomLuxury);
    }
//
//    @Test
//    public void bookRoom() {
//        Client[] clients = new Client[2];
//        clients[0] = new Client("test1", "test1", "test1");
//        clients[1] = new Client("test2", "test2", "test2");
//
//        hotel.bookRoom(1, clients);
//        Mockito.verify(hotel, Mockito.times(1)).bookRoom(1, clients);
//
//    }
//
    @Test
    public void availability() {

        Room.RoomType doubleLuxury = Room.RoomType.DoubleLuxury;
        Room.RoomType doubleNotLuxury = Room.RoomType.DoubleNotLuxury;
        Room.RoomType singleLuxury = Room.RoomType.SingleLuxury;
        Room.RoomType singleNotLuxury = Room.RoomType.SingleNotLuxury;

        assertEquals(10, hotel.availability(doubleLuxury));
        assertEquals(20, hotel.availability(doubleNotLuxury));
        assertEquals(10, hotel.availability(singleLuxury));
        assertEquals(20, hotel.availability(singleNotLuxury));
    }

    @Test
    public void checkout01() {
        systemInMock.provideLines("y", "y");
        hotel.checkout(1);
        Mockito.verify(hotel, Mockito.times(1)).checkout(1);
    }

    @Test
    public void checkout02() {
        /*Client client1 = new Client("test1", "test1", "test1");
        Client client2 = new Client("test2", "test2", "test2");

        DoubleRoom doubleRoomLuxury = new DoubleRoom(client1, client2, 1, true);
        hotel.rooms[0] = doubleRoomLuxury;

        systemInMock2.provideLines("y");
        hotel.checkout(1);
        Mockito.verify(hotel, Mockito.times(1)).checkout(1);*/
    }

    @Test
    public void checkout03() {
        /*Client client1 = new Client("test1", "test1", "test1");
        Client client2 = new Client("test2", "test2", "test2");

        DoubleRoom doubleRoomLuxury = new DoubleRoom(client1, client2, 1, true);
        hotel.rooms[0] = doubleRoomLuxury;

        systemInMock2.provideLines("n");
        hotel.checkout(1);
        Mockito.verify(hotel, Mockito.times(1)).checkout(1);*/
    }

    @Test
    public void order() {
        /*Client client1 = new Client("test1", "test1", "test1");
        Client client2 = new Client("test2", "test2", "test2");

        DoubleRoom doubleRoomLuxury = new DoubleRoom(client1, client2, 1, true);
        hotel.rooms[0] = doubleRoomLuxury;

        systemInMock.provideLines("1", "1", "n");
        hotel.order(1);
        Mockito.verify(hotel, Mockito.times(1)).order(1);*/
    }

    @Test
    public void calculateBill() {
        Client client1 = new Client("test1", "test1", "test1");
        Client client2 = new Client("test2", "test2", "test2");

        DoubleRoom doubleRoomLuxury = new DoubleRoom(client1, client2, 1, true);
        doubleRoomLuxury.foods.add(new Food(Food.FoodType.Sandwich, 1));
        DoubleRoom doubleRoomNotLuxury = new DoubleRoom(client1, client2, 2, false);

        assertEquals(4050, hotel.calculateBill(doubleRoomLuxury));
        assertEquals(3000, hotel.calculateBill(doubleRoomNotLuxury));

        SingleRoom singleRoomLuxury = new SingleRoom(client1, 1, true);
        SingleRoom singleRoomNotLuxury = new SingleRoom(client1, 2, false);

        assertEquals(2200, hotel.calculateBill(singleRoomLuxury));
        assertEquals(1200, hotel.calculateBill(singleRoomNotLuxury));
    }
}
