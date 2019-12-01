import org.junit.Test;
import org.mockito.Mockito;


public class WriterTest {

    @Test
    public void run() {
        Hotel hotel = Mockito.mock(Hotel.class);
        Writer writer = new Writer(hotel);
        writer.run();
    }
}