package backend;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FlightDBInterface {

    void initialize() throws ClassNotFoundException;
    ArrayList<Flight> dbFlightSearch(String location, String destination, String date) throws SQLException;
    void createFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException;
    void closeConnection() throws SQLException;
}
