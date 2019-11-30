import java.io.Serializable;

public class Client implements Serializable {

    final private String name;
    final private String contact;
    final private String gender;

    public Client(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getGender() {
        return gender;
    }

}
