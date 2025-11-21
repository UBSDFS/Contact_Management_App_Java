/*
 * Ulysses Burden III
 * November 13, 2025
 * Assignment: Week 2
 *Description: This is the main application class for the Rolodex contact management system.
 */
import java.util.Scanner;

public class RolodexApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        manager.run();
        scanner.close();
    }
}
