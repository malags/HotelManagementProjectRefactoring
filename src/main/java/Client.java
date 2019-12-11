import ch.usi.si.codelounge.jsicko.Contract;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Class representing a customer.
 */
public class Client implements Serializable, Contract {

    @Pure
    @Invariant
    boolean client_has_values(){
        return this.name != null && this.contact != null && this.gender != null;
    }

    /**
     * Fields that represent a customer.
     * Represented by name, contact and gender.
     */
    final private String name;
    final private String contact;
    final private String gender;

    public Client(){
        this.name = null;
        this.contact = null;
        this.gender = null;
    }


    public Client(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    /**
     * @return the value of the customer's name.
     */
    @Pure
    @EnsuresNonNull("returns")
    public String getName() {
        return name;
    }

    /**
     * @return the value of the customer's contact.
     */
    @Pure
    @EnsuresNonNull("returns")
    public String getContact() {
        return contact;
    }

    /**
     * @return the value of the customer's gender.
     */
    @Pure
    @EnsuresNonNull("returns")
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
    @Pure //not from IO point of view, but makes no changes to parameters
    @EnsuresNonNull("returns")
    public static Client createClientFromInput(final String customerPosition) {
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
