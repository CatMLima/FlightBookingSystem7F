package backend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionFailMock implements FlightDBInterface{


    public ConnectionFailMock(ArrayList<Flight> flights){

    }
    @Override
    public void initialize() throws ClassNotFoundException {

    }

    @Override
    public ArrayList<Flight> dbFlightSearch(String location, String destination, String date) throws SQLException {
        return null;
    }

    @Override
    public void createFlight(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {

    }

    @Override
    public void closeConnection() throws SQLException {

    }

    @Override
    public ArrayList<Flight> select(String location, String destination, String date) throws SQLException{
        throw new SQLException();
    }

    @Override
    public ArrayList<Flight> selectPrice(int price) throws SQLException {
        throw new SQLException();
    }

    @Override
    public ArrayList<Flight> selectDate(Date date) throws SQLException{
        throw new SQLException();
    }

    @Override
    public void update(Flight flight, String depDate, String depTime, String arrDate, String arrTime, String status) throws SQLException {

    }

    @Override
    public void create(String flightID, String location, String destination, String depDate, String depTime, String arrDate, String arrTime) throws SQLException {

    }

    @Override
    public void delete(Flight flight) {

    }

    @Override
    public String fetchID(String location) throws SQLException {
        return null;
    }

}
