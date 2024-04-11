package backend;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class FlightController {

    private FlightDB db;

    public FlightController(FlightDB flightDB){
        db = flightDB;
    }

    /*
    This method can be used to add more flights to the database. Initially we thought that there should be a page for
    managers to have access to the system.
     */
    public void addFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {
        db.create(flightID, location, destination, depDate, depTime, arrDate, arrTime);
    }

    /*
    When a general search with only location, destination and date is made, this method is called.
     */
    public ArrayList<Flight> searchFlights(String departureLocation, String arrivalLocation, String date) throws SQLException, ParseException {
        return db.select(departureLocation, arrivalLocation, date);
    }

    /*
    When a search that has a maximum price the user is willing to pay is made, this method is called.
     */
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

    /*
    Again, this is tied to the feature of management being able to update flight status.
     */
    public void updateFlight(Flight f, String depDate, String depTime, String arrDate, String arrTime, String status) {
        db.update(f, depDate, depTime, arrDate, arrTime, status);
    }

    /*
    This can be used by the UI class to display all the possible places to leave from and go to.
     */
    public ArrayList<String> getAirportNames(){
        return db.getAirportNames();
    }

    // TODO: Code how the available seat display is coded, both to show what is available and what happens when a seat is chosen.
    /*
    This method searches which seats at available and returns the seat objects to be used in UI elements and tied
    to a booking.
     */
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


    /*
    Another managerial method in case a flight can be completely taken away. Can also be used
    to clean the database after enough time has passed since the flight happened.
     */
    public void deleteFlight(Flight f) {
        db.delete(f);
    }

}
