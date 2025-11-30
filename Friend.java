/*
 * Ulysses Burden III
 * November 25, 2025
 * Assignment: Week 3 - Project- Show Composition
 *Description: This class represents a Friend contact, which is a specific type of Contact.
 *with a nickname attribute.
 */
//Inheritance relationship. Friend "is a" Contact
public class Friend extends Contact {

    //Additional attribute specific to Friend
    private String nickName;

    // Constructor for fully initializing a Friend object
    public Friend(String firstName, String lastName, String phoneNumber, String email, Address address, String nickName) {
        super(firstName, lastName, phoneNumber, email, address);
        this.nickName = nickName;
    }

    //Constructor with only first and last name and nickname for Friend
    public Friend(String firstName, String lastName, String nickName) {
        super(firstName, lastName);
        this.nickName = nickName;
    }

    // Getter and Setter for nickName
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    //Override toString to include nickName
    @Override()
    public String getContactType() {
        return "Friend";
    }

    @Override
    public void printSummary() {
        System.out.println("[Friend] ID: " + getContactId()
                + "| Name: " + getFirstName()
                + " " + getLastName()
                + "| Nickname: " + nickName);
    }

    @Override
    public String toString() {
        return super.toString() + "\nNickname: " + nickName;
    }
}
