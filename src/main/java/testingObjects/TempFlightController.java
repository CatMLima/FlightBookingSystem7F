package testingObjects;

import backend.Flight;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class TempFlightController {

    FlightDBInterface flightDBInterface;

    /*
    Constructor of the controller class.
     */
    public TempFlightController(FlightDBInterface flightDBInterface){
        this.flightDBInterface = flightDBInterface;
    }

    /*
    This method returns an array list of Flight objects created by the FlightDBInterface class that filters for the
    given parameters.
     */

    public ArrayList<Flight> searchFlights(String location, String destination, String date) throws SQLException {
        return flightDBInterface.select(location, destination, date);
    }

    /*
    When the user is searching with the addition of a max price set (something the UI will recognize), the searchFlightsWithPrice
    method is called instead of the default searchFlights method.
     */
    public ArrayList<Flight> searchFlightsWithPrice(String departureLocation, String arrivalLocation, String date, int maxPrice) throws SQLException {
        ArrayList<Flight> unfilteredFlights = searchFlights(departureLocation, arrivalLocation, date);
        ArrayList<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight: unfilteredFlights){
            if (flight.getStartingPrice() <= maxPrice){
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }

    /*
    When airport employees are adding new flights in this method will be called with all the parameters collected from the UI.
    This can also be programmed to create recurring flights when the current date is two months ahead of the next time the recurring flight
    would be created.
     */
    public void addFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {
        flightDBInterface.create(flightID, location, destination, depDate, depTime, arrDate, arrTime);
    }

    /*
    This method is quite versatile, depending on which aspect of the flight is being updated, the parameter is included as the same date or new date, old status or
    new status for example.
     */
    public void updateFlight(Flight flight, String depDate, String depTime, String arrDate, String arrTime, String newStatus) throws SQLException, ParseException {
        flightDBInterface.update(flight, depDate, depTime, arrDate, arrTime, newStatus);
    }

    /*
    This is a call that can be used by airport employees or by the system itself when a flight is old enough to be deleted from our records.
     */
    public void deleteFlight(Flight f){
        flightDBInterface.delete(f);
    }


}
