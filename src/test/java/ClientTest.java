import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertNotNull;

public class ClientTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    private String contact = "contact";
    private String name = "name";
    private String gender = "gender";

    @Test
    public void clientName() {
        Client client = new Client(name, contact, gender);
        assert (client.getName().equals(name));
    }

    @Test
    public void clientContact() {
        Client client = new Client(name, contact, gender);
        assert (client.getContact().equals(contact));
    }

    @Test
    public void clientGender() {
        Client client = new Client(name, contact, gender);
        assert (client.getGender().equals(gender));
    }

    @Test
    public void createClientFromInput() {
        String clientPosition = "first";
        systemInMock.provideLines("test", "test", "test");
        Client client = Client.createClientFromInput(clientPosition);
        assertNotNull(client);
    }
}
