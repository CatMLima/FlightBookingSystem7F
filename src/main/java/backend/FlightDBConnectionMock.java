package backend;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

// simulates finding a flight
public class FlightDBConnectionMock implements FlightDBInterface{

    Flight flight;
    private Date departureDate;

    private Date arrDate;
    private ArrayList<Seat> seats;
    private String status;
    private String location;
    private String flightID;

    private String destination;

    public FlightDBConnectionMock(String location, String destination, Date departureDate, Date arrDate, String id, ArrayList<Seat> seats, String status){
        this.location = location;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrDate = arrDate;
        flightID = id;
        this.seats = seats;
        this.status = status;
    }
    @Override
    public void initialize() throws ClassNotFoundException {

    }

    @Override
    public ArrayList<Flight> dbFlightSearch(String location, String destination, String date) throws SQLException {
        ArrayList<Flight> flights = new ArrayList<>();
        flight = new Flight(this.location, this.destination, this.departureDate, this.arrDate, this.flightID, this.seats, this.status);
        flights.add(flight);
        return flights;

    }

    @Override
    public void createFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {

    }

    @Override
    public void closeConnection() throws SQLException {

    }
}
