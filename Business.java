/*
 * Ulysses Burden III
 * November 25, 2025
 * Assignment: Week 3 - Project- Show Composition
 * Description: This class represents a Business contact, which is a specific type of Contact.
 * with companyName and jobTitle attributes.
 */

public class Business extends Contact {
    //Additional attribute specific to Friend

    private String companyName;
    private String jobTitle;

    //Complete Constructor
    public Business(String firstName, String lastName, String phoneNumber, String email, Address address, String companyName, String jobTitle) {
        super(firstName, lastName, phoneNumber, email, address);
        this.companyName = companyName;
        this.jobTitle = jobTitle;
    }

    //Constructor with only first and last name, companyName and jobTitle for Business
    public Business(String firstName, String lastName, String companyName, String jobTitle) {
        super(firstName, lastName);
        this.companyName = companyName;
        this.jobTitle = jobTitle;
    }

    // Getter and Setter for companyName and jobTitle
    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    //Override method from ContactType interface
    @Override
    public String getContactType() {
        return "Business";
    }

    @Override
    public void printSummary() {
        System.out.println("[Business] ID: " + getContactId()
                + "| Name: " + getFirstName()
                + " " + getLastName()
                + "| Phone: " + getPhoneNumber()
                + "| Company: " + companyName
                + "| Job Title: " + jobTitle);
    }

    //Override toString to include companyName and jobTitle
    @Override
    public String toString() {
        return super.toString() + "\nCompany Name: " + companyName + "\nJob Title: " + jobTitle;
    }

}
