import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class HotelTest {
    final Client client1 = new Client("name","contact","gender");
    final Client client2 = new Client("name2","contact2","gender2");
    final int roomNumber = 1;
    Hotel hotel = new Hotel();

    @Test
    public void hotelHasRightSize(){
        assert (hotel.rooms.length == 60);
    }

    @Test
    public void hotelLuxuryDoubleNrAvailable(){
        assert (hotel.availability(Hotel.RoomType.DoubleLuxury) == 10);
    }

    @Test
    public void hotelNotLuxuryDoubleNrAvailable(){
        assert (hotel.availability(Hotel.RoomType.DoubleNotLuxury) == 20);
    }

    @Test
    public void hotelLuxurySingleNrAvailable(){
        assert (hotel.availability(Hotel.RoomType.SingleLuxury) == 10);
    }

    @Test
    public void hotelNotLuxurySingleNrAvailable(){
        assert (hotel.availability(Hotel.RoomType.SingleNotLuxury) == 20);
    }

    @Test
    public void availableSingleRoomNotLuxurySize(){
        List<Room> availableSingleNotLuxury = hotel.availableRooms(false,false);
        assert (availableSingleNotLuxury.size() == 20);
    }

    @Test
    public void availableSingleRoomLuxurySize(){
        List<Room> availableSingleLuxury = hotel.availableRooms(false,true);
        assert (availableSingleLuxury.size() == 10);
    }

    @Test
    public void availableDoubleRoomNotLuxurySize(){
        List<Room> availableDoubleNotLuxury = hotel.availableRooms(true,false);
        assert (availableDoubleNotLuxury.size() == 20);
    }

    @Test
    public void availableDoubleRoomLuxurySize(){
        List<Room> availableDoubleLuxury = hotel.availableRooms(true,true);
        assert (availableDoubleLuxury.size() == 10);
    }

    @Test
    public void availableSingleRoomNotLuxuryType(){
        List<Room> availableSingleNotLuxury = hotel.availableRooms(false,false);
        for(Room room : availableSingleNotLuxury){
            assert (room.isEmpty());
            assert (!room.isLuxury());
            assert (room instanceof SingleRoom);
        }
    }

    @Test
    public void availableSingleRoomLuxuryType(){
        List<Room> availableSingleLuxury = hotel.availableRooms(false,true);
        for(Room room : availableSingleLuxury){
            assert (room.isEmpty());
            assert (room.isLuxury());
            assert (room instanceof SingleRoom);
        }
    }

    @Test
    public void availableDoubleRoomNotLuxuryType(){
        List<Room> availableDoubleNotLuxury = hotel.availableRooms(true,false);
        for(Room room : availableDoubleNotLuxury){
            assert (room.isEmpty());
            assert (!room.isLuxury());
            assert (room instanceof DoubleRoom);
        }
    }

    @Test
    public void availableDoubleRoomLuxuryType(){
        List<Room> availableDoubleLuxury = hotel.availableRooms(true,true);
        for(Room room : availableDoubleLuxury){
            assert (room.isEmpty());
            assert (room.isLuxury());
            assert (room instanceof DoubleRoom);
        }
    }

    @Test
    public void availableSingleRoomNotLuxurySizeBooked(){
        final boolean doubleRoom = false;
        final boolean luxury = false;
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 20);
        hotel.availableRooms(doubleRoom,luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 19);
    }

    @Test
    public void availableSingleRoomLuxurySizeBooked(){
        final boolean doubleRoom = false;
        final boolean luxury = true;
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 10);
        hotel.availableRooms(doubleRoom,luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 9);
    }

    @Test
    public void availableDoubleRoomNotLuxurySizeBooked(){
        final boolean doubleRoom = true;
        final boolean luxury = false;
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 20);
        hotel.availableRooms(doubleRoom,luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 19);
    }

    @Test
    public void availableDoubleRoomLuxurySizeBooked(){
        final boolean doubleRoom = true;
        final boolean luxury = true;
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 10);
        hotel.availableRooms(doubleRoom,luxury).get(0).book(client1);
        assert (hotel.availableRooms(doubleRoom,luxury).size() == 9);
    }



    //TODO extra tests


}
