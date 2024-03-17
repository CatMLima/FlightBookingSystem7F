package backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchControllerTest {

    Flight flight;
    ArrayList<Seat> seats = new ArrayList<>();

    Date date = Date.valueOf("2024-06-06");
    @BeforeEach
    public void setUp(){
        flight = new Flight("Reykjavik", "Akureyri", date,
                "RVL001", seats, "On Time");
    }

    @AfterEach
    public void tearDown(){
        flight = null;
    }

    @Test
    public void testSearchByLocation(){
        String location = flight.getLocation();
        assertEquals("Reykjavik", location);
    }

}
