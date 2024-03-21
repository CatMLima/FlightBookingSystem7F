package backend;

import catlima.todatabasefrominterfacetest.FlightSearchUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightControllerTest {

    Date departureDate = Date.valueOf("2024-06-06 20:00");
    Date arrivalDate = Date.valueOf("2024-06-06 20:45");
    ArrayList<Seat> seats = new ArrayList<>();

    Flight flight;

    @BeforeEach
    public void setUp(){
        flight = new Flight("Reykjavik", "Akureyri", Date.valueOf("2024-06-06 20:00"),
                Date.valueOf("2024-06-06 20:45"), "RVK001", seats, "On Time");
    }

    @Test
    public void otherTest() {
       assertEquals(flight.getId(), "RVK001");
    }


}
