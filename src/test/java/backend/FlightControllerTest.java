package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testingObjects.ConnectionFailMock;
import testingObjects.ConnectionSuccessMock;
import testingObjects.FlightDBInterface;
import testingObjects.TempFlightController;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightControllerTest {

    /*
    Needed information to create a flight object to test on.
     */
    ArrayList<Seat> seats = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    ArrayList<Flight> flights;

    public FlightControllerTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() throws ParseException {
        Flight flight1= new Flight("Reykjavik", "Akureyri", dateFormat.parse("2024-06-06 20:00"), dateFormat.parse("2024-06-06 20:45"),"RVK001",
                seats, "On Time");
        Flight flight2 = new Flight("Akureyri", "Reykjavik", dateFormat.parse("2024-06-07 20:00"), dateFormat.parse("2024-06-07 20:45"),
                "AKY002", seats, "On Time");
        Flight flight3 = new Flight("Vestmannaeyjar", "Reykjavik", dateFormat.parse("2024-06-08 10:00"), dateFormat.parse("2024-06-08 10:30"),
                "VST003", seats, "On Time");
        Flight flight4 = new Flight("Reykjavik", "Akureyri", dateFormat.parse("2024-06-06 10:00"), dateFormat.parse("2024-06-06 10:45"),
                "RVK002", seats, "On Time");
        flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    /*
    Testing that the correct list of flights is created when the given search parameters are given. We have two flights from the same location to
    the same destination on the same day but at different times, here we test that both are returned in the list.
     */
    @Test
    public void testSearchLocationSuccess() throws SQLException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flights);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlights("Vestmannaeyjar", "Reykjavik","2024-06-08");
        assertEquals(1, result.size());
    }

    /*
    Testing that the list will be empty if the item does not match any of the items in the list of flights exactly.
     */
    @Test
    public void testSearchLocationFail() throws SQLException{
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flights);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlights("Vestmannaeyjar", "Akureyri","2024-06-06");
        assertEquals(0, result.size());
    }

    /*
    Testing that when a flight status is changed that the change takes place.
     */
    @Test
    public void testUpdateFlightStatus() throws SQLException, ParseException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flights);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        // The first flight is the one from Reykjavik to Akureyri, its current status is "On Time"
        Flight flight = flights.get(0);
        flightController.updateFlight(flight, "2024-06-06", "20:00", "2024-06-06", "21:00", "Late");
        assertEquals("Late", flight.getStatus());
    }

    /*
    Testing that when serves crash that the appropriate system response will happen.
     */
    @Test
    public void testConnectionFailure(){
        FlightDBInterface connectionFailure = new ConnectionFailMock(flights);
        TempFlightController flightController = new TempFlightController(connectionFailure);
        assertThrows(SQLException.class, () -> flightController.searchFlights("Reykjavik", "Akureyri","2024-06-06"));
    }

    /*
    Test that when the starting price of the flight is exactly the same as the price the user is searching for, the flight will appear in the results.
    There are two flights with this price, so it should match it. We have two flights that should fulfill these criteria.
     */
    @Test
    public void testSearchFlightsByPriceSuccess() throws SQLException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flights);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlightsWithPrice("Reykjavik", "Akureyri", "2024-06-06", 13500);
        assertEquals(2, result.size());
    }

    /*
    With the knowledge that the Flight list we created has two flights with starting price of 13500, we test that if the maximum is set at 13499, the Flights won't
    appear in the results.
     */
    @Test
    public void testSearchByPriceCornerCase1plus() throws SQLException{
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flights);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlightsWithPrice("Reykjavik", "Akureyri", "2024-06-06", 13499);
        assertEquals(0, result.size());
    }

    /*
    Now we test that if the user's maximum is 1kr above the price of the flights, they will appear in the search results.
     */
    @Test
    public void testSearchByPriceCornerCase1minus() throws SQLException{
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flights);
        TempFlightController flightController = new TempFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchFlightsWithPrice("Reykjavik", "Akureyri", "2024-06-06", 13501);
        assertEquals(2, result.size());
    }

}
