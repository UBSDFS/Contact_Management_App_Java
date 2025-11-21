/*
 * Ulysses Burden III
 * November 20, 2025
 * Assignment: Week 2 - Project- Show Composition
 *Description: This class represents a Friend contact, which is a specific type of Contact.
 *with a nickname attribute.
 */
//Inheritance relationship. Friend "is a" Contact
public class Friend extends Contact {

    //Additional attribute specific to Friend
    private String nickName;

    // Constructor
    public Friend(String firstName, String lastName, String phoneNumber, String email, Address address, String nickName) {
        super(firstName, lastName, phoneNumber, email, address);
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    @Override()
    public String getContactType() {
        return "Friend";
    }

    @Override
    public String toString() {
        return super.toString() + "\nNickname: " + nickName;

    }
}
