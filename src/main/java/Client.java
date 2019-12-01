import java.io.Serializable;
import java.util.Scanner;

/**
 * Class representing a customer.
 */
public class Client implements Serializable {

    /**
     * Fields that represent a customer.
     * Represented by name, contact and gender.
     */
    final private String name;
    final private String contact;
    final private String gender;

    public Client(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    /**
     * @return the value of the customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value of the customer's contact.
     */
    public String getContact() {
        return contact;
    }

    /**
     * @return the value of the customer's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param customerPosition
     * @return
     *
     * Static method that allows defining a new customer.
     * Return a new customer type object.
     */
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
