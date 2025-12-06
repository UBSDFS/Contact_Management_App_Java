/*
 * Name: Ulysses Burden III
 * Date: December 4, 2025
 * Assignment: SDC330L Week 4 GP – Database Interactions
 *
 * Class to handle database interactions with a SQLite database. The
 * connect method will either connect to an existing database or
 * create the database if the database doesn't exist.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// SQLiteDatabase class to manage SQLite database connections
public class SQLiteDatabase {

    // Method to connect to a SQLite database
    public static Connection connect(String database) {
        String url = "jdbc:sqlite:" + database;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
