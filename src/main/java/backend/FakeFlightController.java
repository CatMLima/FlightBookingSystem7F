package backend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class FakeFlightController {

    FlightDBInterface flightDBInterface;
    public FakeFlightController(FlightDBInterface flightDBInterface){
        this.flightDBInterface = flightDBInterface;
    }

    public ArrayList<Flight> searchLocation(String location, String destination, String date) throws SQLException {
        return flightDBInterface.select(location, destination, date);
    }

    public ArrayList<Flight> searchPrice(int price) throws SQLException {
        return flightDBInterface.selectPrice(price);
    }

    public ArrayList<Flight> searchDate(Date date) throws SQLException{
        return flightDBInterface.selectDate(date);
    }

    public void updateStatus(Flight flight, String newStatus) throws SQLException{
        flightDBInterface.update(flight, newStatus);
    }


}
