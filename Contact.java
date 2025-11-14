/*
 * Ulysses Burden III
 * November 13, 2025
 * Assignment: Week 1 - Project- Show Composition
 *Description: This class represents a generic Contact with basic contact information. This is the Superclass for specific contact types.
 */

public class Contact {

    // Basic contact attributes
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    // Composition relationship from Address class. Contact "has a" Address
    private Address address;

    // Constructor
    public Contact(String firstName, String lastName, String phoneNumber, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\nPhone: " + phoneNumber + "\nEmail: " + email + "\nAddress: " + address.toString();
    }
}
