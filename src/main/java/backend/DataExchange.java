package backend;

import java.sql.*;

public class DataExchange {

    // This method focuses on getting an input location and date and showing the user which seats are available.
    // So far there is only one flight so don't expect much.
    public static void dbSearch(String location, String date) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Class.forName("org.sqlite.JDBC");
        try{
            c = DriverManager.getConnection("jdbc:sqlite:src/main/flightsystem.db");

            // Testing that given the Location and Date of travels, the available seats will be printed out.
            String sql = "Select Seat from Flights, Airport where Flights.FlightID = Airport.FlightID AND Location = (?) AND DepDate = (?) AND Taken=FALSE";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, location);
            preparedStatement.setString(2,date);
            ResultSet result = preparedStatement.executeQuery();


            while(result.next()){
                System.out.println(result.getString(1) + " is available");
            }


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
