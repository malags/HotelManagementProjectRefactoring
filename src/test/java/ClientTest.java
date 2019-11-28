import org.junit.Test;

public class ClientTest {

    String contact = "contact";
    String name = "name";
    String gender = "gender";

    @Test
    public void clientName(){
        Client client = new Client(name,contact,gender);
        assert (client.getName().equals(name));
    }

    @Test
    public void clientContact(){
        Client client = new Client(name,contact,gender);
        assert (client.getContact().equals(contact));
    }

    @Test
    public void clientGender(){
        Client client = new Client(name,contact,gender);
        assert (client.getGender().equals(gender));
    }

}
