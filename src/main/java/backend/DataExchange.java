package backend;

import java.sql.*;
import java.util.ArrayList;

public class DataExchange {

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

    // This method focuses on getting an input location and date and showing the user which seats are available.
    // So far there is only one flight so don't expect much.
    public static ArrayList<String> dbSearch(String location, String destination, String date) throws SQLException, ClassNotFoundException {
        try{

            // Testing that given the Location and Date of travels the name of the flight and time shows up.
            String sql = "Select DISTINCT Flights.FlightID, DepTime from Flights, Airport a1, Airport a2 where Flights.FlightID = a1.FlightID AND Flights.FlightID = a2.FlightID AND a1.Location = (?) AND a2.location = (?) AND DepDate = (?) AND Taken=FALSE";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, location);
            preparedStatement.setString(2,destination);
            preparedStatement.setString(3,date);
            ResultSet result = preparedStatement.executeQuery();

            // The array list that gets populated with the unique fligthID and departure time combinations for now.
            ArrayList<String> flights = new ArrayList<>();

            while(result.next()){
                flights.add("flight: " + result.getString(1) + " at " + result.getString(2) + ".");
            }
            return flights;

        } catch (Exception e){
            System.out.println("Error fetching the chosen flight date and location.");
            throw e;
        }
    }

    //Method that returns all the distinct airport names.
    public static ArrayList<String> dbAirports(){
        try{
            String search = "Select DISTINCT Location from Airport";
            PreparedStatement prepStat = c.prepareStatement(search);
            ResultSet result = prepStat.executeQuery();

            ArrayList<String> airports = new ArrayList<>();

            while (result.next()){
                airports.add(result.getString(1));
            }
            return airports;

        }catch (Exception e){
            System.out.println("Error finding the airport names.");
        }
        return null;
    }

    // turns off the connection when the application is no longer in use.
    public static void closeConnection() throws SQLException {
        c.close();
    }
}
