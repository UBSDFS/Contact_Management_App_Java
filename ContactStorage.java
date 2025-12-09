/*
*Name: Ulysses Burden III
*Date: December 4, 2025
* Description: This class handles storage operations for Contact objects in a SQLite database.
*
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactStorage {

    // Database connection
    private final Connection conn;

    // Constructor for ContactStorage to initialize the database connection
    public ContactStorage(Connection conn) {
        this.conn = conn;
        createTable();
    }

    // Create contacts table if it doesn't exist into the database
    //with the appropriate fields for different contact types
    public boolean createTable() {
        if (conn == null) {
            System.out.println("No database connection.");
            return false;
        } // SQL statement for creating a new table with necessary fields
        String sql = "CREATE TABLE IF NOT EXISTS contacts (\n"
                + "	contactId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	firstName TEXT NOT NULL,\n"
                + "	lastName TEXT NOT NULL,\n"
                + "	phoneNumber TEXT,\n"
                + "	email TEXT,\n"
                + "	address TEXT,\n"
                + "	contactType TEXT NOT NULL,\n"
                + "	relationship TEXT,\n"
                + "	companyName TEXT,\n"
                + "	jobTitle TEXT,\n"
                + "	nickName TEXT\n"
                + ");";
        // Execute the SQL statement to create the table
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Contacts table created.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            return false;
        }
    }

    //Method to add a contact to the database
    public boolean addContact(Contact contact) {
        if (conn == null) {
            System.out.println("No database connection.");
            return false;
        }
        String sql = "INSERT INTO contacts(firstName, lastName, phoneNumber,email, address, contactType, relationship, companyName, jobTitle, nickName) VALUES(?,?,?,?,?,?,?,?,?,?)";

        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();
        String phoneNumber = contact.getPhoneNumber();
        String email = contact.getEmail();
        String addressText = (contact.getAddress() != null ? contact.getAddress().toString() : null);
        String contactType = contact.getContactType();
        String relationship = null;

        String companyName = null;
        String jobTitle = null;
        String nickName = null;

        if (contact instanceof Family) {
            relationship = ((Family) contact).getRelationship();
        } else if (contact instanceof Business) {
            companyName = ((Business) contact).getCompanyName();
            jobTitle = ((Business) contact).getJobTitle();
        } else if (contact instanceof Friend) {
            nickName = ((Friend) contact).getNickName();
        }

        try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, phoneNumber);
            pst.setString(4, email);
            pst.setString(5, addressText);
            pst.setString(6, contactType);
            pst.setString(7, relationship);
            pst.setString(8, companyName);
            pst.setString(9, jobTitle);
            pst.setString(10, nickName);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                try (var keys = pst.getGeneratedKeys()) {
                    if (keys.next()) {
                        int contactId = keys.getInt(1);
                        contact.setContactId(contactId);
                    }
                }
                return true;
            } else {
                System.out.println("No rows inserted.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error adding contact: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve all contacts from the database
    public List<Contact> getAllContacts() {
        List<Contact> results = new ArrayList<>();
        if (conn == null) {
            System.out.println("No database connection.");
            return results;
        }
        String sql = "SELECT contactId, firstName, lastName, phoneNumber, email, address," + " contactType, relationship, companyName, jobTitle, nickName " + "FROM contacts";
        // Execute the query and build Contact objects based on the contact type
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("contactId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String addressText = rs.getString("address");
                String type = rs.getString("contactType");
                String relationship = rs.getString("relationship");
                String companyName = rs.getString("companyName");
                String jobTitle = rs.getString("jobTitle");
                String nickName = rs.getString("nickName");
                // Create Address object if addressText is not null
                Address address = null;
                if (addressText != null) {
                    address = new Address(addressText, "", "", "");
                }
                Contact c;
                switch (type) {
                    case "Family":
                        c = new Family(firstName, lastName, phoneNumber, email, address, relationship);
                        break;
                    case "Business":
                        c = new Business(firstName, lastName, phoneNumber, email, address, companyName, jobTitle);
                        break;
                    case "Friend":
                        c = new Friend(firstName, lastName, phoneNumber, email, address, nickName);
                        break;
                    default:
                        System.out.println("Unknown contact type: " + type);
                        continue;
                }
                c.setContactId(id);
                results.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving contacts: " + e.getMessage());
        }
        return results;
    }

    // Method to find a contact by full name (first + last)
    public Contact findContactByFullName(String fullName) {
        if (conn == null) {
            System.out.println("No database connection.");
            return null;
        }

        String sql = "SELECT contactId, firstName, lastName, phoneNumber, email, address, "
                + "contactType, relationship, companyName, jobTitle, nickName "
                + "FROM contacts WHERE LOWER(firstName || ' ' || lastName) = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, fullName.trim().toLowerCase());
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                int id = rs.getInt("contactId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String addressText = rs.getString("address");
                String type = rs.getString("contactType");
                String relationship = rs.getString("relationship");
                String companyName = rs.getString("companyName");
                String jobTitle = rs.getString("jobTitle");
                String nickName = rs.getString("nickName");

                Address address = null;
                if (addressText != null) {
                    address = new Address(addressText, "", "", "");
                }

                Contact c;
                switch (type) {
                    case "Family":
                        c = new Family(firstName, lastName, phone, email, address, relationship);
                        break;
                    case "Business":
                        c = new Business(firstName, lastName, phone, email, address, companyName, jobTitle);
                        break;
                    case "Friend":
                    default:
                        c = new Friend(firstName, lastName, phone, email, address, nickName);
                        break;
                }

                c.setContactId(id);
                return c;
            }
        } catch (SQLException e) {
            System.out.println("Error finding contact: " + e.getMessage());
            return null;
        }
    }

    // Method to update an existing contact in the database
    public boolean updateContact(Contact contact) {
        // Implementation for updating a contact in the database
        if (conn == null) {
            System.out.println("No database connection.");
            return false;
        }
        String sql = "UPDATE contacts SET"
                + " firstName = ?,"
                + " lastName = ?,"
                + " phoneNumber = ?,"
                + " email = ?,"
                + " address = ?,"
                + " contactType = ?,"
                + " relationship = ?,"
                + " companyName = ?,"
                + " jobTitle = ?,"
                + " nickName = ?"
                + " WHERE contactId = ?";

        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();
        String phoneNumber = contact.getPhoneNumber();
        String email = contact.getEmail();
        String addressText = (contact.getAddress() != null ? contact.getAddress().toString() : null);
        String contactType = contact.getContactType();

        String relationship = null;
        String companyName = null;
        String jobTitle = null;
        String nickName = null;

        if (contact instanceof Family) {
            relationship = ((Family) contact).getRelationship();
        } else if (contact instanceof Business) {
            companyName = ((Business) contact).getCompanyName();
            jobTitle = ((Business) contact).getJobTitle();
        } else if (contact instanceof Friend) {
            nickName = ((Friend) contact).getNickName();
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, phoneNumber);
            pst.setString(4, email);
            pst.setString(5, addressText);
            pst.setString(6, contactType);
            pst.setString(7, relationship);
            pst.setString(8, companyName);
            pst.setString(9, jobTitle);
            pst.setString(10, nickName);
            pst.setInt(11, contact.getContactId());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating contact: " + e.getMessage());
            return false;
        }
    }
    // Method to delete a contact by contactId

    public boolean deleteContact(int contactId) {
        if (conn == null) {
            System.out.println("No database connection.");
            return false;
        }
        String sql = "DELETE FROM contacts WHERE contactId = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, contactId);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Contact with ID: " + contactId + " deleted successfully.");
                return true;
            } else {
                System.out.println("No contact found with ID: " + contactId + ".");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting contact: " + e.getMessage());
            return false;
        }

    }
}
