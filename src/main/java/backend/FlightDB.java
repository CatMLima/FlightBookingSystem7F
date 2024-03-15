package backend;

import java.sql.*;
import java.util.ArrayList;

public class FlightDB {
    /* Where all queries about finding flights, updating flights and adding flights will reside.*/

    final static String [] seats = {"1A","1B","1C","1D",
            "2A","2B","2C","2D",
            "3A","3B","3C","3D",
            "4A","4B","4C","4D",
            "5A","5B","5C","5D",
            "6A","6B","6C","6D",
            "7A","7B","7C","7D",
            "8A","8B","8C","8D",
            "9A","9B","9C","9D",
            "10A","10B","10C","10D",
            "11A","11B","11C","11D",
            "12A","12B","12C","12D",
            "13A","13B","13C","13D",
            "14A","14B","14C","14D",
            "15A","15B","15C","15D",
            "16A","16B","16C","16D",
            "17A","17B","17C","17D",
            "18A","18B","18C","18D",
            "19A","19B","19C","19D",
            "20A","20B","20C","20D"};

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

    /*
    Test method to create a flight object whenever a search is conducted.
     */
    public static ArrayList<Flight> dbFlightSearch(String location, String destination, String date) throws SQLException{
        try{

            String sql = "Select DISTINCT Flights.FlightID, DepTime from Flights, Airport a1, Airport a2 where Flights.FlightID = a1.FlightID " +
                    "AND Flights.FlightID = a2.FlightID AND a1.Location = (?) AND a2.location = (?) AND DepDate = (?) " +
                    "AND Taken=FALSE AND a1.FlightType='Departure' AND a2.FlightType='Arrival'";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, location);
            preparedStatement.setString(2,destination);
            preparedStatement.setString(3,date);
            ResultSet result = preparedStatement.executeQuery();

            ArrayList<Flight> flights = new ArrayList<>();

            while(result.next()){
                String flightID = result.getString(1);
                flights.add(new Flight(location,destination,Date.valueOf(date),flightID, SeatDB.findSeats(flightID,date), "On Time"));
            }
            return flights;

        } catch (Exception e){
            System.out.println("Error fetching the chosen flight date and location.");
            throw e;
        }
    }

    // testing dbFlightSearch
    public static void main(String [] args) throws SQLException, ClassNotFoundException {
        FlightDB.initialize();
        SeatDB.initialize();
        ArrayList<Flight> flightList = dbFlightSearch("Reykjavik Domestic Airport (RKV)","Akureyri Domestic Airport (AEY)", "2024-03-16");
        Flight flight = flightList.get(0);
        System.out.println(flight.getLocation());
        ArrayList<Seat> seatsList = flight.getSeats();
        System.out.println(seatsList.get(0).getBooked());
    }



    // This method focuses on getting an input location and date and showing the user which seats are available.
    // So far there is only one flight so don't expect much.
    public static ArrayList<String> dbFindFlight(String location, String destination, String date) throws SQLException, ClassNotFoundException {
        try{

            // Testing that given the Location and Date of travels the name of the flight and time shows up.
            String sql = "Select DISTINCT Flights.FlightID, DepTime from Flights, Airport a1, Airport a2 where Flights.FlightID = a1.FlightID " +
                    "AND Flights.FlightID = a2.FlightID AND a1.Location = (?) AND a2.location = (?) AND DepDate = (?) " +
                    "AND Taken=FALSE AND a1.FlightType='Departure' AND a2.FlightType='Arrival'";
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

    /*
    This method is responsible for inserting new flights into the database, it receives information about
    the flightID, departure date and time, arrival date and time, and from which airport to which airport.
    The main goal here is that we create a flight first and then add it to two airports, where it is a
    departure and where it is an arrival.
     */
    public static void createFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {
        String locationID = fetchID(location);
        String destinationID = fetchID(destination);
        int count = seats.length;
        boolean value = false;
        try {
            for (int i = 0; i < count; i++) {
                String statement = "INSERT INTO Flights VALUES ((?),(?),(?),(?),(?),(?),(?))";
                PreparedStatement insert = c.prepareStatement(statement);
                insert.setString(1, flightID);
                insert.setString(2, seats[i]);
                insert.setBoolean(3, value);
                insert.setString(4, depDate);
                insert.setString(5, depTime);
                insert.setString(6, arrDate);
                insert.setString(7, arrTime);
                insert.executeUpdate();
            }

            String airportAssign = "INSERT INTO Airport VALUES ((?),(?),(?),(?))";

            //Add the flight as a departure from airport
            PreparedStatement locationInsert = c.prepareStatement(airportAssign);
            locationInsert.setString(1, locationID);
            locationInsert.setString(2, location);
            locationInsert.setString(3, flightID);
            locationInsert.setString(4, "Departure");
            locationInsert.executeUpdate();

            //Add the flight as an arrival to an airport
            PreparedStatement destinationInsert = c.prepareStatement(airportAssign);
            destinationInsert.setString(1, destinationID);
            destinationInsert.setString(2, destination);
            destinationInsert.setString(3, flightID);
            destinationInsert.setString(4, "Arrival");
            destinationInsert.executeUpdate();
        } catch (Exception e){
            System.out.println("Error adding the flight, check Primary Keys.");
        }
    }

    public static String fetchID(String location) throws SQLException {
        String query = "Select AirportID from AirportSolo WHERE Location=(?)";
        PreparedStatement prep = c.prepareStatement(query);
        prep.setString(1,location);
        ResultSet result = prep.executeQuery();
        return result.getString(1);
    }

    public static void closeConnection() throws SQLException {
        c.close();
    }
}
