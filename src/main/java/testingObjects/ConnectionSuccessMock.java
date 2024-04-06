package testingObjects;

import backend.Flight;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// simulates finding a flight
public class ConnectionSuccessMock implements FlightDBInterface {

    ArrayList<Flight> flights;

    public ConnectionSuccessMock(ArrayList<Flight> flights){
        this.flights = flights;
    }
    @Override
    public void initialize() throws ClassNotFoundException {

    }

    @Override
    public void closeConnection() throws SQLException {

    }

    @Override
    public ArrayList<Flight> select(String location, String destination, String date) throws SQLException{
        ArrayList<Flight> flightsAnswer = new ArrayList<>();
        for (Flight flight: flights ){
            if (flight.getLocation().equals(location) && flight.getDestination().equals(destination)){
                flightsAnswer.add(flight);
            }
        }
        return flightsAnswer;
    }

    @Override
    public ArrayList<Flight> selectPrice(int price) throws SQLException{
        ArrayList<Flight> flightsAnswer = new ArrayList<>();
        for (Flight flight : flights){
            if (flight.getStartingPrice() <= price){
                flightsAnswer.add(flight);
            }
        }
        return flightsAnswer;
    }

    @Override
    public ArrayList<Flight> selectDate(java.util.Date date) throws SQLException{
        ArrayList<Flight> flightsAnswer = new ArrayList<>();
        for (Flight flight : flights){
            if (flight.getDepartureDate() == date){
                flightsAnswer.add(flight);
            }
        }

        return flightsAnswer;
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
