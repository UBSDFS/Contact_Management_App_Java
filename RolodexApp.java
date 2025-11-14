/*
 * Ulysses Burden III
 * November 13, 2025
 * Assignment: Week 1 - Project- Show Composition
 *
 */
import java.util.Scanner;

public class RolodexApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Header
        System.out.println();
        System.out.println();
        System.out.println("Project Week 1 Rolodex Demo.");
        System.out.println();
        System.out.println("Developed by: Ulysses Burden III");
        System.out.println();
        System.out.println("Welcome to the Rolodex demo. \nThis program will show how a few examples of\nhow contacts can be created using \ncomposition and inheritance in Java. \nThis is just a preview. \nMore features will be added in future weeks.");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("----------Main Menu Demo----------");
            System.out.println();
            System.out.println("1. Add Contact");
            System.out.println("0. Exit");
            System.out.println("-----------------------------------");
            System.out.print("Please enter your choice: ");
            System.out.println();

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            if (choice == 1) {

                System.out.println();
                System.out.println("Adding a Friend Contact...");
                System.out.println();
                System.out.println("Please enter the following information:");

                System.out.print("First Name: ");
                String firstName = input.nextLine();

                System.out.print("Last Name: ");
                String lastName = input.nextLine();

                System.out.print("Phone Number: ");
                String phoneNumber = input.nextLine();

                System.out.print("Email: ");
                String email = input.nextLine();

                System.out.print("Street Address: ");
                String street = input.nextLine();

                System.out.print("City: ");
                String city = input.nextLine();

                System.out.print("State: ");
                String state = input.nextLine();

                System.out.print("Zipcode: ");
                String zipcode = input.nextLine();

                System.out.print("Contact Type (e.g., Friend, Family): ");
                String contactType = input.nextLine();

                System.out.println();
                
                //Create an Address Object
                Address friendAddress = new Address(street, city, state, zipcode);
                
                //Create a Contact Object
                Friend friend = new Friend(firstName, lastName, phoneNumber, email, friendAddress, contactType);
                
                //Display Friend Information
                System.out.println(friend.toString());
            } else if (choice == 0) {
                System.out.println("Exiting the program. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice. Exiting the program.");
            }

        }
        input.close();
    }
}
