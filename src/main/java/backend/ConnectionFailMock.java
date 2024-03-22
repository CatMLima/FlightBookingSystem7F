package backend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionFailMock implements FlightDBInterface{


    public ConnectionFailMock(Flight flight){

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
    public void update(Flight flight, String status) throws SQLException{
        throw new SQLException();
    }
}
