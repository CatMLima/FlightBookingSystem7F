package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SeatDB {
    /*Where queries about the seats will be processed. Use to update the status of a seat depending on the status of bookings.*/

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
