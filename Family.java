/*
 * Ulysses Burden III
 * November 20, 2025
 * Assignment: Week 2 - Project- Show Composition
 * Description: This class represents a Family contact, which is a specific type of Contact.
 * with a relationship attribute.
 */
public class Family extends Contact {

    private String relationship;

    // Constructor
    public Family(String firstName, String lastName, String phoneNumber, String email, Address address, String relationship) {
        super(firstName, lastName, phoneNumber, email, address);
        this.relationship = relationship;

    }

    public String getRelationship() {
        return relationship;
    }

    //Override method from ContactType interface
    @Override
    public String getContactType() {
        return "Family";
    }

    //Override toString to include nickName
    @Override
    public String toString() {
        return super.toString() + "\nRelationship: " + relationship;
    }

}
