import org.junit.Test;


import static org.junit.Assert.*;


public class NotAvailableTest {

    @Test
    public void testToString() {
        final NotAvailable notAvailable = new NotAvailable(1);
        assertEquals(notAvailable.toString(), "Room " + notAvailable.id + " not Available!");
    }
}
