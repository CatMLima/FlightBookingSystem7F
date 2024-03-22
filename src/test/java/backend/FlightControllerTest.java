package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightControllerTest {
    ArrayList<Seat> seats = new ArrayList<>();

    Flight flight;

    String departureDate = "2024-06-06";

    String departureTime = "20:00";
    String arrivalDate = "2024-06-06";

    String arrivalTime = "20:45";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    java.util.Date depDateTime = dateFormat.parse(departureDate + " " + departureTime);
    java.util.Date arrDateTime = dateFormat.parse(arrivalDate + " " + arrivalTime);

    public FlightControllerTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() throws ParseException {
        flight = new Flight("Reykjavik", "Akureyri", depDateTime, arrDateTime,"RVK001",
                seats, "On Time");
    }

    /*
    Testing that the correct list of flights is created when the given search parameters are given.
     */
    @Test
    public void testSearchLocationSuccess() throws SQLException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flight);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlights("Reykjavik", "Akureyri","2024-06-06");
        assertEquals("RVK001", result.get(0).getId());
    }

    /*
    Testing that when a flight status is changed that the change takes place.
     */
    @Test
    public void testUpdateFlightStatus() throws SQLException, ParseException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flight);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        flightController.updateFlight(flight, departureDate, departureTime, arrivalDate, arrivalTime, "Late");
        assertEquals("Late", flight.getStatus());
    }

    /*
    Testing that when serves crash that the appropriate system response will happen.
     */
    @Test
    public void testConnectionFailure(){
        FlightDBInterface connectionFailure = new ConnectionFailMock(flight);
        TempFlightController flightController = new TempFlightController(connectionFailure);
        assertThrows(SQLException.class, () -> flightController.searchFlights("Reykjavik", "Akureyri","2024-06-06"));
    }

    /*
    Test that when the starting price of the flight is exactly the same as the price the user is searching for, the flight will appear in the results.
     */
    @Test
    public void testSearchFlightsByPriceSuccess() throws SQLException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flight);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlightsWithPrice("Reykjavik", "Akureyri", "2024-06-06", 13500);
        assertEquals(1, result.size());
    }

    /*
    With the knowledge that the Flight object we created has a starting price of 13500, we test that if the maximum is set at 13499, the Flight won't
    appear in the results.
     */
    @Test
    public void testSearchByPriceCornerCase1plus() throws SQLException{
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flight);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlightsWithPrice("Reykjavik", "Akureyri", "2024-06-06", 13499);
        assertEquals(0, result.size());
    }

    /*
    Now we test that if the user's maximum is 1kr above the price of the flight, it will appear in the search results.
     */
    @Test
    public void testSearchByPriceCornerCase1minus() throws SQLException{
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flight);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlightsWithPrice("Reykjavik", "Akureyri", "2024-06-06", 13501);
        assertEquals(1, result.size());
    }

}
