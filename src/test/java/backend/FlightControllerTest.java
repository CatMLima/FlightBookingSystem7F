package backend;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchControllerTest {

    Date departureDate = Date.valueOf("2024-06-06 20:00");

    Date arrivalDate = Date.valueOf("2024-06-06 20:45");

    ArrayList<Seat> seats = new ArrayList<>();

    FlightDBInterface flightMock = new FlightDBConnectionMock("Reykjavik", "Akureyri", departureDate, arrivalDate, "RVK001", seats, "On Time");

    @Test
    public void testSearchByLocation() throws SQLException {
        ArrayList<Flight> flights = flightMock.dbFlightSearch("Reykjavik","Akureyri", String.valueOf(departureDate));
        assertEquals("RVK001",flights.get(0).getId());


    }


}
