package backend;

import java.sql.*;

public class DataExchange {

    // This method focuses on getting a input location and date and showing the user which seats are available.
    // So far there is only one flight so don't expect much.
    public static void dbSearch(String location, String date) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Class.forName("org.sqlite.JDBC");
        try{
            c = DriverManager.getConnection("jdbc:sqlite:src/main/flightsystem.db");




            // Testing that the connection works by printing it.
            Statement s = c.createStatement();
            ResultSet data = s.executeQuery("Select * from flights where taken=false");

            // Prints out the result of the query above.
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
