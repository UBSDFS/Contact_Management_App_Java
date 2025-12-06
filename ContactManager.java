/*
 * Ulysses Burden III
 * Date: December 4, 2025
 * Assignment: SDC330L Week 4 - Project
 * Description: This class manages a collection of Contact objects. Handles logic for adding, removing, and displaying contacts.
 * Demonstrates use of inheritance, composition, and polymorphism and interfaces.
 * Interacts with ContactStorage for database persistence.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {

    private final List<Contact> contacts; // List to store Contact objects locally
    private final Scanner scanner; // Scanner for user input in the manager and menu loop
    private final ContactStorage storage; // Handles database operations

    // Constructor for ContactManager to initialize the contacts list and scanner
    public ContactManager(ContactStorage storage) {
        this.contacts = new ArrayList<>(); // Initialize the contacts list locally
        this.scanner = new Scanner(System.in);
        this.storage = storage; // Initialize storage for database operations
    }

    // Helper methods used for reading text input from user
    //Used for name, phone, email, etc.
    private String readString(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    // Helper method to read an integer from user
    // Used for menu choices
    private int readInt(String prompt) {
        {
            System.out.print(prompt + " ");
            String line = scanner.nextLine();
            return Integer.parseInt(line);
        }
    }

    // Helper method to read Address details
    // Creates and returns an Address object
    //Demonstrates composition relationship
    // Contact "has a" Address
    private Address readAddress() {
        System.out.println("\nEnter Address Details:");
        String street = readString("Street:");
        String city = readString("City:");
        String state = readString("State:");
        String zipcode = readString("Zip Code:");
        return new Address(street, city, state, zipcode);
    }

    // Method to find a contact by full name (case insensitive)
    public Contact findContactByName() {
        String name = readString("Enter the full name of the contact to find:");
        String lower = name.trim().toLowerCase();

        for (Contact c : contacts) {
            String fullName = (c.getFirstName() + " " + c.getLastName()).trim().toLowerCase();
            if (fullName.equals(lower) || fullName.contains(lower)) {
                return c;
            }
        }
        return null;
    }

    // Main loop to run the contact manager
    public void run() {
        int choice;
        do {
            showMenu();
            choice = readInt("Choose an option:");
            handleChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n---Project Week 4 Rolodex Application---");
        System.out.println("Developed by: Ulysses Burden III");
        System.out.println("\nUse the menu below to manage your contacts:");
        System.out.println("-----------------------------------");
        System.out.println("1. Add Contact");
        System.out.println("2. Find Contact");
        System.out.println("3. Update Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Display All Contacts");
        System.out.println("6. Display Contacts by Type");
        System.out.println("0. Exit");
    }

    // Switch case to handle user menu choices
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addContact();
                break;
            case 2:
                findContact();
                break;
            case 3:
                updateContact();
                break;
            case 4:
                deleteContact();
                break;
            case 5:
                displayAllContacts();
                break;
            case 6:
                displayContactsByType();
                break;
            case 0:
                System.out.println("Exiting Contact Manager. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Implementation for adding a contact based on user input
    private void addContact() {
        System.out.println("\nAdding a new contact...");
        System.out.println("Select contact type to add:");
        System.out.println("1. Friend");
        System.out.println("2. Family");
        System.out.println("3. Business");

        int typeChoice = readInt("Enter choice:");
        // Common contact details
        String firstName = readString("First Name:");
        String lastName = readString("Last Name:");
        String phoneNumber = readString("Phone Number:");
        String email = readString("Email:");
        // Read address details using composition
        Address address = readAddress();

        // Create specific contact based on type. Demonstrates inheritance relationship
        //and polymorphism by using Contact superclass reference and overriding methods from subclasses
        Contact newContact;
        switch (typeChoice) {
            case 1:
                String nickName = readString("Nickname:");
                newContact = new Friend(firstName, lastName, phoneNumber, email, address, nickName);
                break;
            case 2:
                String relationship = readString("Relationship:");
                newContact = new Family(firstName, lastName, phoneNumber, email, address, relationship);
                break;
            case 3:
                String companyName = readString("Company Name:");
                String jobTitle = readString("Job Title:");
                newContact = new Business(firstName, lastName, phoneNumber, email, address, companyName, jobTitle);
                break;
            default:
                System.out.println("Invalid contact type. Returning to main menu.");
                return;
        }
        // Add the new contact to the list and storage
        contacts.add(newContact);
        storage.addContact(newContact);
        System.out.println("New contact ID: " + newContact.getContactId());
        newContact.printSummary();
    }

    // Implementation for finding a contact by name
    private void findContact() {
        System.out.println("\nFinding a contact...");
        Contact result = findContactByName();
        if (result == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Contact found:");
            System.out.println(result.toString());
        }

    }

    // Implementation for updating a contact
    private void updateContact() {
        System.out.println("\nUpdate contact....");
        Contact target = findContactByName();
        if (target == null) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Contact found:");
        System.out.println(target.toString()); // Display current details
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone Number");
        System.out.println("4. Email");
        System.out.println("5. Address");
        System.out.println("6. Type-specific (Nickname/Relationship/Company Info)");
        int updateChoice = readInt("Enter choice:");
        switch (updateChoice) {
            case 1:
                String newFirstName = readString("Enter new first name:");
                target.setFirstName(newFirstName);
                break;
            case 2:
                String newLastName = readString("Enter new last name:");
                target.setLastName(newLastName);
                break;
            case 3:
                String newPhoneNumber = readString("Enter new phone number:");
                target.setPhoneNumber(newPhoneNumber);
                break;
            case 4:
                String newEmail = readString("Enter new email:");
                target.setEmail(newEmail);
                break;
            case 5:
                Address newAddress = readAddress();
                target.setAddress(newAddress);
                break;
            case 6:
                updateTypeSpecificFields(target);
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }
        // Update the contact in local storage
        System.out.println("Contact updated successfully!");
        System.out.println(target.toString());
        // Update the contact in the database
        boolean yes = storage.updateContact(target);
        if (yes) {
            System.out.println("Contact updated in database successfully.");
        } else {
            System.out.println("Failed to update contact in database.");
        }
    }

    // Helper method to update fields that are specific to subclasses.
    private void updateTypeSpecificFields(Contact target) {
        if (target instanceof Friend) {
            Friend friend = (Friend) target;
            String newNickName = readString("Enter new nickname:");
            friend.setNickName(newNickName);
            System.out.println("Friend nickname updated successfully.");
        } else if (target instanceof Family) {
            Family family = (Family) target;
            String newRelationship = readString("Enter new relationship:");
            family.setRelationship(newRelationship);
            System.out.println("Family relationship updated successfully.");
        } else if (target instanceof Business) {
            Business business = (Business) target;
            String newCompanyName = readString("Enter new company name:");
            String newJobTitle = readString("Enter new job title:");
            business.setCompanyName(newCompanyName);
            business.setJobTitle(newJobTitle);
            System.out.println("Business details updated successfully.");
        }
    }

    private void deleteContact() {
        System.out.println("\nDeleting a contact...");

        Contact target = findContactByName();
        if (target == null) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Contact found:");
        System.out.println(target.toString());
        String confirmation = readString("Are you sure you want to delete this contact? (yes/no):");
        if (confirmation.equalsIgnoreCase("yes")) {
            contacts.remove(target);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
        // Delete the contact from the database
        boolean yes = storage.deleteContact(target.getContactId());
        if (yes) {
            System.out.println("Contact deleted from database successfully.");
        } else {
            System.out.println("Failed to delete contact from database.");
        }
    }

    // Implementation for displaying all contacts.
    private void displayAllContacts() {
        System.out.println("\nDisplaying all contacts...");

        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact c : contacts) {
                c.printSummary();
            }
        }
    }

    // Demonstrates polymorphism by calling overridden getContactType method
    private void displayContactsByType() {
        System.out.println("\nDisplaying contacts by type...");
        System.out.println("1. Friends");
        System.out.println("2. Family");
        System.out.println("3. Business");

        int typeChoice = readInt("Select contact type to display:");
        String type;
        switch (typeChoice) {
            case 1:
                type = "Friend";
                break;
            case 2:
                type = "Family";
                break;
            case 3:
                type = "Business";
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }
        boolean found = false;
        //Uses interface method getContactType to filter contacts
        for (Contact c : contacts) {
            if (c.getContactType().equalsIgnoreCase(type)) {
                c.printSummary();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts of type " + type + " found.");
        }
    }

}
