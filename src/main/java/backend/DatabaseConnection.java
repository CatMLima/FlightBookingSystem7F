package backend;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    // This method will take whatever String input is given to it and insert it into the flights table under the columns flightID and Taken.
    public static void dbInsert(String flightID, String seat) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Class.forName("org.sqlite.JDBC");
        try{
            c = DriverManager.getConnection("jdbc:sqlite:src/main/flights1.db");
            String sql = "INSERT INTO flights (FlightID,Taken) VALUES (?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setString(1,flightID);
                pstmt.setString(2,seat);
                pstmt.executeUpdate();
            }

            // Testing that the information was inserted by printing it.
            Statement s = c.createStatement();
            ResultSet data = s.executeQuery("Select * from flights");

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
