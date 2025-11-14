/*
 * Ulysses Burden III
 * November 13, 2025
 * Assignment: Week 1 - Project- Show Composition
 *Description: This class represents a Friend contact, which is a specific type of Contact.
 */
//Inheritance relationship. Friend "is a" Contact
public class Friend extends Contact {

    //Additional attribute specific to Friend
    private String contactType;

    // Constructor
    public Friend(String firstName, String lastName, String phoneNumber, String email, Address address, String contactType) {
        super(firstName, lastName, phoneNumber, email, address);
        this.contactType = contactType;
    }

    public String getContactType() {
        return contactType;
    }

    //Override toString to include contactType
    @Override
    public String toString() {
        return super.toString() + "\nContact Type: " + contactType;
    }

}
