package backend;
import java.sql.*;

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
            String test = "SELECT * FROM flights";
            //try (PreparedStatement pstmt2 = c.prepareStatement(test))
        } catch (Exception e){
            System.out.println("No connection was possible.");
        } finally {
            if (c != null){
                c.close();
            }
        }
    }


}
