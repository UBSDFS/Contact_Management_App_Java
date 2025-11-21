/*
 * Ulysses Burden III
 * November 13, 2025
 * Assignment: Week 2 - Project- Show Composition
 * Description: This class represents a Business contact, which is a specific type of Contact.
 * with companyName and jobTitle attributes.
 */

public class Business extends Contact {
    //Additional attribute specific to Friend

    private String companyName;
    private String jobTitle;

    // Constructor
    public Business(String firstName, String lastName, String phoneNumber, String email, Address address, String companyName, String jobTitle) {
        super(firstName, lastName, phoneNumber, email, address);
        this.companyName = companyName;
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    //Override method from ContactType interface
    @Override
    public String getContactType() {
        return "Business";
    }

    //Override toString to include companyName and jobTitle
    @Override
    public String toString() {
        return super.toString() + "\nCompany Name: " + companyName + "\nJob Title: " + jobTitle;
    }

}
