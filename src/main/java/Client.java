import java.io.Serializable;
import java.util.Scanner;

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

    public static Client createClientFromInput(String customerPosition) {
        Scanner sc = new Scanner(System.in);
        String name, contact, gender;
        System.out.println("\nEnter " + customerPosition + "customer name:");
        name = sc.next();
        System.out.println("Enter contact number:");
        contact = sc.next();
        System.out.println("Enter gender:");
        gender = sc.next();
        return new Client(name, contact, gender);
    }

}
