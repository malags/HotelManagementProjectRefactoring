import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;


public class Project3Test {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    private Scanner scanner;

    @Before
    public void setUp() {
        scanner = new Scanner(System.in);
    }

    @Test
    public void chooseRoomNumber01() {
        systemInMock.provideLines("1");
        int roomNumber = Project3.chooseRoomNumber(scanner);
        assertEquals(1, roomNumber);
    }

    @Test
    public void chooseRoomNumber02() {
        systemInMock.provideLines("0");
        int roomNumber = Project3.chooseRoomNumber(scanner);
        assertEquals(0, roomNumber);
    }

    @Test
    public void roomSelection() {
        systemInMock.provideLines("1");
        int roomSelection = Project3.roomSelection(scanner);
        assertEquals(1, roomSelection);
    }
}