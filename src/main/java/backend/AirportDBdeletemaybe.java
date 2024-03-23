package backend;

import java.sql.*;
import java.util.ArrayList;

public class AirportDBdeletemaybe {
    //queries about airports will take place here

    static Connection c;

    public AirportDBdeletemaybe() throws ClassNotFoundException{
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

    //This methods returns all the existing airports;
    public ArrayList<String> dbAirportNames(){
        try{
            String query = "Select DISTINCT Location from AirportSolo";
            PreparedStatement prep = c.prepareStatement(query);
            ResultSet result = prep.executeQuery();

            ArrayList<String> airportLocations = new ArrayList<>();
            while (result.next()){
                airportLocations.add(result.getString(1));
            }
            return airportLocations;

        } catch (Exception e){
            System.out.println("Error in finding all existing airports.");
        }
        return null;
    }

    public void closeConnection() throws SQLException {
        c.close();
    }
}
