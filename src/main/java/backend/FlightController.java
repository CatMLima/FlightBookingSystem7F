package backend;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class FlightController {

    private FlightDB db;

    public FlightController(FlightDB flightDB){
        db = flightDB;
    }

    public void addFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {
        db.create(flightID, location, destination, depDate, depTime, arrDate, arrTime);
    }

    public ArrayList<Flight> searchFlights(String departureLocation, String arrivalLocation, String date) throws SQLException, ParseException {
        return db.select(departureLocation, arrivalLocation, date);
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
        db.update(f, depDate, depTime, arrDate, arrTime, status);
    }

    public ArrayList<String> getAirportNames(){
        return db.getAirportNames();
    }

    // TODO: Code how the available seat display is coded, both to show what is available and what happens when a seat is chosen.
    public Seat[][] getSeatAvailability(Flight flight){
        Seat [][] seats = new Seat[20][4];
        int columns = 4;
        int rows = 20;
        int seatCount = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                seats[i][j] = flight.getSeats().get(seatCount++);
            }
        }

        return seats;
    }

    public void deleteFlight(Flight f) {
        db.delete(f);
    }

    public void callSpecial() throws SQLException {
        db.specialAdd();
    }
}
