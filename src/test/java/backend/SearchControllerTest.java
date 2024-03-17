package backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchControllerTest {

    Date date = Date.valueOf("2024-06-06");

    ArrayList<Seat> seats = new ArrayList<>();

    FlightDBInterface flightMock = new FlightDBConnectionMock("Reykjavik", "Akureyri", date, "RVK001", seats, "On Time");

    @Test
    public void testSearchByLocation() throws SQLException {
        ArrayList<Flight> flights = flightMock.dbFlightSearch("Reykjavik","Akureyri", String.valueOf(date));
        assertEquals("RVK001",flights.get(0).getId());


    }


}
