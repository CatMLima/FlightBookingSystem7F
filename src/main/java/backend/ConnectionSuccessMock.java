package backend;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

// simulates finding a flight
public class ConnectionSuccessMock implements FlightDBInterface{

    Flight flight;

    public ConnectionSuccessMock(String location, String destination, Date departureDate, Date arrDate, String id, ArrayList<Seat> seats, String status){
        flight = new Flight(location,destination,departureDate,arrDate,id,seats,status);
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
    public ArrayList<Flight> selectLocation(String location) throws SQLException{
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
        int flightprice = flight.calculateDuration() * 300;
        if (flightprice == price){
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
    public void update(Flight newFlight, String status) throws SQLException{
        if (Objects.equals(flight.getId(), newFlight.getId())){
            flight.setStatus(status);
        }
    }
}
