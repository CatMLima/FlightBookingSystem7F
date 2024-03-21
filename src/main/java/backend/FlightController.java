package backend;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class FlightController {

    ArrayList<Flight> flights = new ArrayList<Flight>();
    FlightDB db = new FlightDB();

    public void addFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {
        FlightDB.create(flightID, location, destination, depDate, depTime, arrDate, arrTime);
    }

    public ArrayList<Flight> searchFlights(String departureLocation, String arrivalLocation, String date) throws SQLException, ParseException {
        return FlightDB.select(departureLocation, arrivalLocation, date);
    }

    public ArrayList<Flight> searchFlightsWithPrice(String departureLocation, String arrivalLocation, String date, int maxPrice) throws SQLException, ParseException {
        ArrayList<Flight> unfilteredFlights = searchFlights(departureLocation, arrivalLocation, date);
        ArrayList<Flight> filteredFlights = new ArrayList<>();
        
        for (Flight flight : unfilteredFlights) {
            if (flight.getStartingPrice() <= maxPrice) {
                filteredFlights.add(flight);
            }
        }
        
        return filteredFlights;
    }

    public void updateFlight(Flight f, String depDate, String depTime, String arrDate, String arrTime, String status) {
        FlightDB.update(f, depDate, depTime, arrDate, arrTime, status);
    }

    public void deleteFlight(Flight f) {
        FlightDB.delete(f);
    }
}
