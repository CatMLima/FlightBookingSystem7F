package backend;

import java.sql.*;
import java.util.ArrayList;

public class DataExchange {

    // This method focuses on getting an input location and date and showing the user which seats are available.
    // So far there is only one flight so don't expect much.
    public static ArrayList<String> dbSearch(String location, String destination, String date) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Class.forName("org.sqlite.JDBC");

        try{
            c = DriverManager.getConnection("jdbc:sqlite:src/main/flightsystem.db");

            // Testing that given the Location and Date of travels, the available seats will be printed out.
            String sql = "Select DISTINCT Flights.FlightID, DepTime from Flights, Airport a1, Airport a2 where Flights.FlightID = a1.FlightID AND Flights.FlightID = a2.FlightID AND a1.Location = (?) AND a2.location = (?) AND DepDate = (?) AND Taken=FALSE";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, location);
            preparedStatement.setString(2,destination);
            preparedStatement.setString(3,date);
            ResultSet result = preparedStatement.executeQuery();

            // The array list that gets populated with the unique fligthID and departure time combinations for now.
            ArrayList<String> flights = new ArrayList<>();
            int count = 0;

            while(result.next()){
                flights.add("flight: " + result.getString(1) + " at " + result.getString(2) + ".");
            }
            return flights;

        } catch (Exception e){
            System.out.println("No connection was possible.");
        } finally {
            if (c != null){
                c.close();
            }
        }
        return null;
    }
}
