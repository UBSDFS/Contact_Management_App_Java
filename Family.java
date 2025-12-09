/*
 * Ulysses Burden III
 * November 25, 2025
 * Description: This class represents a Family contact, which is a specific type of Contact.
 * with a relationship attribute.
 */
public class Family extends Contact {

    //Additional attribute specific to Family
    private String relationship;

    // Constructor
    public Family(String firstName, String lastName, String phoneNumber, String email, Address address, String relationship) {
        super(firstName, lastName, phoneNumber, email, address);
        this.relationship = relationship;

    }

    //Constructor with only first and last name and relationship for Family
    public Family(String firstName, String lastName, String relationship) {
        super(firstName, lastName);
        this.relationship = relationship;
    }

    // Getter and Setter for relationship
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    //Override method from ContactType interface
    @Override
    public String getContactType() {
        return "Family";
    }

    @Override
    public void printSummary() {
        System.out.println("[Family] ID: " + getContactId()
                + "| Name: " + getFirstName()
                + " " + getLastName()
                + "|Phone: " + getPhoneNumber()
                + "| Relationship: " + relationship);
    }

    //Override toString to include relationship
    @Override
    public String toString() {
        return super.toString() + "\nRelationship: " + relationship;
    }

}
