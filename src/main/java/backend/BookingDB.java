package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookingDB {
    /*Where all queries about bookings and updating and creating them will reside.*/

    static Connection c;

    // initializes the connection from the controller
    public static void initialize() throws ClassNotFoundException {
        c = null;
        Class.forName("org.sqlite.JDBC");

        try {
            c = DriverManager.getConnection("jdbc:sqlite:src/main/flightsystem.db");
        } catch (Exception e){
            System.out.println("no connection was possible");
        }

    }

    public static void closeConnection() throws SQLException {
        c.close();
    }
}
