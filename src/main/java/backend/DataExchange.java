package backend;

import java.sql.*;

public class DataExchange {
    public static void dbSearch() throws SQLException, ClassNotFoundException {
        Connection c = null;
        Class.forName("org.sqlite.JDBC");
        try{
            c = DriverManager.getConnection("jdbc:sqlite:src/main/flightsystem.db");

            // Testing that the information was inserted by printing it.
            Statement s = c.createStatement();
            ResultSet data = s.executeQuery("Select * from flights where taken=false");

            // This prints out all the information in the flights table to check that it was updated.
            while (data.next()){
                System.out.println(data.getString(1) + " " + data.getString(2));
            }



        } catch (Exception e){
            System.out.println("No connection was possible.");
        } finally {
            if (c != null){
                c.close();
            }
        }
    }
}
