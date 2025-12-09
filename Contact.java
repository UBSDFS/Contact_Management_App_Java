/*
 * Ulysses Burden III
 * November 28, 2025
 *Description: This class represents a generic Contact with basic contact information. This is the Superclass for specific contact types
 * and demonstrates the creation of an interface and inheritance.
 */
//Contact class demonstrating the creation of an interface and inheritance
public abstract class Contact {

    //Unique identifier for DB purposes
    private int contactId;
    // Basic contact attributes with private access modifiers
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    // Composition relationship from Address class. Contact "has a" Address
    private Address address;

    //No-argument constructor to be used by subclasses such as Business, Family, and Friend
    protected Contact() {
    }

    //
    protected Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor. works in conjunction with ArrayList in ContactManager to store multiple contacts
    protected Contact(String firstName, String lastName, String phoneNumber, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Abstract method to be implemented by subclasses for printing a summary of the contact
    public abstract void printSummary();

    // Abstract method to get contact type
    public abstract String getContactType();

    // Getters and Setters for encapsulation
    public int getContactId() {
        return contactId;
    }

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

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Override toString method to display contact information
    @Override
    public String toString() {
        return "Contact ID: " + contactId
                + ", Name: " + firstName + " " + lastName
                + ", Phone: " + phoneNumber
                + ", Email: " + email
                + ", Address: " + address;
    }
}
