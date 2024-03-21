package backend;

import catlima.todatabasefrominterfacetest.FlightSearchUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightControllerTest {
    ArrayList<Seat> seats = new ArrayList<>();

    Flight flight;

    String departureDateTime = "2024-06-06 20:00";
    String arrivalDateTime = "2024-06-06 20:45";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    java.util.Date depDateTime = dateFormat.parse(departureDateTime);
    java.util.Date arrDateTime = dateFormat.parse(arrivalDateTime);

    public FlightControllerTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() throws ParseException {
        flight = new Flight("Reykjavik", "Akureyri", depDateTime, arrDateTime,"RVK001",
                seats, "On Time");
    }

    @Test
    public void searchLocationTest() throws SQLException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flight);
        FakeFlightController flightController = new FakeFlightController(connectionSuccess);
        ArrayList<Flight> result = flightController.searchLocation("Reykjavik");
        assertEquals("RVK001", result.get(0).getId());
    }

    @Test
    public void updateFlightTest() throws SQLException {
        FlightDBInterface connectionSuccess = new ConnectionSuccessMock(flight);
        FakeFlightController flightController = new FakeFlightController(connectionSuccess);
        flightController.updateStatus(flight, "Late");
        assertEquals("Late", flight.getStatus());
    }

    @Test
    public void testConnectionFailure(){
        FlightDBInterface connectionFailure = new ConnectionFailMock(flight);
        FakeFlightController flightController = new FakeFlightController(connectionFailure);
        assertThrows(SQLException.class, () -> flightController.searchLocation("Reykjavik"));
    }

}
