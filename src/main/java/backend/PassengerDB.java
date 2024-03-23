package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PassengerDB {
    /* Where all queries about finding and updating and creating Passengers will be processed.*/
    static Connection c;

    public PassengerDB() throws ClassNotFoundException {
        initialize();
    }

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
