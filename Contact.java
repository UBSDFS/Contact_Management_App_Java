/*
 * Ulysses Burden III
 * November 13, 2025
 * Assignment: Week 2 Project
 *Description: This class represents a generic Contact with basic contact information. This is the Superclass for specific contact types
 * and demonstrates the creation of an interface and inheritance.
 */
//Contact class demonstrating the creation of an interface and inheritance
public abstract class Contact implements ContactType {

    // Basic contact attributes
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    // Composition relationship from Address class. Contact "has a" Address
    private Address address;

    // Constructor. works in conjunction with ArrayList in ContactManager to store multiple contacts
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

    // Method to print a summary of the contact (for displaying in lists)
    public void printSummary() {
        System.out.println(getContactType() + ": " + firstName + " " + lastName + " | Phone: " + phoneNumber + " | Email: " + email);
    }

    // Override toString method for detailed contact information
    @Override
    public String toString() {
        return firstName + " " + lastName + "\nPhone: " + phoneNumber + "\nEmail: " + email + "\nAddress: " + address.toString();
    }
}
