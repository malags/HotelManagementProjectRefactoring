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
        Scanner scanner = new Scanner(System.in);
        String name, contactInfo, gender;
        System.out.println("\nEnter " + customerPosition + "customer name:");
        name = scanner.nextLine();
        System.out.println("Enter contactInfo number:");
        contactInfo = scanner.nextLine();
        System.out.println("Enter gender:");
        gender = scanner.next();
        return new Client(name, contactInfo, gender);
    }

}
