package backend;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

// simulates finding a flight
public class ConnectionSuccessMock implements FlightDBInterface{

    Flight flight;

    public ConnectionSuccessMock(Flight flight){
        this.flight = flight;
    }
    @Override
    public void initialize() throws ClassNotFoundException {

    }

    @Override
    public ArrayList<Flight> dbFlightSearch(String location, String destination, String date) throws SQLException {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight);
        return flights;

    }

    @Override
    public void createFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {

    }

    @Override
    public void closeConnection() throws SQLException {

    }

    @Override
    public ArrayList<Flight> select(String location, String destination, String date) throws SQLException{
        ArrayList<Flight> flights = new ArrayList<>();
        if (flight.getLocation().equals(location)){
            flights.add(flight);
            return flights;
        }
        return null;
    }

    @Override
    public ArrayList<Flight> selectPrice(int price) throws SQLException{
        ArrayList<Flight> flights = new ArrayList<>();
        int flightPrice = flight.calculateDuration() * 300;
        if (flightPrice == price){
            flights.add(flight);
            return flights;
        }
        return null;
    }

    @Override
    public ArrayList<Flight> selectDate(java.util.Date date) throws SQLException{
        ArrayList<Flight> flights = new ArrayList<>();
        if (flight.getDepartureDate().equals(date)){
            flights.add(flight);
            return flights;
        }
        return null;
    }

    @Override
    public void update(Flight flight, String depDate, String depTime, String arrDate, String arrTime, String status) throws SQLException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date depDateTime = dateFormat.parse(depDate + " " + depTime);
        java.util.Date arrDateTime = dateFormat.parse(arrDate + " " + arrTime);
        flight.setDepartureDate(depDateTime);
        flight.setArrivalDate(arrDateTime);
        flight.setStatus(status);
    }

    @Override
    public void create(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {

    }

    @Override
    public void delete(Flight flight) {

    }

    @Override
    public String fetchID(String location) throws SQLException {
        return null;
    }

}
