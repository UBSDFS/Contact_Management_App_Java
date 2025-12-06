/*
 * Ulysses Burden III
 * Date: December 4, 2025
 * Assignment: SDC330L Week 4 - Project
 *Description: This is the main application class for the Rolodex contact management system.
 */
import java.sql.Connection;
import java.util.Scanner;

public class RolodexApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = SQLiteDatabase.connect("contacts.db");
        if (conn != null) {
            System.out.println("Connected successfully.");
        }
        ContactStorage storage = new ContactStorage(conn);
        ContactManager manager = new ContactManager(storage);
        manager.run();
        scanner.close();
    }
}
